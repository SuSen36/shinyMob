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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SPuffFishModelM<T extends SPufferFishEntity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ShinyMob.MODID, "shiny_small_pufferfish"), "main");
    private final ModelPart body;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public SPuffFishModelM(ModelPart root) {
        this.body = root.getChild("body");
        this.rightFrontLeg = root.getChild("rightFrontLeg");
        this.leftFrontLeg = root.getChild("leftFrontLeg");
        this.rightLeg = root.getChild("rightLeg");
        this.leftLeg = root.getChild("leftLeg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(20, 12).addBox(-5.0F, -5.0F, -7.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.5F, 0.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition spikes = body.addOrReplaceChild("spikes", CubeListBuilder.create().texOffs(0, 50).addBox(-5.0F, -1.0F, 0.0F, 10.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.9F, -6.9F, 0.5236F, 0.0F, 0.0F));

        PartDefinition spikes_2 = body.addOrReplaceChild("spikes_2", CubeListBuilder.create().texOffs(3, 28).addBox(0.0F, -5.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.9F, 0.0F, -6.9F, 0.0F, 0.5236F, 0.0F));

        PartDefinition spikes_3 = body.addOrReplaceChild("spikes_3", CubeListBuilder.create().texOffs(0, 50).addBox(-5.0F, 0.0F, 0.0F, 10.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.9F, -6.9F, -0.5236F, 0.0F, 0.0F));

        PartDefinition spikes_1 = body.addOrReplaceChild("spikes_1", CubeListBuilder.create().texOffs(0, 28).addBox(-1.0F, -5.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9F, 0.0F, -6.9F, 0.0F, -0.5236F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(4, 37).addBox(0.0F, -2.0F, -1.1F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 3.1F, 0.0873F, 0.0F, 0.0F));

        PartDefinition rightFrontLeg = partdefinition.addOrReplaceChild("rightFrontLeg", CubeListBuilder.create().texOffs(17, 0).mirror().addBox(-1.0F, 0.0F, -2.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 20.5F, -3.0F, -0.1745F, 0.0F, 1.0472F));

        PartDefinition leftFrontLeg = partdefinition.addOrReplaceChild("leftFrontLeg", CubeListBuilder.create().texOffs(25, 0).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 20.5F, -3.0F, -0.1745F, 0.0F, -1.0472F));

        PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(41, 0).mirror().addBox(-1.0F, 0.0F, -3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 20.5F, 3.0F, 0.1745F, 0.0F, 1.0472F));

        PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(33, 0).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 20.5F, 3.0F, 0.1745F, 0.0F, -1.0472F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightFrontLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftFrontLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}