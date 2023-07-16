package com.susen36.shiny.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.client.renderer.layer.CarriedPumpkinLayer;
import com.susen36.shiny.client.renderer.layer.ShinyEnderEyesLayer;
import com.susen36.shiny.world.entity.SEnderManEntity;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SEnderManRender extends MobRenderer<SEnderManEntity,EndermanModel<SEnderManEntity>> {

    public SEnderManRender(EntityRendererProvider.Context context) {
        super(context, new EndermanModel(context.bakeLayer(ModelLayers.ENDERMAN)), 0.7F);
        this.addLayer(new ShinyEnderEyesLayer<>(this));
        this.addLayer(new CarriedPumpkinLayer(this, context.getBlockRenderDispatcher()));
    }
    private final RandomSource random = RandomSource.create();
    public void render(SEnderManEntity enderMan, float p_114340_, float p_114341_, PoseStack p_114342_, MultiBufferSource p_114343_, int p_114344_) {
        EndermanModel<EnderMan> endermanmodel = (EndermanModel)this.getModel();
        endermanmodel.carrying = true;
        endermanmodel.creepy = enderMan.isCreepy();
        super.render(enderMan, p_114340_, p_114341_, p_114342_, p_114343_, p_114344_);
    }

    public Vec3 getRenderOffset(SEnderManEntity p_114336_, float p_114337_) {
        if (p_114336_.isCreepy()) {
            return new Vec3(this.random.nextGaussian() * 0.02, 0.0, this.random.nextGaussian() * 0.02);
        } else {
            return super.getRenderOffset(p_114336_, p_114337_);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(SEnderManEntity p_114482_) {
        return new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_enderman.png");
    }
}