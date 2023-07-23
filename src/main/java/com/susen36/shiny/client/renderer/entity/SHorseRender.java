package com.susen36.shiny.client.renderer.entity;

import com.google.common.collect.Maps;
import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.client.renderer.layer.HorseLeatherLayer;
import com.susen36.shiny.client.renderer.layer.ShinyHorseEyesLayer;
import com.susen36.shiny.world.entity.SHorseEntity;
import net.minecraft.Util;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class SHorseRender extends MobRenderer<SHorseEntity, HorseModel<SHorseEntity>> {
    private static final Map<SHorseEntity.ArmorType, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (p_115516_) -> {
        p_115516_.put(SHorseEntity.ArmorType.LEATHER, new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_horse/leather_armor.png"));
        p_115516_.put(SHorseEntity.ArmorType.IRON, new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_horse/iron_armor.png"));
        p_115516_.put(SHorseEntity.ArmorType.GOLD, new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_horse/gold_armor.png"));
        p_115516_.put(SHorseEntity.ArmorType.DIAMOND, new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_horse/diamond_armor.png"));
    });
    public SHorseRender(EntityRendererProvider.Context context) {
        super(context, new HorseModel<>(context.bakeLayer(ModelLayers.HORSE)), 0.7f);
        this.addLayer(new HorseLeatherLayer(this, context.getModelSet()));
        this.addLayer(new ShinyHorseEyesLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(SHorseEntity horse) {
        return TEXTURES.get(horse.getVariant());
    }
}
