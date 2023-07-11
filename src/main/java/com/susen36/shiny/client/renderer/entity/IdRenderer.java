package com.susen36.shiny.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.susen36.shiny.world.entity.IdEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IdRenderer<T extends IdEntity> extends HumanoidMobRenderer<T,PlayerModel<T>> {
    private final PlayerModel<T> normalModel;
    private final PlayerModel<T> slimModel;
   
    public IdRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5F);
        
        normalModel = getModel();
        slimModel = new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER_SLIM), true);
        }


    protected void renderNameTag(T entity, Component p_117809_, PoseStack p_117810_, MultiBufferSource p_117811_, int p_117812_) {
        double d0 = this.entityRenderDispatcher.distanceToSqr(entity);

        if (d0 < 100.0) {
            super.renderNameTag(entity, p_117809_, p_117810_, p_117811_, p_117812_);
        }
    }
    @Override
    public void scale(T entitylivingbaseIn, PoseStack stack, float partialTickTime) {
        float scale = 0.9375F;
        stack.scale(scale, scale, scale);
    }

    @Override
    public ResourceLocation getTextureLocation(IdEntity entity) {
        Minecraft mc = Minecraft.getInstance();
        boolean slim = false;
        ResourceLocation texture = DefaultPlayerSkin.getDefaultSkin();

        if (mc.getCameraEntity() instanceof AbstractClientPlayer client) {
            texture = client.getSkinTextureLocation();
            slim = client.getModelName().equals("slim");
        }

        model = slim ? slimModel : normalModel;
        return texture;
    }
}
