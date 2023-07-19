package com.susen36.shiny.client.renderer.layer;

import com.susen36.shiny.client.model.entity.SWitherModel;
import com.susen36.shiny.world.entity.SWitherEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitherPowerLayer extends EnergySwirlLayer<SWitherEntity, SWitherModel<SWitherEntity>> {
    private static final ResourceLocation WITHER_ARMOR_LOCATION = new ResourceLocation("textures/entity/wither/wither_armor.png");
    private final SWitherModel<SWitherEntity> model;

    public WitherPowerLayer(RenderLayerParent<SWitherEntity, SWitherModel<SWitherEntity>> p_174554_, EntityModelSet p_174555_) {
        super(p_174554_);
        this.model = new SWitherModel<>(p_174555_.bakeLayer(SWitherModel.LAYER_LOCATION));
    }

    protected float xOffset(float p_117702_) {
        return Mth.cos(p_117702_ * 0.02F) * 3.0F;
    }

    protected ResourceLocation getTextureLocation() {
        return WITHER_ARMOR_LOCATION;
    }

    protected EntityModel<SWitherEntity> model() {
        return this.model;
    }
}