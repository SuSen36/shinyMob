package com.susen36.shiny.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.world.entity.STropicalFishEntity;
import net.minecraft.client.model.ColorableHierarchicalModel;
import net.minecraft.client.model.TropicalFishModelA;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class STropicalFishRender extends MobRenderer<STropicalFishEntity, ColorableHierarchicalModel<STropicalFishEntity>> {

    public STropicalFishRender(EntityRendererProvider.Context p_174428_) {
        super(p_174428_, new TropicalFishModelA<>(p_174428_.bakeLayer(ModelLayers.TROPICAL_FISH_SMALL)), 0.15F);
    }

    protected void setupRotations(STropicalFishEntity p_116226_, PoseStack p_116227_, float p_116228_, float p_116229_, float p_116230_) {
        super.setupRotations(p_116226_, p_116227_, p_116228_, p_116229_, p_116230_);
        float f = 4.3F * Mth.sin(0.6F * p_116228_);
        p_116227_.mulPose(Axis.YP.rotationDegrees(f));
        if (!p_116226_.isInWater()) {
            p_116227_.translate(0.2F, 0.1F, 0.0F);
            p_116227_.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }
    }
    public ResourceLocation getTextureLocation(STropicalFishEntity p_116217_) {
        return new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_tropical_fish.png");
    }
}
