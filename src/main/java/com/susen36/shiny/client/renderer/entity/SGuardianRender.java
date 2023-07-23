package com.susen36.shiny.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.world.entity.SGuardianEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.GuardianRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SGuardianRender extends GuardianRenderer {
    public SGuardianRender(EntityRendererProvider.Context context) {
        super(context);
    }
    protected void scale(Guardian guardian, PoseStack p_114047_, float p_114048_) {
        float f = ((SGuardianEntity)guardian).getSwelling(p_114048_);
        float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f *= f;
        f *= f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        p_114047_.scale(f2, f3, f2);
    }

    protected float getWhiteOverlayProgress(Guardian guardian, float p_114044_) {
        float f = ((SGuardianEntity)guardian).getSwelling(p_114044_);
        return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
    }
    public ResourceLocation getTextureLocation(Guardian p_114827_) {
        return new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_guardian.png");
    }
}
