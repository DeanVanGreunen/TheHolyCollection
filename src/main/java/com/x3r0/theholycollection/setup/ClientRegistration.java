package com.x3r0.theholycollection.setup;

import com.x3r0.theholycollection.TheHolyCollection;
import com.x3r0.theholycollection.blocks.ModBlocks;
import com.x3r0.theholycollection.blocks.fancyblock.FancyBakedModel;
import com.x3r0.theholycollection.items.ModItems;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheHolyCollection.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistration {
    @SubscribeEvent
    public static void onItemColor(ColorHandlerEvent.Item event){
        event.getItemColors().register((stack, i) -> 0xff0000, ModItems.WEIRDMOB_EGG);
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event){
        if(!event.getMap().getBasePath().equals("textures")){
            return;
        }
        event.addSprite(new ResourceLocation(TheHolyCollection.MODID, "block/fancyblock"));
    }   

    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event){
        event.getModelRegistry().put(new ModelResourceLocation(ModBlocks.FANCYBLOCK.getRegistryName(), ""), //standard world
                new FancyBakedModel((DefaultVertexFormats.BLOCK)));
        event.getModelRegistry().put(new ModelResourceLocation(ModBlocks.FANCYBLOCK.getRegistryName(), "inventory"),
                new FancyBakedModel((DefaultVertexFormats.ITEM)));
    }
}
