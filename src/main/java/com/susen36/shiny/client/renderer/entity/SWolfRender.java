package com.susen36.shiny.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.world.entity.SSpiderEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Wolf;

public class SWolfRender extends WolfRenderer {
    private static final ResourceLocation WOLF_LOCATION = new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_wolf/shiny_wolf.png");
    private static final ResourceLocation WOLF_ANGRY_LOCATION = new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_wolf/shiny_wolf_angry.png");

    public SWolfRender(EntityRendererProvider.Context context) {
        super(context);
    }
    protected void scale(SSpiderEntity spider, PoseStack poseStack, float p_114759_) {
        poseStack.scale(1.2F, 1.2F, 1.2F);
    }
    public ResourceLocation getTextureLocation(Wolf p_116526_) {
        return p_116526_.isAngry() ? WOLF_ANGRY_LOCATION : WOLF_LOCATION;
    }
}
