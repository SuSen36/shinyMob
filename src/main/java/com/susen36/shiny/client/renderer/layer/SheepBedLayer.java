package com.susen36.shiny.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.susen36.shiny.world.entity.SSheepEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.ModelData;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class SheepBedLayer<T extends SSheepEntity> extends RenderLayer<SSheepEntity, SheepModel<SSheepEntity>>{

	private final BlockRenderDispatcher blockRenderer;

	public SheepBedLayer(RenderLayerParent<SSheepEntity, SheepModel<SSheepEntity>> model, BlockRenderDispatcher blockRenderer) {
		super(model);
		this.blockRenderer = blockRenderer;
	}
	public void render(@Nonnull PoseStack transform, @Nonnull MultiBufferSource buffer, int uv2, SSheepEntity entity, float f1, float f2, float ticks, float f3, float f4, float xRot) {
		if (!entity.isInvisible()&&!entity.isBaby()) {
			if(entity.getColor()== DyeColor.WHITE){
			renderColoredCutoutModel(this.getParentModel(), Blocks.WHITE_BED.defaultBlockState(), transform, buffer, uv2);
		}else if(entity.getColor()== DyeColor.BLACK){
				renderColoredCutoutModel(this.getParentModel(), Blocks.BLACK_BED.defaultBlockState(), transform, buffer, uv2);
			}else if(entity.getColor()== DyeColor.BLUE){
				renderColoredCutoutModel(this.getParentModel(), Blocks.BLUE_BED.defaultBlockState(), transform, buffer, uv2);
			}else if(entity.getColor()== DyeColor.BROWN){
				renderColoredCutoutModel(this.getParentModel(), Blocks.BROWN_BED.defaultBlockState(), transform, buffer, uv2);
			}else if(entity.getColor()== DyeColor.CYAN){
				renderColoredCutoutModel(this.getParentModel(), Blocks.CYAN_BED.defaultBlockState(), transform, buffer, uv2);
			}else if(entity.getColor()== DyeColor.GRAY){
				renderColoredCutoutModel(this.getParentModel(), Blocks.GRAY_BED.defaultBlockState(), transform, buffer, uv2);
			}else if(entity.getColor()== DyeColor.GREEN){
				renderColoredCutoutModel(this.getParentModel(), Blocks.GREEN_BED.defaultBlockState(), transform, buffer, uv2);
			}else if(entity.getColor()== DyeColor.LIGHT_BLUE){
				renderColoredCutoutModel(this.getParentModel(), Blocks.LIGHT_BLUE_BED.defaultBlockState(), transform, buffer, uv2);
			}else if(entity.getColor()== DyeColor.LIGHT_GRAY){
				renderColoredCutoutModel(this.getParentModel(), Blocks.LIGHT_GRAY_BED.defaultBlockState(), transform, buffer, uv2);
			}else if(entity.getColor()== DyeColor.LIME){
				renderColoredCutoutModel(this.getParentModel(), Blocks.LIME_BED.defaultBlockState(), transform, buffer, uv2);
			}else if(entity.getColor()== DyeColor.MAGENTA){
				renderColoredCutoutModel(this.getParentModel(), Blocks.MAGENTA_BED.defaultBlockState(), transform, buffer, uv2);
			}else if(entity.getColor()== DyeColor.ORANGE){
				renderColoredCutoutModel(this.getParentModel(), Blocks.ORANGE_BED.defaultBlockState(), transform, buffer, uv2);
			}else if(entity.getColor()== DyeColor.PINK){
				renderColoredCutoutModel(this.getParentModel(), Blocks.PINK_BED.defaultBlockState(), transform, buffer, uv2);
			}else if(entity.getColor()== DyeColor.PURPLE){
				renderColoredCutoutModel(this.getParentModel(), Blocks.PURPLE_BED.defaultBlockState(), transform, buffer, uv2);
			}else if(entity.getColor()== DyeColor.RED){
				renderColoredCutoutModel(this.getParentModel(), Blocks.RED_BED.defaultBlockState(), transform, buffer, uv2);
			}else if(entity.getColor()== DyeColor.YELLOW){
				renderColoredCutoutModel(this.getParentModel(), Blocks.YELLOW_BED.defaultBlockState(), transform, buffer, uv2);
			}
		}
	}
	@SuppressWarnings("ConstantConditions")
	protected static void renderColoredCutoutModel(SheepModel model, BlockState technicalBlock, PoseStack transform, MultiBufferSource buffer, int uv2) {
		transform.pushPose();
		transform.mulPose(Axis.XP.rotationDegrees(180.0F));
		transform.translate(-0.5D, -0.5D, -0.4D);
		Minecraft.getInstance().getBlockRenderer().renderSingleBlock(technicalBlock, transform, buffer, uv2, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, null);
		transform.popPose();
	}
}
