package com.susen36.shiny.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GloemPumpkinLayer<T extends IronGolem> extends RenderLayer<IronGolem, IronGolemModel<IronGolem>> {
    private final BlockRenderDispatcher blockRenderer;

    public GloemPumpkinLayer(RenderLayerParent<IronGolem, IronGolemModel<IronGolem>> model, BlockRenderDispatcher p_234815_) {
        super(model);
        this.blockRenderer = p_234815_;
    }
    public void render(PoseStack p_116639_, MultiBufferSource bufferSource, int p_116641_, IronGolem golem, float p_116643_, float p_116644_, float p_116645_, float p_116646_, float p_116647_, float p_116648_) {

        BlockState blockstate = Blocks.CARVED_PUMPKIN.defaultBlockState();
        p_116639_.pushPose();
        p_116639_.translate(0.0F, 0.6875F, -0.75F);
        //p_116639_.mulPose(Axis.XP.rotationDegrees(20.0F));
        //p_116639_.mulPose(Axis.YP.rotationDegrees(45.0F));
        p_116639_.translate(0.4F, -1.2F, 0.15F);
        p_116639_.scale(-0.8F, -0.8F, 0.8F);
        //p_116639_.mulPose(Axis.YP.rotationDegrees(90.0F));
        this.blockRenderer.renderSingleBlock(blockstate, p_116639_, bufferSource, p_116641_, OverlayTexture.NO_OVERLAY);
        p_116639_.popPose();
    }
}
