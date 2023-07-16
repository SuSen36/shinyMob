package com.susen36.shiny.client.model.entity;

import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.world.entity.SAllayEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SAllayModel extends HierarchicalModel<SAllayEntity> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart arm;
    private final ModelPart right_wing;
    private final ModelPart left_wing;

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ShinyMob.MODID, "shiny_allay"), "main");


    public SAllayModel(ModelPart root) {
        super(RenderType::entityTranslucent);
        this.root = root.getChild("root");
        this.head = this.root.getChild("head");
        this.body = this.root.getChild("body");
        this.arm = this.body.getChild("arm");
        this.right_wing = this.body.getChild("right_wing");
        this.left_wing = this.body.getChild("left_wing");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 7.0F, -3.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(24, 3).addBox(-1.0F, 12.0F, -5.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -21.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 13).addBox(-2.0F, 14.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -21.0F, 0.0F));

        PartDefinition arm = body.addOrReplaceChild("arm", CubeListBuilder.create().texOffs(16, 20).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(16, 13).addBox(2.0F, -1.0F, -4.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(16, 13).mirror().addBox(-4.0F, -1.0F, -4.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 15.0F, 0.0F, 0.6981F, 0.0F, 0.0F));

        PartDefinition right_wing = body.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(18, 0).addBox(0.0F, 13.0F, 0.0F, 7.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)),PartPose.offset(-0.5F, 0.0F, 0.6F));

        PartDefinition left_wing = body.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(18, 0).mirror().addBox(-7.0F, 13.0F, 0.0F, 7.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.5F, 0.0F, 0.6F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }
    public ModelPart root() {
        return this.root;
    }
    public void setupAnim(SAllayEntity sAllay, float p_233326_, float p_233327_, float p_233328_, float p_233329_, float p_233330_) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        float f = p_233328_ * 20.0F * 0.017453292F + p_233326_;
        float f1 = Mth.cos(f) * 3.1415927F * 0.1F + p_233327_;
        float f3 = p_233328_ * 9.0F * 0.017453292F;
        float f4 = Math.min(p_233327_ / 0.3F, 1.0F);
        float f5 = 1.0F - f4;

        this.right_wing.yRot = -0.6053982F + f1;
        this.left_wing.yRot = 0.6053982F - f1;
        this.root.y += (float)Math.cos((double)f3) * 0.25F * f5;
    }

}
