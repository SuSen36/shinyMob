package com.susen36.shiny.init;


import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.client.model.entity.*;
import com.susen36.shiny.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.client.renderer.entity.StrayRenderer;
import net.minecraft.client.renderer.entity.WitherSkeletonRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = ShinyMob.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    @SubscribeEvent
    public static void onRegisterModel(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SAllayModel.LAYER_LOCATION, SAllayModel::createBodyLayer);
        event.registerLayerDefinition(SCreeperModel.LAYER_LOCATION, SCreeperModel::createBodyLayer);
        event.registerLayerDefinition(SSnowGolemModel.LAYER_LOCATION, SSnowGolemModel::createBodyLayer);
        event.registerLayerDefinition(SPiglinModel.LAYER_LOCATION, SPiglinModel::createBodyLayer);
        event.registerLayerDefinition(SWitherModel.LAYER_LOCATION, SWitherModel::createBodyLayer);
        event.registerLayerDefinition(SHoglinModel.LAYER_LOCATION, SHoglinModel::createBodyLayer);
        event.registerLayerDefinition(SPuffFishModelM.LAYER_LOCATION, SPuffFishModelM::createBodyLayer);
        event.registerLayerDefinition(SPuffFishModel.LAYER_LOCATION, SPuffFishModel::createBodyLayer);

    }
    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.SHINY_ENDERMAN.get(), SEnderManRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_SHEEP.get(), SSheepRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_ALLAY.get(), SAllayRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_CREEPER.get(), SCreeperRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_SNOW_GOLEM.get(), SSnowGolemRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_BLAZE.get(), SBlazeRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_WITHER_SKELETON.get(), WitherSkeletonRenderer::new);
        event.registerEntityRenderer(EntityInit.SHINY_GHAST.get(), SGhastRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_SKELETON.get(), SkeletonRenderer::new);
        event.registerEntityRenderer(EntityInit.SHINY_WARDEN.get(), SWardenRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_STRAY.get(), StrayRenderer::new);
        event.registerEntityRenderer(EntityInit.SHINY_HUSK.get(), SHuskRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_ZOMBIE.get(), SZombieRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_STRIDER.get(), SStriderRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_SPIDER.get(), SSpiderRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_IRON_GOLEM.get(), SIronRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_SHULKER.get(), SShulkerRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_PANDA.get(), SPandaRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_PIG.get(), SPigRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_ZOMBIFIED_PIGLIN.get(), SZombifiedPiglinRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_PIGLIN.get(), SPiglinRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_WITHER_SKELETON.get(), SWitherSkeletonRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_WITHER.get(), SWitherRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_TURTLE.get(), STurtleRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_HOGLIN.get(), SHoglinRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_ZOGLIN.get(), SZoglinRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_COD.get(), SCodRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_SALMON.get(), SSalmonRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_PUFFERFISH.get(), SPufferFishRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_SLIME.get(), SlimeRenderer::new);

    }
}
