package com.susen36.shiny.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.susen36.shiny.ShinyMob;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WitherSkeletonRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;

public class SWitherSkeletonRender extends WitherSkeletonRenderer {

    public SWitherSkeletonRender(EntityRendererProvider.Context context) {
        super(context);
    }
    protected void scale(AbstractSkeleton p_116460_, PoseStack poseStack, float p_116462_) {
        poseStack.scale(1.0F, 1.0F, 1.0F);
    }
    public ResourceLocation getTextureLocation(AbstractSkeleton p_116458_) {
        return new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_wither_skeleton.png");
    }
}