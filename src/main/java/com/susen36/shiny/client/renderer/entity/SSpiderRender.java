package com.susen36.shiny.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.world.entity.SSpiderEntity;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SSpiderRender extends MobRenderer<SSpiderEntity, SpiderModel<SSpiderEntity>> {

    public SSpiderRender(EntityRendererProvider.Context context) {
        super(context, new SpiderModel(context.bakeLayer(ModelLayers.SPIDER)), 0.7F);

    }
    protected float getFlipDegrees(SSpiderEntity p_116011_) {
        return 180.0F;
    }
    protected void scale(SSpiderEntity spider, PoseStack poseStack, float p_114759_) {
        poseStack.scale(1.2F, 1.2F, 1.2F);
    }

    public ResourceLocation getTextureLocation(SSpiderEntity p_116009_) {
        return new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_spider.png");
    }
}
