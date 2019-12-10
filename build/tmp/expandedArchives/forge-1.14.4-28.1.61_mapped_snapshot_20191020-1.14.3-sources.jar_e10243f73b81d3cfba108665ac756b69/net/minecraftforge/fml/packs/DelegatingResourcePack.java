package net.minecraftforge.fml.packs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import net.minecraft.resources.ResourcePack;
import net.minecraft.resources.ResourcePackFileNotFoundException;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.resources.data.IMetadataSectionSerializer;
import net.minecraft.resources.data.PackMetadataSection;
import net.minecraft.util.ResourceLocation;

public class DelegatingResourcePack extends ResourcePack
{
    private final List<DelegatableResourcePack> delegates = new ArrayList<>();
    private final String name;
    private final PackMetadataSection packInfo;
    
    public DelegatingResourcePack(String id, String name, PackMetadataSection packInfo)
    {
        this(id, name, packInfo, Collections.emptyList());
    }

    public DelegatingResourcePack(String id, String name, PackMetadataSection packInfo, List<DelegatableResourcePack> packs)
    {
        super(new File(id));
        this.name = name;
        this.packInfo = packInfo;
        packs.forEach(this::addDelegate);
    }

    public void addDelegate(DelegatableResourcePack pack)
    {
        synchronized(delegates)
        {
            this.delegates.add(pack);
        }
    }
    
    @Override
    public String getName()
    {
        return name;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getMetadata(IMetadataSectionSerializer<T> deserializer) throws IOException
    {
        if (deserializer.getSectionName().equals("pack"))
        {
            return (T) packInfo;
        }
        return null;
    }

    @Override
    public Collection<ResourceLocation> getAllResourceLocations(ResourcePackType type, String pathIn, int maxDepth, Predicate<String> filter)
    {
        synchronized(delegates)
        {
            return delegates.stream()
                    .flatMap(r -> r.getAllResourceLocations(type, pathIn, maxDepth, filter).stream())
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Set<String> getResourceNamespaces(ResourcePackType type)
    {
        synchronized (delegates)
        {
            return delegates.stream()
                    .flatMap(r -> r.getResourceNamespaces(type).stream())
                    .collect(Collectors.toSet());
        }
    }

    @Override
    public void close() throws IOException
    {
        synchronized(delegates)
        {
            for (ResourcePack pack : delegates)
            {
                pack.close();
            }
        }
    }

    @Override
    protected InputStream getInputStream(String resourcePath) throws IOException
    {
        if (!resourcePath.equals("pack.png")) // Mods shouldn't be able to mess with the pack icon
        {
            synchronized (delegates)
            {
                for (DelegatableResourcePack pack : delegates)
                {
                    if (pack.resourceExists(resourcePath))
                    {
                        return pack.getInputStream(resourcePath);
                    }
                }
            }
        }
        throw new ResourcePackFileNotFoundException(this.file, resourcePath);
    }

    @Override
    protected boolean resourceExists(String resourcePath)
    {
        synchronized (delegates)
        {
            for (DelegatableResourcePack pack : delegates)
            {
                if (pack.resourceExists(resourcePath))
                {
                    return true;
                }
            }
        }
        return false;
    }
}