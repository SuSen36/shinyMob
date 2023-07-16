package com.susen36.shiny.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.susen36.shiny.client.renderer.entity.SGhastRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.GhastModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.ModelData;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class GhastMushroomLayer<T extends Ghast> extends RenderLayer<Ghast, GhastModel<Ghast>> {


    public GhastMushroomLayer(SGhastRender model) {
        super(model);
    }


    public void render(@Nonnull PoseStack transform, @Nonnull MultiBufferSource buffer, int uv2, Ghast entity, float f1, float f2, float ticks, float f3, float f4, float xRot) {
        if (!entity.isInvisible()) {
            renderColoredCutoutModel(this.getParentModel(), Blocks.BROWN_MUSHROOM.defaultBlockState(), transform, buffer, uv2);
        }
    }
    @SuppressWarnings("ConstantConditions")
    protected static void renderColoredCutoutModel(GhastModel model, BlockState technicalBlock, PoseStack transform, MultiBufferSource buffer, int uv2) {

        transform.pushPose();
        transform.mulPose(Axis.XP.rotationDegrees(180.0F));
        transform.translate(-0.5D, -0.7D, -0.5D);
        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(technicalBlock, transform, buffer, uv2, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, null);
        transform.popPose();
    }

}
