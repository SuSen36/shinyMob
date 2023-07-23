package com.susen36.shiny.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.susen36.shiny.world.entity.SHorseEntity;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HorseLeatherLayer extends RenderLayer<SHorseEntity, HorseModel<SHorseEntity>> {
    private final HorseModel<SHorseEntity> model;

    public HorseLeatherLayer(RenderLayerParent<SHorseEntity, HorseModel<SHorseEntity>> p_174496_, EntityModelSet p_174497_) {
        super(p_174496_);
        this.model = new HorseModel<>(p_174497_.bakeLayer(ModelLayers.HORSE_ARMOR));
    }

    public void render(PoseStack p_117032_, MultiBufferSource p_117033_, int p_117034_, SHorseEntity horse, float p_117036_, float p_117037_, float p_117038_, float p_117039_, float p_117040_, float p_117041_) {
        SHorseEntity.ArmorType type = horse.getVariant();
        if ( type == SHorseEntity.ArmorType.LEATHER) {
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(horse, p_117036_, p_117037_, p_117038_);
            this.model.setupAnim(horse, p_117036_, p_117037_, p_117039_, p_117040_, p_117041_);
            float f;
            float f1;
            float f2;
            if (horse.hasCustomName() && "jeb_".equals(horse.getName().getString())) {
                int i1 = 25;
                int i = horse.tickCount / 25 + horse.getId();
                int j = DyeColor.values().length;
                int k = i % j;
                int l = (i + 1) % j;
                float f3 = ((float)(horse.tickCount % 25) + p_117038_) / 25.0F;
                float[] afloat1 = Sheep.getColorArray(DyeColor.byId(k));
                float[] afloat2 = Sheep.getColorArray(DyeColor.byId(l));
                f = afloat1[0] * (1.0F - f3) + afloat2[0] * f3;
                f1 = afloat1[1] * (1.0F - f3) + afloat2[1] * f3;
                f2 = afloat1[2] * (1.0F - f3) + afloat2[2] * f3;
            } else {
                float[] afloat = Sheep.getColorArray(horse.getColor());
                f = afloat[0];
                f1 = afloat[1];
                f2 = afloat[2];
            }

            VertexConsumer vertexconsumer = p_117033_.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(horse)));
            this.model.renderToBuffer(p_117032_, vertexconsumer, p_117034_, OverlayTexture.NO_OVERLAY, f, f1, f2, 1.0F);
        }
    }
}
