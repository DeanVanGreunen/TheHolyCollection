package com.x3r0.theholycollection.entities.SnakeMob;

import com.x3r0.theholycollection.TheHolyCollection;
import com.x3r0.theholycollection.entities.WeirdMob.WeirdMobEntity;
import com.x3r0.theholycollection.entities.WeirdMob.WeirdMobModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class SnakeMobRenderer extends MobRenderer<SnakeMobEntity, SnakeMobModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(TheHolyCollection.MODID, "textures/entity/snakemob.png");
    public SnakeMobRenderer(EntityRendererManager manager){
        super(manager, new SnakeMobModel(), 0.3f); //0.25f is the shadow
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(SnakeMobEntity entity) {
        return TEXTURE;
    }
}