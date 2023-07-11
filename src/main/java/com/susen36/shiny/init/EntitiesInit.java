package com.susen36.shiny.init;

import com.susen36.shiny.world.entity.IdEntity;
import com.susen36.shiny.world.entity.shiny.ShinyEnderManEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.susen36.shiny.ShinyMob.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntitiesInit {
    public static final DeferredRegister<EntityType<?>> ENTITIES  = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    //Shiny Player
    public static final RegistryObject<EntityType<IdEntity>> ID = ENTITIES.register("id",
            () -> EntityType.Builder.of(IdEntity::new, MobCategory.CREATURE)
                    .sized(0.6F, 1.8F)
                    .clientTrackingRange(10)
                    .build("id"));
    //Shiny

    public static final RegistryObject<EntityType<ShinyEnderManEntity>> SHINY_ENDERMAN = ENTITIES.register("shiny_enderman",
            () -> EntityType.Builder.of(ShinyEnderManEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 2.9F).clientTrackingRange(8)
                    .fireImmune()
                    .build("shiny_enderman"));





    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(EntitiesInit.ID.get(), IdEntity.createAttributes().build());
        event.put(EntitiesInit.SHINY_ENDERMAN.get(),ShinyEnderManEntity.createAttributes().build());
    }

}
