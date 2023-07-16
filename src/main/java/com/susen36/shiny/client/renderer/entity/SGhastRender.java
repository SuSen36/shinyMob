package com.susen36.shiny.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.susen36.shiny.client.renderer.layer.GhastMushroomLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.GhastRenderer;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SGhastRender extends GhastRenderer {
    public SGhastRender(EntityRendererProvider.Context context) {
        super(context);
        this.addLayer(new GhastMushroomLayer<>(this));
        shadowRadius=0.2f;
    }

    protected void scale(Ghast ghast, PoseStack poseStack, float p_114759_) {
        poseStack.scale(0.5F, 0.5F, 0.5F);
    }
}
