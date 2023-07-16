package com.susen36.shiny.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.susen36.shiny.ShinyMob;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombiePlayerLayer<T extends Entity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    public ZombiePlayerLayer(RenderLayerParent<T, M> p_116981_) {
        super(p_116981_);
    }

    public void render(PoseStack p_116983_, MultiBufferSource p_116984_, int p_116985_, T p_116986_, float p_116987_, float p_116988_, float p_116989_, float p_116990_, float p_116991_, float p_116992_) {
        VertexConsumer vertexconsumer = p_116984_.getBuffer(this.renderType());
        this.getParentModel().renderToBuffer(p_116983_, vertexconsumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    private static final RenderType SHINY_ZOMBIE = RenderType.entityTranslucent(new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_zombie_layer.png"));

    public RenderType renderType() {
        return SHINY_ZOMBIE;
    }
}
