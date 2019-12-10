package com.x3r0.theholycollection.entities;

import com.x3r0.theholycollection.TheHolyCollection;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class WeirdMobRenderer extends MobRenderer<WeirdMobEntity, WeirdMobModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(TheHolyCollection.MODID, "textures/entity/weirdmob.png");
    public WeirdMobRenderer(EntityRendererManager manager){
        super(manager, new WeirdMobModel(), 0.5f); //0.5f is the shadow
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(WeirdMobEntity entity) {
        return TEXTURE;
    }
}
