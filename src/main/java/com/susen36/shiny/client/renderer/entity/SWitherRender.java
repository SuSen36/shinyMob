package com.susen36.shiny.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.client.model.entity.SWitherModel;
import com.susen36.shiny.client.renderer.layer.WitherPowerLayer;
import com.susen36.shiny.world.entity.SWitherEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SWitherRender  extends MobRenderer<SWitherEntity, SWitherModel<SWitherEntity>> {
    private static final ResourceLocation WITHER_LOCATION = new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_wither.png");

    public SWitherRender(EntityRendererProvider.Context context) {
        super(context, new SWitherModel<>(context.bakeLayer(SWitherModel.LAYER_LOCATION)),1.0F);
        this.addLayer(new WitherPowerLayer(this, context.getModelSet()));
    }


    protected int getBlockLightLevel(SWitherEntity p_116443_, BlockPos p_116444_) {
        return 6;
    }

    public ResourceLocation getTextureLocation(SWitherEntity wither) {
        return WITHER_LOCATION;
    }

    protected void scale(SWitherEntity wither, PoseStack p_114047_, float p_114048_) {
        float f = wither.getSwelling(p_114048_);
        float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f *= f;
        f *= f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        p_114047_.scale(f2, f3, f2);
    }

    protected float getWhiteOverlayProgress(SWitherEntity wither, float p_114044_) {
        float f = wither.getSwelling(p_114044_);
        return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
    }
}
