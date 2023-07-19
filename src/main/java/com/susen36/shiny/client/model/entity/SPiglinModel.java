package com.susen36.shiny.client.model.entity;

import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.init.EntityInit;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinArmPose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SPiglinModel<T extends Mob> extends PlayerModel<Mob> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ShinyMob.MODID, "shiny_piglin"), "main");

    private final PartPose bodyDefault;
    private final PartPose headDefault;
    private final PartPose leftArmDefault;
    private final PartPose rightArmDefault;

    public SPiglinModel(ModelPart p_170821_, boolean p_170822_) {
        super(p_170821_, p_170822_);

        this.bodyDefault = this.body.storePose();
        this.headDefault = this.head.storePose();
        this.leftArmDefault = this.leftArm.storePose();
        this.rightArmDefault = this.rightArm.storePose();
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = PlayerModel.createMesh( new CubeDeformation(-0.00F), false);
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F,  new CubeDeformation(-0.00F)), PartPose.ZERO);
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -8.0F, -4.0F, 10.0F, 8.0F, 8.0F, new CubeDeformation(-0.02F)).texOffs(31, 1).addBox(-2.0F, -4.0F, -5.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 64, 64);
    }
        public void setupAnim(Mob p_103366_, float p_103367_, float p_103368_, float p_103369_, float p_103370_, float p_103371_) {
        this.body.loadPose(this.bodyDefault);
        this.head.loadPose(this.headDefault);
        this.leftArm.loadPose(this.leftArmDefault);
        this.rightArm.loadPose(this.rightArmDefault);
        super.setupAnim(p_103366_, p_103367_, p_103368_, p_103369_, p_103370_, p_103371_);
        float f = ((float)Math.PI / 6F);
        float f1 = p_103369_ * 0.1F + p_103367_ * 0.5F;
        float f2 = 0.08F + p_103368_ * 0.4F;

        if (p_103366_ instanceof AbstractPiglin abstractpiglin) {
            PiglinArmPose piglinarmpose = abstractpiglin.getArmPose();
            if (piglinarmpose == PiglinArmPose.DANCING) {
                float f3 = p_103369_ / 60.0F;
                this.head.x = Mth.sin(f3 * 10.0F);
                this.head.y = Mth.sin(f3 * 40.0F) + 0.4F;
                this.rightArm.zRot = ((float)Math.PI / 180F) * (70.0F + Mth.cos(f3 * 40.0F) * 10.0F);
                this.leftArm.zRot = this.rightArm.zRot * -1.0F;
                this.rightArm.y = Mth.sin(f3 * 40.0F) * 0.5F + 1.5F;
                this.leftArm.y = Mth.sin(f3 * 40.0F) * 0.5F + 1.5F;
                this.body.y = Mth.sin(f3 * 40.0F) * 0.35F;
            } else if (piglinarmpose == PiglinArmPose.ATTACKING_WITH_MELEE_WEAPON && this.attackTime == 0.0F) {
                this.holdWeaponHigh(p_103366_);
            } else if (piglinarmpose == PiglinArmPose.CROSSBOW_HOLD) {
                AnimationUtils.animateCrossbowHold(this.rightArm, this.leftArm, this.head, !p_103366_.isLeftHanded());
            } else if (piglinarmpose == PiglinArmPose.CROSSBOW_CHARGE) {
                AnimationUtils.animateCrossbowCharge(this.rightArm, this.leftArm, p_103366_, !p_103366_.isLeftHanded());
            } else if (piglinarmpose == PiglinArmPose.ADMIRING_ITEM) {
                this.head.xRot = 0.5F;
                this.head.yRot = 0.0F;
                if (p_103366_.isLeftHanded()) {
                    this.rightArm.yRot = -0.5F;
                    this.rightArm.xRot = -0.9F;
                } else {
                    this.leftArm.yRot = 0.5F;
                    this.leftArm.xRot = -0.9F;
                }
            }
        } else if (p_103366_.getType() == EntityInit.SHINY_ZOMBIFIED_PIGLIN.get()) {
            AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, p_103366_.isAggressive(), this.attackTime, p_103369_);
        }

        this.leftPants.copyFrom(this.leftLeg);
        this.rightPants.copyFrom(this.rightLeg);
        this.leftSleeve.copyFrom(this.leftArm);
        this.rightSleeve.copyFrom(this.rightArm);
        this.jacket.copyFrom(this.body);
        this.hat.copyFrom(this.head);
    }

    protected void setupAttackAnimation(Mob p_103363_, float p_103364_) {
        if (this.attackTime > 0.0F && p_103363_ instanceof Piglin && ((Piglin)p_103363_).getArmPose() == PiglinArmPose.ATTACKING_WITH_MELEE_WEAPON) {
            AnimationUtils.swingWeaponDown(this.rightArm, this.leftArm, p_103363_, this.attackTime, p_103364_);
        } else {
            super.setupAttackAnimation(p_103363_, p_103364_);
        }
    }

    private void holdWeaponHigh(Mob p_103361_) {
        if (p_103361_.isLeftHanded()) {
            this.leftArm.xRot = -1.8F;
        } else {
            this.rightArm.xRot = -1.8F;
        }

    }
}