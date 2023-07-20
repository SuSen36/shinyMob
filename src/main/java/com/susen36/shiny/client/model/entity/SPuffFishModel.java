package com.susen36.shiny.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.world.entity.SPufferFishEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SPuffFishModel<T extends SPufferFishEntity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ShinyMob.MODID, "shiny_pufferfish"), "main");
    private final ModelPart body_1;
    private final ModelPart leftFrontLeg_1;
    private final ModelPart leftLeg_1;
    private final ModelPart rightLeg_1;
    private final ModelPart rightFrontLeg_1;

    public SPuffFishModel(ModelPart root) {
        this.body_1 = root.getChild("body_1");
        this.leftFrontLeg_1 = root.getChild("leftFrontLeg_1");
        this.leftLeg_1 = root.getChild("leftLeg_1");
        this.rightLeg_1 = root.getChild("rightLeg_1");
        this.rightFrontLeg_1 = root.getChild("rightFrontLeg_1");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();


        PartDefinition body_1 = partdefinition.addOrReplaceChild("body_1", CubeListBuilder.create().texOffs(1, 1).addBox(-2.5F, -2.5F, -3.5F, 5.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition tail_1 = body_1.addOrReplaceChild("tail_1", CubeListBuilder.create().texOffs(4, 37).addBox(-1.0F, -2.0F, -2.2F, 0.0F, 4.0F, 4.0F, new CubeDeformation(-1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.1F, 0.0873F, 0.0F, 0.0F));

        PartDefinition spikes_6 = body_1.addOrReplaceChild("spikes_6", CubeListBuilder.create().texOffs(3, 0).addBox(0.0F, -2.5F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4F, 0.0F, -3.4F, 0.0F, 0.5236F, 0.0F));

        PartDefinition spikes_7 = body_1.addOrReplaceChild("spikes_7", CubeListBuilder.create().texOffs(0, 50).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.4F, -3.4F, -0.5236F, 0.0F, 0.0F));

        PartDefinition spikes_4 = body_1.addOrReplaceChild("spikes_4", CubeListBuilder.create().texOffs(0, 50).addBox(-2.5F, -1.0F, 0.0F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.4F, -3.4F, 0.5236F, 0.0F, 0.0F));

        PartDefinition collarNormal = body_1.addOrReplaceChild("collarNormal", CubeListBuilder.create().texOffs(14, 14).addBox(-3.0F, -3.0F, -0.5F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));

        PartDefinition spikes_5 = body_1.addOrReplaceChild("spikes_5", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.5F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4F, 0.0F, -3.4F, 0.0F, -0.5236F, 0.0F));

        PartDefinition leftFrontLeg_1 = partdefinition.addOrReplaceChild("leftFrontLeg_1", CubeListBuilder.create().texOffs(17, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 21.0F, -1.0F, -0.2618F, 0.0F, -0.2618F));

        PartDefinition leftLeg_1 = partdefinition.addOrReplaceChild("leftLeg_1", CubeListBuilder.create().texOffs(33, 0).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8F, 22.0F, 2.5F, 0.3491F, 0.0F, -0.2618F));

        PartDefinition rightLeg_1 = partdefinition.addOrReplaceChild("rightLeg_1", CubeListBuilder.create().texOffs(41, 0).mirror().addBox(-1.0F, -1.0F, -2.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.8F, 22.0F, 2.5F, 0.3491F, 0.0F, 0.2618F));

        PartDefinition rightFrontLeg_1 = partdefinition.addOrReplaceChild("rightFrontLeg_1", CubeListBuilder.create().texOffs(25, 0).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 21.0F, -1.0F, -0.2618F, 0.0F, 0.2618F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.leftFrontLeg_1.xRot = Mth.cos(limbSwing * 1.47f) * 0.75f * limbSwingAmount;
        this.rightFrontLeg_1.xRot = Mth.cos(limbSwing * 1.47f + (float) Math.PI) * 0.75f * limbSwingAmount;
        this.leftLeg_1.xRot = Mth.cos(limbSwing * 1.47f + (float) Math.PI) * 0.75f * limbSwingAmount;
        this.rightLeg_1.xRot = Mth.cos(limbSwing * 1.47f) * 0.75f * limbSwingAmount;
    }
    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body_1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftFrontLeg_1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftLeg_1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightLeg_1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightFrontLeg_1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
       }
}
