package com.susen36.shiny.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.susen36.shiny.client.renderer.layer.ZombiePlayerLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class SZombieRender extends ZombieRenderer {

    public SZombieRender(EntityRendererProvider.Context context) {
        super(context);
        this.addLayer(new ZombiePlayerLayer<>(this));

    }
    protected void renderNameTag(Zombie entity, Component p_117809_, PoseStack p_117810_, MultiBufferSource p_117811_, int p_117812_) {
        double d0 = this.entityRenderDispatcher.distanceToSqr(entity);

        if (d0 < 100.0) {
            super.renderNameTag(entity, p_117809_, p_117810_, p_117811_, p_117812_);
        }
    }
    @Override
    public void scale(@NotNull Zombie entitylivingbaseIn, PoseStack stack, float partialTickTime) {
        float scale = 0.9375F;
        stack.scale(scale, scale, scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie entity) {
        Minecraft mc = Minecraft.getInstance();
        ResourceLocation texture = DefaultPlayerSkin.getDefaultSkin();

        if (mc.getCameraEntity() instanceof AbstractClientPlayer client) {
            texture = client.getSkinTextureLocation();
        }

        return texture;
    }
}
