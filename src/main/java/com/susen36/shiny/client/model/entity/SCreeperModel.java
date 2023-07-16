package com.susen36.shiny.client.model.entity;

import com.susen36.shiny.ShinyMob;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SCreeperModel<T extends Entity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ShinyMob.MODID, "shiny_creeper"), "main");
    private final ModelPart root;
    private final ModelPart left_hind_leg;
    private final ModelPart right_hind_leg;
    private final ModelPart left_front_leg;
    private final ModelPart right_front_leg;
    private final ModelPart body;

	public SCreeperModel(ModelPart root) {
        this.root = root.getChild("root");
        this.left_hind_leg = this.root.getChild("left_hind_leg");
        this.right_hind_leg = this.root.getChild("right_hind_leg");
        this.body = this.root.getChild("body");
        this.left_front_leg = this.root.getChild("left_front_leg");
        this.right_front_leg = this.root.getChild("right_front_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 14).addBox(-2.0F, -18.0F, -2.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(40, 25).addBox(-2.0F, -20.0F, -2.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(1.0F))
                .texOffs(40, 24).addBox(-2.0F, -23.0F, -2.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(40, 22).addBox(-2.0F, -26.0F, -2.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(-1.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_front_leg = root.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(1, 17).addBox(0.0F, -2.0F, 1.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -6.0F, -4.0F));

        PartDefinition left_front_leg = root.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(1, 17).addBox(-1.0F, -2.0F, 1.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -6.0F, -4.0F));

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(1, 1).addBox(-3.0F, 1.0F, -4.7F, 8.0F, 8.0F, 7.0F, new CubeDeformation(-2.0F)), PartPose.offset(0.0F, -18.0F, 0.0F));

        PartDefinition right_hind_leg = root.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(1, 17).addBox(0.0F, -2.0F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -6.0F, 4.0F));

        PartDefinition left_hind_leg = root.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(1, 17).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -6.0F, 4.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    public ModelPart root() {
        return this.root;
    }

    public void setupAnim(T p_102463_, float p_102464_, float p_102465_, float p_102466_, float p_102467_, float p_102468_) {
        this.right_hind_leg.xRot = Mth.cos(p_102464_ * 0.6662F) * 1.4F * p_102465_;
        this.left_hind_leg.xRot = Mth.cos(p_102464_ * 0.6662F + 3.1415927F) * 1.4F * p_102465_;
        this.right_front_leg.xRot = Mth.cos(p_102464_ * 0.6662F + 3.1415927F) * 1.4F * p_102465_;
        this.left_front_leg.xRot = Mth.cos(p_102464_ * 0.6662F) * 1.4F * p_102465_; }
}

