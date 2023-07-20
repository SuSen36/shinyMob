package com.susen36.shiny.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.client.model.entity.SPuffFishModel;
import com.susen36.shiny.client.model.entity.SPuffFishModelM;
import com.susen36.shiny.world.entity.SPufferFishEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SPufferFishRender extends MobRenderer<SPufferFishEntity, EntityModel<SPufferFishEntity>> {
    private static final ResourceLocation PUFFER_LOCATION = new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_pufferfish.png");


    private final EntityModel<SPufferFishEntity> small;
    private final EntityModel<SPufferFishEntity> mid=this.getModel();


    public SPufferFishRender(EntityRendererProvider.Context context) {
        super(context, new SPuffFishModelM<>(context.bakeLayer(SPuffFishModelM.LAYER_LOCATION)), 0.2F);
        this.small = new SPuffFishModel<>(context.bakeLayer(SPuffFishModel.LAYER_LOCATION));
    }

    public ResourceLocation getTextureLocation(SPufferFishEntity p_115775_) {
        return PUFFER_LOCATION;
    }

    public void render(SPufferFishEntity pufferFish, float p_115778_, float p_115779_, PoseStack p_115780_, MultiBufferSource p_115781_, int p_115782_) {
        Boolean i = pufferFish.getPuffState();
            if (i) {
                this.model = this.mid;
            } else {
                this.model = this.small;
            }
        this.shadowRadius = 0.15F * (i?1:3);
        super.render(pufferFish, p_115778_, p_115779_, p_115780_, p_115781_, p_115782_);
    }
    protected void setupRotations(SPufferFishEntity fishEntity, PoseStack p_115785_, float p_115786_, float p_115787_, float p_115788_) {
        Boolean i = fishEntity.getPuffState();
        if (i) {
            p_115785_.translate(0.0F, Mth.cos(p_115786_ * 0.05F) * 0.08F, 0.0F);
        }
        super.setupRotations(fishEntity, p_115785_, p_115786_, p_115787_, p_115788_);
    }
}