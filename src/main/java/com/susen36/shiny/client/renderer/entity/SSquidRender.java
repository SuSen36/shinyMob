package com.susen36.shiny.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.world.entity.SSquidEntity;
import net.minecraft.client.model.SquidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SSquidRender extends MobRenderer<SSquidEntity, SquidModel<SSquidEntity>> {

    public SSquidRender(EntityRendererProvider.Context context) {
         super(context, new SquidModel(context.bakeLayer(ModelLayers.SQUID)), 0.55F);
    }
    protected void setupRotations(SSquidEntity p_116035_, PoseStack p_116036_, float p_116037_, float p_116038_, float p_116039_) {
        float f = Mth.lerp(p_116039_, p_116035_.xBodyRotO, p_116035_.xBodyRot);
        float f1 = Mth.lerp(p_116039_, p_116035_.zBodyRotO, p_116035_.zBodyRot);
        p_116036_.translate(0.0F, 0.45F, 0.0F);
        p_116036_.mulPose(Axis.YP.rotationDegrees(180.0F - p_116038_));
        p_116036_.mulPose(Axis.XP.rotationDegrees(f));
        p_116036_.mulPose(Axis.YP.rotationDegrees(f1));
    }

    protected float getBob(SSquidEntity p_116032_, float p_116033_) {
        return Mth.lerp(p_116033_*0.8f, p_116032_.oldTentacleAngle, p_116032_.tentacleAngle);
    }
    protected void scale(SSquidEntity squid, PoseStack poseStack, float p_114759_) {
        poseStack.scale(0.5F, 0.5F, 0.5F);
    }
    public ResourceLocation getTextureLocation(SSquidEntity squid) {
        return new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_squid.png");
    }
}
