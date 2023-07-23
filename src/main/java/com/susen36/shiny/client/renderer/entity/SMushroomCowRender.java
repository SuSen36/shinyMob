package com.susen36.shiny.client.renderer.entity;

import com.google.common.collect.Maps;
import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.client.renderer.layer.MushroomCowFungusLayer;
import com.susen36.shiny.world.entity.SMushroomCowEntity;
import net.minecraft.Util;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class SMushroomCowRender extends MobRenderer<SMushroomCowEntity, CowModel<SMushroomCowEntity>> {
    private static final Map<SMushroomCowEntity.FungusType, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (p_115516_) -> {
        p_115516_.put(SMushroomCowEntity.FungusType.WART, new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_mooshroom/wart_mooshroom.png"));
        p_115516_.put(SMushroomCowEntity.FungusType.CRIMSON, new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_mooshroom/crimson_mooshroom.png"));
        p_115516_.put(SMushroomCowEntity.FungusType.WARPED, new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_mooshroom/warped_mooshroom.png"));
    });

    public SMushroomCowRender(EntityRendererProvider.Context context) {
        super(context, new CowModel<>(context.bakeLayer(ModelLayers.MOOSHROOM)), 0.7f);
        this.addLayer(new MushroomCowFungusLayer<>(this, context.getBlockRenderDispatcher()));
    }

    public ResourceLocation getTextureLocation(SMushroomCowEntity cow) {
        return TEXTURES.get(cow.getVariant());
    }

}
