package com.x3r0.theholycollection.dimension;
import com.x3r0.theholycollection.TheHolyCollection;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.registries.ObjectHolder;

public class ModDimensions {

    public static final ResourceLocation DIMENSION_ID = new ResourceLocation(TheHolyCollection.MODID, "dimension");

    @ObjectHolder("theholycollection:dimension")
    public static ModDimension DIMENSION;

    public static DimensionType DIMENSION_TYPE;
}