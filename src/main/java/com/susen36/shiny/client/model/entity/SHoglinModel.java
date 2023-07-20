package com.susen36.shiny.client.model.entity;

import com.google.common.collect.ImmutableList;
import com.susen36.shiny.ShinyMob;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.hoglin.HoglinBase;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SHoglinModel <T extends Mob & HoglinBase> extends AgeableListModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ShinyMob.MODID, "shiny_hoglin"), "main");

    private static final float DEFAULT_HEAD_X_ROT = 0.87266463F;
    private static final float ATTACK_HEAD_X_ROT_END = -0.34906584F;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart mane;

    public SHoglinModel(ModelPart p_170640_) {
        super(true, 8.0F, 6.0F, 1.9F, 2.0F, 24.0F);
        this.body = p_170640_.getChild("body");
        this.mane = this.body.getChild("mane");
        this.head = p_170640_.getChild("head");
        this.rightFrontLeg = p_170640_.getChild("right_front_leg");
        this.leftFrontLeg = p_170640_.getChild("left_front_leg");
        this.rightHindLeg = p_170640_.getChild("right_hind_leg");
        this.leftHindLeg = p_170640_.getChild("left_hind_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(1, 1).addBox(-8.0F, -7.0F, -13.0F, 16.0F, 14.0F, 26.0F), PartPose.offset(0.0F, 7.0F, 0.0F));
        partdefinition1.addOrReplaceChild("mane", CubeListBuilder.create().texOffs(90, 33).addBox(0.0F, 0.0F, -9.0F, 0.0F, 10.0F, 19.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -14.0F, -5.0F));
        PartDefinition partdefinition2 = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(61, 1).addBox(-7.0F, -3.0F, -19.0F, 14.0F, 6.0F, 19.0F)
                .texOffs(1, 13).addBox(-8.0F, -6.0F, -14.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(1, 13).addBox(6.0F, -6.0F, -14.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -12.0F, 0.87266463F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(66, 42).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 14.0F, 6.0F), PartPose.offset(-4.0F, 10.0F, -8.5F));
        partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(41, 42).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 14.0F, 6.0F), PartPose.offset(4.0F, 10.0F, -8.5F));
        partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(21, 45).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 11.0F, 5.0F), PartPose.offset(-5.0F, 13.0F, 10.0F));
        partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(0, 45).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 11.0F, 5.0F), PartPose.offset(5.0F, 13.0F, 10.0F));
        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head);
    }

    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.rightFrontLeg, this.leftFrontLeg, this.rightHindLeg, this.leftHindLeg);
    }

    public void setupAnim(T p_102744_, float p_102745_, float p_102746_, float p_102747_, float p_102748_, float p_102749_) {

        this.head.yRot = p_102748_ * 0.017453292F;
        int i = ((HoglinBase)p_102744_).getAttackAnimationRemainingTicks();
        float f = 1.0F - (float)Mth.abs(10 - 2 * i) / 10.0F;
        this.head.xRot = Mth.lerp(f, 0.87266463F, -0.34906584F);
        if (p_102744_.isBaby()) {
            this.head.y = Mth.lerp(f, 2.0F, 5.0F);
            this.mane.z = -3.0F;
        } else {
            this.head.y = 2.0F;
            this.mane.z = -7.0F;
        }

        float f1 = 1.2F;
        this.rightFrontLeg.xRot = Mth.cos(p_102745_) * 1.2F * p_102746_;
        this.leftFrontLeg.xRot = Mth.cos(p_102745_ + 3.1415927F) * 1.2F * p_102746_;
        this.rightHindLeg.xRot = this.leftFrontLeg.xRot;
        this.leftHindLeg.xRot = this.rightFrontLeg.xRot;
    }
}
