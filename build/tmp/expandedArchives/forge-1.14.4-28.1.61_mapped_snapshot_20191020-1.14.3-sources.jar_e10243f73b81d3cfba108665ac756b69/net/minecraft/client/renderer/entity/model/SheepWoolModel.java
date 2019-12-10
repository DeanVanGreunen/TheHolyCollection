package net.minecraft.client.renderer.entity.model;

import net.minecraft.entity.passive.SheepEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SheepWoolModel<T extends SheepEntity> extends QuadrupedModel<T> {
   private float headRotationAngleX;

   public SheepWoolModel() {
      super(12, 0.0F);
      this.headModel = new RendererModel(this, 0, 0);
      this.headModel.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
      this.headModel.setRotationPoint(0.0F, 6.0F, -8.0F);
      this.body = new RendererModel(this, 28, 8);
      this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 1.75F);
      this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
      float f = 0.5F;
      this.legBackRight = new RendererModel(this, 0, 16);
      this.legBackRight.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
      this.legBackRight.setRotationPoint(-3.0F, 12.0F, 7.0F);
      this.legBackLeft = new RendererModel(this, 0, 16);
      this.legBackLeft.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
      this.legBackLeft.setRotationPoint(3.0F, 12.0F, 7.0F);
      this.legFrontRight = new RendererModel(this, 0, 16);
      this.legFrontRight.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
      this.legFrontRight.setRotationPoint(-3.0F, 12.0F, -5.0F);
      this.legFrontLeft = new RendererModel(this, 0, 16);
      this.legFrontLeft.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
      this.legFrontLeft.setRotationPoint(3.0F, 12.0F, -5.0F);
   }

   public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
      super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
      this.headModel.rotationPointY = 6.0F + entityIn.getHeadRotationPointY(partialTick) * 9.0F;
      this.headRotationAngleX = entityIn.getHeadRotationAngleX(partialTick);
   }

   public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
      super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
      this.headModel.rotateAngleX = this.headRotationAngleX;
   }
}