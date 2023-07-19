package com.susen36.shiny.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.susen36.shiny.world.entity.STurtleEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.TurtleModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TurtleMapLayer<T extends STurtleEntity> extends RenderLayer<STurtleEntity, TurtleModel<STurtleEntity>> {
    private final ItemRenderer itemRenderer;
    public TurtleMapLayer(RenderLayerParent<STurtleEntity, TurtleModel<STurtleEntity>> p_117432_, ItemRenderer itemRender) {
        super(p_117432_);
        this.itemRenderer = itemRender;
    }

    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int p_115081_, STurtleEntity turtle, float p_117498_, float p_117499_, float p_117500_, float p_117501_, float p_117502_, float p_117503_) {
        poseStack.pushPose();

        Vec3 vec3 = Vec3.ZERO;
        poseStack.translate(-vec3.x()-0.62, -vec3.y()+0.25, -vec3.z()+1.420);

        ItemStack itemstack = turtle.getItem();

        this.getParentModel().body.translateAndRotate(poseStack);

        if (!itemstack.isEmpty()) {
            MapItemSavedData mapitemsaveddata = MapItem.getSavedData(itemstack, turtle.level());

            if (mapitemsaveddata != null) {
                poseStack.mulPose(Axis.XP.rotationDegrees(-180.0F));

                float f = 0.0095F;

                poseStack.scale(f, f, f);

                Minecraft.getInstance().gameRenderer.getMapRenderer().render(poseStack, multiBufferSource, turtle.getFramedMapId().getAsInt(), mapitemsaveddata, true, p_115081_);

            } else {
                poseStack.scale(0.5F, 0.5F, 0.5F);
                this.itemRenderer.renderStatic(itemstack, ItemDisplayContext.HEAD, p_115081_, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, turtle.level(), turtle.getId());
            }
        }

        poseStack.popPose();
    }
}

