package com.susen36.shiny.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.client.model.entity.SCreeperModel;
import com.susen36.shiny.world.entity.SCreeperEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SCreeperRender extends MobRenderer<SCreeperEntity, SCreeperModel<SCreeperEntity>> {
    private static final ResourceLocation SHINY_CREEPER = new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_creeper.png");
    private static final ResourceLocation SHINY_CREEPER_POWER = new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_creeper_power.png");


    public SCreeperRender(EntityRendererProvider.Context context) {
        super(context, new SCreeperModel<>(context.bakeLayer(SCreeperModel.LAYER_LOCATION)),0.5f);
    }

    protected void scale(SCreeperEntity p_114046_, PoseStack p_114047_, float p_114048_) {
        float f = p_114046_.getSwelling(p_114048_);
        float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f *= f;
        f *= f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        p_114047_.scale(f2, f3, f2);
    }

    protected float getWhiteOverlayProgress(SCreeperEntity p_114043_, float p_114044_) {
        float f = p_114043_.getSwelling(p_114044_);
        return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
    }

    public ResourceLocation getTextureLocation(SCreeperEntity creeper) {
        return creeper.isPowered()? SHINY_CREEPER_POWER :SHINY_CREEPER;
    }
}

