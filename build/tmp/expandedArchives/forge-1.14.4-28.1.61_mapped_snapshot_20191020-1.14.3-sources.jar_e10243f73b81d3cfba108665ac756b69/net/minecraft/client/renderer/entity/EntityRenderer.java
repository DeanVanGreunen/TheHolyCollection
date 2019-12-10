package net.minecraft.client.renderer.entity;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import javax.annotation.Nullable;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class EntityRenderer<T extends Entity> {
   private static final ResourceLocation SHADOW_TEXTURES = new ResourceLocation("textures/misc/shadow.png");
   protected final EntityRendererManager renderManager;
   protected float shadowSize;
   protected float shadowOpaque = 1.0F;
   protected boolean renderOutlines;

   protected EntityRenderer(EntityRendererManager renderManager) {
      this.renderManager = renderManager;
   }

   public void setRenderOutlines(boolean renderOutlinesIn) {
      this.renderOutlines = renderOutlinesIn;
   }

   public boolean shouldRender(T livingEntity, ICamera camera, double camX, double camY, double camZ) {
      if (!livingEntity.isInRangeToRender3d(camX, camY, camZ)) {
         return false;
      } else if (livingEntity.ignoreFrustumCheck) {
         return true;
      } else {
         AxisAlignedBB axisalignedbb = livingEntity.getRenderBoundingBox().grow(0.5D);
         if (axisalignedbb.hasNaN() || axisalignedbb.getAverageEdgeLength() == 0.0D) {
            axisalignedbb = new AxisAlignedBB(livingEntity.posX - 2.0D, livingEntity.posY - 2.0D, livingEntity.posZ - 2.0D, livingEntity.posX + 2.0D, livingEntity.posY + 2.0D, livingEntity.posZ + 2.0D);
         }

         return camera.isBoundingBoxInFrustum(axisalignedbb);
      }
   }

   public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
      if (!this.renderOutlines) {
         this.renderName(entity, x, y, z);
      }

   }

   protected int getTeamColor(T entityIn) {
      ScorePlayerTeam scoreplayerteam = (ScorePlayerTeam)entityIn.getTeam();
      return scoreplayerteam != null && scoreplayerteam.getColor().getColor() != null ? scoreplayerteam.getColor().getColor() : 16777215;
   }

   protected void renderName(T entity, double x, double y, double z) {
      if (this.canRenderName(entity)) {
         this.renderLivingLabel(entity, entity.getDisplayName().getFormattedText(), x, y, z, 64);
      }
   }

   protected boolean canRenderName(T entity) {
      return entity.getAlwaysRenderNameTagForRender() && entity.hasCustomName();
   }

   protected void renderEntityName(T entityIn, double x, double y, double z, String name, double distanceSq) {
      this.renderLivingLabel(entityIn, name, x, y, z, 64);
   }

   @Nullable
   protected abstract ResourceLocation getEntityTexture(T entity);

   protected boolean bindEntityTexture(T entity) {
      ResourceLocation resourcelocation = this.getEntityTexture(entity);
      if (resourcelocation == null) {
         return false;
      } else {
         this.bindTexture(resourcelocation);
         return true;
      }
   }

   public void bindTexture(ResourceLocation location) {
      this.renderManager.textureManager.bindTexture(location);
   }

   /**
    * Renders a layer of fire on top of an entity.
    */
   private void renderEntityOnFire(Entity entity, double x, double y, double z, float partialTicks) {
      GlStateManager.disableLighting();
      AtlasTexture atlastexture = Minecraft.getInstance().getTextureMap();
      TextureAtlasSprite textureatlassprite = atlastexture.getSprite(ModelBakery.LOCATION_FIRE_0);
      TextureAtlasSprite textureatlassprite1 = atlastexture.getSprite(ModelBakery.LOCATION_FIRE_1);
      GlStateManager.pushMatrix();
      GlStateManager.translatef((float)x, (float)y, (float)z);
      float f = entity.getWidth() * 1.4F;
      GlStateManager.scalef(f, f, f);
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferbuilder = tessellator.getBuffer();
      float f1 = 0.5F;
      float f2 = 0.0F;
      float f3 = entity.getHeight() / f;
      float f4 = (float)(entity.posY - entity.getBoundingBox().minY);
      GlStateManager.rotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
      GlStateManager.translatef(0.0F, 0.0F, -0.3F + (float)((int)f3) * 0.02F);
      GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      float f5 = 0.0F;
      int i = 0;
      bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);

      while(f3 > 0.0F) {
         TextureAtlasSprite textureatlassprite2 = i % 2 == 0 ? textureatlassprite : textureatlassprite1;
         this.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
         float f6 = textureatlassprite2.getMinU();
         float f7 = textureatlassprite2.getMinV();
         float f8 = textureatlassprite2.getMaxU();
         float f9 = textureatlassprite2.getMaxV();
         if (i / 2 % 2 == 0) {
            float f10 = f8;
            f8 = f6;
            f6 = f10;
         }

         bufferbuilder.pos((double)(f1 - 0.0F), (double)(0.0F - f4), (double)f5).tex((double)f8, (double)f9).endVertex();
         bufferbuilder.pos((double)(-f1 - 0.0F), (double)(0.0F - f4), (double)f5).tex((double)f6, (double)f9).endVertex();
         bufferbuilder.pos((double)(-f1 - 0.0F), (double)(1.4F - f4), (double)f5).tex((double)f6, (double)f7).endVertex();
         bufferbuilder.pos((double)(f1 - 0.0F), (double)(1.4F - f4), (double)f5).tex((double)f8, (double)f7).endVertex();
         f3 -= 0.45F;
         f4 -= 0.45F;
         f1 *= 0.9F;
         f5 += 0.03F;
         ++i;
      }

      tessellator.draw();
      GlStateManager.popMatrix();
      GlStateManager.enableLighting();
   }

   /**
    * Renders the entities shadow.
    */
   private void renderShadow(Entity entityIn, double x, double y, double z, float shadowAlpha, float partialTicks) {
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
      this.renderManager.textureManager.bindTexture(SHADOW_TEXTURES);
      IWorldReader iworldreader = this.getWorldFromRenderManager();
      GlStateManager.depthMask(false);
      float f = this.shadowSize;
      if (entityIn instanceof MobEntity) {
         MobEntity mobentity = (MobEntity)entityIn;
         if (mobentity.isChild()) {
            f *= 0.5F;
         }
      }

      double d5 = MathHelper.lerp((double)partialTicks, entityIn.lastTickPosX, entityIn.posX);
      double d0 = MathHelper.lerp((double)partialTicks, entityIn.lastTickPosY, entityIn.posY);
      double d1 = MathHelper.lerp((double)partialTicks, entityIn.lastTickPosZ, entityIn.posZ);
      int i = MathHelper.floor(d5 - (double)f);
      int j = MathHelper.floor(d5 + (double)f);
      int k = MathHelper.floor(d0 - (double)f);
      int l = MathHelper.floor(d0);
      int i1 = MathHelper.floor(d1 - (double)f);
      int j1 = MathHelper.floor(d1 + (double)f);
      double d2 = x - d5;
      double d3 = y - d0;
      double d4 = z - d1;
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);

      for(BlockPos blockpos : BlockPos.getAllInBoxMutable(new BlockPos(i, k, i1), new BlockPos(j, l, j1))) {
         BlockPos blockpos1 = blockpos.down();
         BlockState blockstate = iworldreader.getBlockState(blockpos1);
         if (blockstate.getRenderType() != BlockRenderType.INVISIBLE && iworldreader.getLight(blockpos) > 3) {
            this.func_217759_a(blockstate, iworldreader, blockpos1, x, y, z, blockpos, shadowAlpha, f, d2, d3, d4);
         }
      }

      tessellator.draw();
      GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableBlend();
      GlStateManager.depthMask(true);
   }

   /**
    * Returns the render manager's world object
    */
   private IWorldReader getWorldFromRenderManager() {
      return this.renderManager.world;
   }

   private void func_217759_a(BlockState p_217759_1_, IWorldReader p_217759_2_, BlockPos p_217759_3_, double p_217759_4_, double p_217759_6_, double p_217759_8_, BlockPos p_217759_10_, float p_217759_11_, float p_217759_12_, double p_217759_13_, double p_217759_15_, double p_217759_17_) {
      if (p_217759_1_.func_224756_o(p_217759_2_, p_217759_3_)) {
         VoxelShape voxelshape = p_217759_1_.getShape(this.getWorldFromRenderManager(), p_217759_10_.down());
         if (!voxelshape.isEmpty()) {
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            double d0 = ((double)p_217759_11_ - (p_217759_6_ - ((double)p_217759_10_.getY() + p_217759_15_)) / 2.0D) * 0.5D * (double)this.getWorldFromRenderManager().getBrightness(p_217759_10_);
            if (!(d0 < 0.0D)) {
               if (d0 > 1.0D) {
                  d0 = 1.0D;
               }

               AxisAlignedBB axisalignedbb = voxelshape.getBoundingBox();
               double d1 = (double)p_217759_10_.getX() + axisalignedbb.minX + p_217759_13_;
               double d2 = (double)p_217759_10_.getX() + axisalignedbb.maxX + p_217759_13_;
               double d3 = (double)p_217759_10_.getY() + axisalignedbb.minY + p_217759_15_ + 0.015625D;
               double d4 = (double)p_217759_10_.getZ() + axisalignedbb.minZ + p_217759_17_;
               double d5 = (double)p_217759_10_.getZ() + axisalignedbb.maxZ + p_217759_17_;
               float f = (float)((p_217759_4_ - d1) / 2.0D / (double)p_217759_12_ + 0.5D);
               float f1 = (float)((p_217759_4_ - d2) / 2.0D / (double)p_217759_12_ + 0.5D);
               float f2 = (float)((p_217759_8_ - d4) / 2.0D / (double)p_217759_12_ + 0.5D);
               float f3 = (float)((p_217759_8_ - d5) / 2.0D / (double)p_217759_12_ + 0.5D);
               bufferbuilder.pos(d1, d3, d4).tex((double)f, (double)f2).color(1.0F, 1.0F, 1.0F, (float)d0).endVertex();
               bufferbuilder.pos(d1, d3, d5).tex((double)f, (double)f3).color(1.0F, 1.0F, 1.0F, (float)d0).endVertex();
               bufferbuilder.pos(d2, d3, d5).tex((double)f1, (double)f3).color(1.0F, 1.0F, 1.0F, (float)d0).endVertex();
               bufferbuilder.pos(d2, d3, d4).tex((double)f1, (double)f2).color(1.0F, 1.0F, 1.0F, (float)d0).endVertex();
            }
         }
      }
   }

   /**
    * Renders a white box with the bounds of the AABB trasnlated by an offset.
    */
   public static void renderOffsetAABB(AxisAlignedBB boundingBox, double x, double y, double z) {
      GlStateManager.disableTexture();
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferbuilder = tessellator.getBuffer();
      GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      bufferbuilder.setTranslation(x, y, z);
      bufferbuilder.begin(7, DefaultVertexFormats.POSITION_NORMAL);
      bufferbuilder.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).normal(0.0F, 0.0F, -1.0F).endVertex();
      bufferbuilder.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).normal(0.0F, 0.0F, -1.0F).endVertex();
      bufferbuilder.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).normal(0.0F, 0.0F, -1.0F).endVertex();
      bufferbuilder.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).normal(0.0F, 0.0F, -1.0F).endVertex();
      bufferbuilder.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).normal(0.0F, 0.0F, 1.0F).endVertex();
      bufferbuilder.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).normal(0.0F, 0.0F, 1.0F).endVertex();
      bufferbuilder.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).normal(0.0F, 0.0F, 1.0F).endVertex();
      bufferbuilder.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).normal(0.0F, 0.0F, 1.0F).endVertex();
      bufferbuilder.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).normal(0.0F, -1.0F, 0.0F).endVertex();
      bufferbuilder.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).normal(0.0F, -1.0F, 0.0F).endVertex();
      bufferbuilder.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).normal(0.0F, -1.0F, 0.0F).endVertex();
      bufferbuilder.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).normal(0.0F, -1.0F, 0.0F).endVertex();
      bufferbuilder.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).normal(0.0F, 1.0F, 0.0F).endVertex();
      bufferbuilder.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).normal(0.0F, 1.0F, 0.0F).endVertex();
      bufferbuilder.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).normal(0.0F, 1.0F, 0.0F).endVertex();
      bufferbuilder.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).normal(0.0F, 1.0F, 0.0F).endVertex();
      bufferbuilder.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).normal(-1.0F, 0.0F, 0.0F).endVertex();
      bufferbuilder.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).normal(-1.0F, 0.0F, 0.0F).endVertex();
      bufferbuilder.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).normal(-1.0F, 0.0F, 0.0F).endVertex();
      bufferbuilder.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).normal(-1.0F, 0.0F, 0.0F).endVertex();
      bufferbuilder.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).normal(1.0F, 0.0F, 0.0F).endVertex();
      bufferbuilder.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).normal(1.0F, 0.0F, 0.0F).endVertex();
      bufferbuilder.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).normal(1.0F, 0.0F, 0.0F).endVertex();
      bufferbuilder.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).normal(1.0F, 0.0F, 0.0F).endVertex();
      tessellator.draw();
      bufferbuilder.setTranslation(0.0D, 0.0D, 0.0D);
      GlStateManager.enableTexture();
   }

   /**
    * Renders the entity's shadow and fire (if its on fire). Args: entity, x, y, z, yaw, partialTickTime
    */
   public void doRenderShadowAndFire(Entity entityIn, double x, double y, double z, float yaw, float partialTicks) {
      if (this.renderManager.options != null) {
         if (this.renderManager.options.entityShadows && this.shadowSize > 0.0F && !entityIn.isInvisible() && this.renderManager.isRenderShadow()) {
            double d0 = this.renderManager.getDistanceToCamera(entityIn.posX, entityIn.posY, entityIn.posZ);
            float f = (float)((1.0D - d0 / 256.0D) * (double)this.shadowOpaque);
            if (f > 0.0F) {
               this.renderShadow(entityIn, x, y, z, f, partialTicks);
            }
         }

         if (entityIn.canRenderOnFire() && !entityIn.isSpectator()) {
            this.renderEntityOnFire(entityIn, x, y, z, partialTicks);
         }

      }
   }

   /**
    * Returns the font renderer from the set render manager
    */
   public FontRenderer getFontRendererFromRenderManager() {
      return this.renderManager.getFontRenderer();
   }

   /**
    * Renders an entity's name above its head
    */
   protected void renderLivingLabel(T entityIn, String str, double x, double y, double z, int maxDistance) {
      double d0 = entityIn.getDistanceSq(this.renderManager.info.getProjectedView());
      if (!(d0 > (double)(maxDistance * maxDistance))) {
         boolean flag = entityIn.shouldRenderSneaking();
         float f = this.renderManager.playerViewY;
         float f1 = this.renderManager.playerViewX;
         float f2 = entityIn.getHeight() + 0.5F - (flag ? 0.25F : 0.0F);
         int i = "deadmau5".equals(str) ? -10 : 0;
         GameRenderer.drawNameplate(this.getFontRendererFromRenderManager(), str, (float)x, (float)y + f2, (float)z, i, f, f1, flag);
      }
   }

   public EntityRendererManager getRenderManager() {
      return this.renderManager;
   }

   public boolean isMultipass() {
      return false;
   }

   public void renderMultipass(T entityIn, double x, double y, double z, float entityYaw, float partialTicks) {
   }

   public void func_217758_e(T p_217758_1_) {
      int i = p_217758_1_.getBrightnessForRender();
      int j = i % 65536;
      int k = i / 65536;
      GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float)j, (float)k);
   }
}