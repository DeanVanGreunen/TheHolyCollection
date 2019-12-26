package com.x3r0.theholycollection.entities.SnakeMob;

import com.x3r0.theholycollection.entities.WeirdMob.WeirdMobEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class SnakeMobModel extends EntityModel<SnakeMobEntity> {
    public RendererModel head;
    //public RendererModel body;
    //public RendererModel tail;

    public SnakeMobModel(){
        // Models
        head = new RendererModel(this, 0,0);
        head.addBox(-1.0F, -1.0F, -1.0F, 6, 1, 1, 0.0F);
    }

    @Override
    public void render(SnakeMobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        head.render(scale); 
    }
}
