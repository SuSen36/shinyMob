package com.susen36.shiny.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.world.entity.SOcelotEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.OcelotRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class SOcelotRender extends OcelotRenderer {
    public SOcelotRender(EntityRendererProvider.Context context) {
        super(context);
    }
    protected void scale(@NotNull Ocelot ocelot, PoseStack p_114047_, float p_114048_) {
        float f = ((SOcelotEntity)ocelot).getSwelling(p_114048_);
        float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f *= f;
        f *= f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        p_114047_.scale(f2, f3, f2);
    }

    protected float getWhiteOverlayProgress(Ocelot ocelot, float p_114044_) {
        float f = ((SOcelotEntity)ocelot).getSwelling(p_114044_);
        return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
    }
    public ResourceLocation getTextureLocation(Ocelot p_115524_) {
        return new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_ocelot.png");
    }

}
