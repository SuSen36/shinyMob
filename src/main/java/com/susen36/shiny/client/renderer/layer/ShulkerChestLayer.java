package com.susen36.shiny.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ShulkerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShulkerChestLayer  extends RenderLayer<Shulker, ShulkerModel<Shulker>> {
    private final ItemRenderer itemRenderer;
    public ShulkerChestLayer(RenderLayerParent<Shulker, ShulkerModel<Shulker>> p_117432_,ItemRenderer p_234873_) {
        super(p_117432_);

        this.itemRenderer = p_234873_;
    }

    public void render(PoseStack poseStack, MultiBufferSource p_117495_, int p_117496_, Shulker p_117497_, float p_117498_, float p_117499_, float p_117500_, float p_117501_, float p_117502_, float p_117503_) {
            boolean flag = Minecraft.getInstance().shouldEntityAppearGlowing(p_117497_) && p_117497_.isInvisible();
            if (!p_117497_.isInvisible() || flag) {
                poseStack.pushPose();
                this.getParentModel().getHead().translateAndRotate(poseStack);
                float f = 0.625F;
                poseStack.translate(0.0F, 0.15F, 0.0F);
                poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
                poseStack.scale(0.5F, -0.5F, -0.5F);
                ItemStack itemstack = new ItemStack(Blocks.CHEST);

                this.itemRenderer.renderStatic(p_117497_, itemstack, ItemDisplayContext.HEAD, false, poseStack, p_117495_, p_117497_.level(), p_117496_, LivingEntityRenderer.getOverlayCoords(p_117497_, 0.0F), p_117497_.getId());
                }

                poseStack.popPose();
            }
    }

