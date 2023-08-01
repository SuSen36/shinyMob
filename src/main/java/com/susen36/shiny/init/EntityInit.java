package com.susen36.shiny.init;

import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.world.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.susen36.shiny.ShinyMob.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITIES  = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    //Shiny
    public static final RegistryObject<EntityType<SEnderManEntity>> SHINY_ENDERMAN = registerEntity("shiny_enderman", EntityType.Builder.of(SEnderManEntity::new, MobCategory.MONSTER).sized(0.6F, 2.9F).clientTrackingRange(8).fireImmune(), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SSheepEntity>> SHINY_SHEEP = registerEntity("shiny_sheep", EntityType.Builder.of(SSheepEntity::new, MobCategory.CREATURE).sized(0.9F, 1.3F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SAllayEntity>> SHINY_ALLAY = registerEntity("shiny_allay", EntityType.Builder.of(SAllayEntity::new, MobCategory.MISC).sized(0.35F, 0.85F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SCreeperEntity>> SHINY_CREEPER = registerEntity("shiny_creeper", EntityType.Builder.of(SCreeperEntity::new, MobCategory.MONSTER).sized(0.6F, 1.7F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SSnowGolemEntity>> SHINY_SNOW_GOLEM = registerEntity("shiny_snow_golem", EntityType.Builder.of(SSnowGolemEntity::new, MobCategory.MISC).immuneTo(Blocks.POWDER_SNOW).sized(0.7F, 1.9F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SBlazeEntity>> SHINY_BLAZE = registerEntity("shiny_blaze", EntityType.Builder.of(SBlazeEntity::new, MobCategory.MONSTER).sized(0.6F, 1.8F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SWitherSkeletonEntity>> SHINY_WITHER_SKELETON = registerEntity("shiny_wither_skeleton", EntityType.Builder.of(SWitherSkeletonEntity::new, MobCategory.MONSTER).fireImmune().immuneTo(Blocks.WITHER_ROSE).sized(0.6F, 1.99F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SGhastEntity>> SHINY_GHAST = registerEntity("shiny_ghast", EntityType.Builder.of(SGhastEntity::new, MobCategory.MONSTER).fireImmune().sized(0.5F, 0.5F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SSkeletonEntity>> SHINY_SKELETON = registerEntity("shiny_skeleton", EntityType.Builder.of(SSkeletonEntity::new, MobCategory.MONSTER).sized(0.6F, 1.99F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SWardenEntity>> SHINY_WARDEN = registerEntity("shiny_warden", EntityType.Builder.of(SWardenEntity::new, MobCategory.MISC).sized(0.6F, 1.95F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SStrayEntity>> SHINY_STRAY = registerEntity("shiny_stray", EntityType.Builder.of(SStrayEntity::new, MobCategory.MONSTER).sized(0.6F, 1.99F).fireImmune().immuneTo(Blocks.POWDER_SNOW).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SHuskEntity>> SHINY_HUSK = registerEntity("shiny_husk", EntityType.Builder.of(SHuskEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SZombieEntity>> SHINY_ZOMBIE = registerEntity("shiny_zombie", EntityType.Builder.of(SZombieEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SStriderEntity>> SHINY_STRIDER= registerEntity("shiny_strider", EntityType.Builder.of(SStriderEntity::new, MobCategory.MONSTER).fireImmune().sized(0.9F, 1.7F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SSpiderEntity>> SHINY_SPIDER= registerEntity("shiny_spider", EntityType.Builder.of(SSpiderEntity::new, MobCategory.MONSTER).sized(1.8F, 1.15F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SIronGolemEntity>> SHINY_IRON_GOLEM= registerEntity("shiny_iron_golem", EntityType.Builder.of(SIronGolemEntity::new, MobCategory.MISC).sized(1.4F, 2.7F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SShulkerEntity>> SHINY_SHULKER= registerEntity("shiny_shulker", EntityType.Builder.of(SShulkerEntity::new, MobCategory.MISC).fireImmune().canSpawnFarFromPlayer().sized(1.0F, 1.0F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SPandaEntity>> SHINY_PANDA= registerEntity("shiny_panda", EntityType.Builder.of(SPandaEntity::new, MobCategory.CREATURE).sized(1.3F, 1.25F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SPigEntity>> SHINY_PIG= registerEntity("shiny_pig", EntityType.Builder.of(SPigEntity::new, MobCategory.CREATURE).sized(0.9F, 0.9F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SZombifiedPiglinEntity>> SHINY_ZOMBIFIED_PIGLIN= registerEntity("shiny_zombified_piglin", EntityType.Builder.of(SZombifiedPiglinEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SPiglinEntity>> SHINY_PIGLIN= registerEntity("shiny_piglin", EntityType.Builder.of(SPiglinEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SWitherEntity>> SHINY_WITHER= registerEntity("shiny_wither", EntityType.Builder.of(SWitherEntity::new, MobCategory.MONSTER).fireImmune().immuneTo(Blocks.WITHER_ROSE).sized(0.75F, 1.6F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<STurtleEntity>> SHINY_TURTLE= registerEntity("shiny_turtle", EntityType.Builder.of(STurtleEntity::new, MobCategory.CREATURE).sized(1.2F, 0.4F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SHoglinEntity>> SHINY_HOGLIN= registerEntity("shiny_hoglin", EntityType.Builder.of(SHoglinEntity::new,MobCategory.MONSTER).sized(1.3964844F, 1.4F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SZoglinEntity>> SHINY_ZOGLIN= registerEntity("shiny_zoglin", EntityType.Builder.of(SZoglinEntity::new,MobCategory.MONSTER).sized(1.3964844F, 1.4F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SCodEntity>> SHINY_COD= registerEntity("shiny_cod", EntityType.Builder.of(SCodEntity::new, MobCategory.WATER_AMBIENT).sized(0.5F, 0.3F).clientTrackingRange(4), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SSalmonEnity>> SHINY_SALMON= registerEntity("shiny_salmon", EntityType.Builder.of(SSalmonEnity::new,  MobCategory.WATER_AMBIENT).sized(0.7F, 0.4F).clientTrackingRange(4), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SPufferFishEntity>> SHINY_PUFFERFISH= registerEntity("shiny_pufferfish", EntityType.Builder.of(SPufferFishEntity::new, MobCategory.AMBIENT).sized(0.7F, 0.7F).clientTrackingRange(4), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SSlimeEntity>> SHINY_SLIME= registerEntity("shiny_slime", EntityType.Builder.of(SSlimeEntity::new, MobCategory.MONSTER).sized(2.04F, 2.04F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SMagmaCubeEntity>> SHINY_MAGMA_CUBE= registerEntity("shiny_magma_cube", EntityType.Builder.of(SMagmaCubeEntity::new, MobCategory.MONSTER).fireImmune().sized(2.04F, 2.04F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SSquidEntity>> SHINY_SQUID= registerEntity("shiny_squid", EntityType.Builder.of(SSquidEntity::new,  MobCategory.WATER_CREATURE).sized(0.85F, 0.85F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SSilverFishEntity>> SHINY_SILVERFISH= registerEntity("shiny_silverfish", EntityType.Builder.of(SSilverFishEntity::new,  MobCategory.MONSTER).sized(0.4F, 0.3F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SEndermiteEntity>> SHINY_ENDERMITE= registerEntity("shiny_endermite", EntityType.Builder.of(SEndermiteEntity::new,  MobCategory.MONSTER).sized(0.4F, 0.3F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SRabbitEntity>> SHINY_RABBIT= registerEntity("shiny_rabbit", EntityType.Builder.of(SRabbitEntity::new,  MobCategory.MONSTER).sized(0.4F, 0.5F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SGlowSquidEntity>> SHINY_GLOW_SQUID= registerEntity("shiny_glow_squid", EntityType.Builder.of(SGlowSquidEntity::new, MobCategory.UNDERGROUND_WATER_CREATURE).sized(0.8F, 0.8F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SGoatEntity>> SHINY_GOAT= registerEntity("shiny_goat", EntityType.Builder.of(SGoatEntity::new, MobCategory.CREATURE).sized(0.9F, 1.3F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SEvokerEntity>> SHINY_EVOKER= registerEntity("shiny_evoker", EntityType.Builder.of(SEvokerEntity::new,MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SVexEntity>> SHINY_VEX= registerEntity("shiny_vex", EntityType.Builder.of(SVexEntity::new,MobCategory.MONSTER).fireImmune().sized(0.4F, 0.8F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SWitchEntity>> SHINY_WITCH= registerEntity("shiny_witch", EntityType.Builder.of(SWitchEntity::new,MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SCowEntity>> SHINY_COW= registerEntity("shiny_cow", EntityType.Builder.of(SCowEntity::new,MobCategory.CREATURE).sized(0.9F, 1.4F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SMushroomCowEntity>> SHINY_MOOSHROOM= registerEntity("shiny_mooshroom", EntityType.Builder.of(SMushroomCowEntity::new,MobCategory.CREATURE).sized(0.9F, 1.4F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SWolfEntity>> SHINY_WOLF= registerEntity("shiny_wolf", EntityType.Builder.of(SWolfEntity::new,MobCategory.CREATURE).sized(1.25F, 1.25F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SOcelotEntity>> SHINY_OCELOT= registerEntity("shiny_ocelot", EntityType.Builder.of(SOcelotEntity::new,MobCategory.CREATURE).sized(0.6F, 0.7F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SCatEntity>> SHINY_CAT= registerEntity("shiny_cat", EntityType.Builder.of(SCatEntity::new,MobCategory.CREATURE).sized(0.6F, 0.7F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SHorseEntity>> SHINY_HORSE= registerEntity("shiny_horse", EntityType.Builder.of(SHorseEntity::new,MobCategory.CREATURE).sized(1.3964844F, 1.6F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SGuardianEntity>> SHINY_GUARDIAN= registerEntity("shiny_guardian", EntityType.Builder.of(SGuardianEntity::new,MobCategory.MONSTER).sized(0.85F, 0.85F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SElderGuardianEntity>> SHINY_ELDER_GUARDIAN= registerEntity("shiny_elder_guardian", EntityType.Builder.of(SElderGuardianEntity::new,MobCategory.MONSTER).sized(1.9975F, 1.9975F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SBatEntity>> SHINY_BAT= registerEntity("shiny_bat", EntityType.Builder.of(SBatEntity::new, MobCategory.AMBIENT).sized(0.5F, 0.9F).clientTrackingRange(5), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SPolarBearEntity>> SHINY_POLAR_BEAR= registerEntity("shiny_polar_bear", EntityType.Builder.of(SPolarBearEntity::new, MobCategory.CREATURE).sized(1.4F, 1.4F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<STropicalFishEntity>> SHINY_TROPICAL_FISH= registerEntity("shiny_tropical_fish", EntityType.Builder.of(STropicalFishEntity::new, MobCategory.WATER_AMBIENT).sized(0.5F, 0.4F).clientTrackingRange(4), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SZombieHorseEntity>> SHINY_ZOMBIE_HORSE= registerEntity("shiny_zombie_horse", EntityType.Builder.of(SZombieHorseEntity::new,MobCategory.CREATURE).sized(1.3964844F, 1.6F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SSkeletonHorseEntity>> SHINY_SKELETON_HORSE= registerEntity("shiny_skeleton_horse", EntityType.Builder.of(SSkeletonHorseEntity::new,MobCategory.CREATURE).sized(1.3964844F, 1.6F).fireImmune().immuneTo(Blocks.WITHER_ROSE).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SPhantomEntity>> SHINY_PHANTOM= registerEntity("shiny_phantom", EntityType.Builder.of(SPhantomEntity::new,MobCategory.MONSTER).sized(0.9F, 0.5F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SAxolotlEntity>> SHINY_AXOLOTL= registerEntity("shiny_axolotl", EntityType.Builder.of(SAxolotlEntity::new,MobCategory.AXOLOTLS).sized(0.75F, 0.42F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SVindicatorEntity>> SHINY_VINDICATOR= registerEntity("shiny_vindicator", EntityType.Builder.of(SVindicatorEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SPillagerEntity>> SHINY_PILLAGER= registerEntity("shiny_pillager", EntityType.Builder.of(SPillagerEntity::new, MobCategory.MONSTER).canSpawnFarFromPlayer().sized(0.6F, 1.95F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SBeeEntity>> SHINY_BEE= registerEntity("shiny_bee", EntityType.Builder.of(SBeeEntity::new, MobCategory.CREATURE).sized(0.7F, 0.6F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SSnifferEntity>> SHINY_SNIFFER= registerEntity("shiny_sniffer", EntityType.Builder.of(SSnifferEntity::new,MobCategory.CREATURE).sized(1.9F, 1.75F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SChickenEntity>> SHINY_CHICKEN= registerEntity("shiny_chicken", EntityType.Builder.of(SChickenEntity::new,MobCategory.CREATURE).sized(0.4F, 0.7F).clientTrackingRange(10), 0XEDBD00, 0XFFE8AF);
    public static final RegistryObject<EntityType<SFoxEntity>> SHINY_FOX= registerEntity("shiny_fox", EntityType.Builder.of(SFoxEntity::new,MobCategory.CREATURE).sized(0.6F, 1.95F).clientTrackingRange(8), 0XEDBD00, 0XFFE8AF);


    private static <E extends Mob> RegistryObject<EntityType<E>> registerEntity(String entityName, EntityType.Builder<E> builder, int baseEggColor, int overlayEggColor) {
        ResourceLocation nameLoc = new ResourceLocation(ShinyMob.MODID, entityName);
        RegistryObject<EntityType<E>> ret = ENTITIES.register(entityName, () -> builder.build(nameLoc.toString()));
        RegistryObject<Item> spawnEggItem = ItemsInit.ITEMS.register(entityName + "_spawn_egg", () -> new ForgeSpawnEggItem(ret, baseEggColor, overlayEggColor, new Item.Properties()));
        ItemsInit.SPAWN_EGGS.add(spawnEggItem);
        return ret;
    }

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(EntityInit.SHINY_ENDERMAN.get(),SEnderManEntity.createAttributes().build());
        event.put(EntityInit.SHINY_SHEEP.get(),SSheepEntity.createAttributes().build());
        event.put(EntityInit.SHINY_ALLAY.get(),SAllayEntity.createAttributes().build());
        event.put(EntityInit.SHINY_CREEPER.get(),SCreeperEntity.createAttributes().build());
        event.put(EntityInit.SHINY_SNOW_GOLEM.get(),SSnowGolemEntity.createAttributes().build());
        event.put(EntityInit.SHINY_BLAZE.get(), SBlazeEntity.createAttributes().build());
        event.put(EntityInit.SHINY_WITHER_SKELETON.get(), SWitherSkeletonEntity.createAttributes().build());
        event.put(EntityInit.SHINY_GHAST.get(), SGhastEntity.createAttributes().build());
        event.put(EntityInit.SHINY_SKELETON.get(), SSkeletonEntity.createAttributes().build());
        event.put(EntityInit.SHINY_WARDEN.get(), SWardenEntity.createAttributes().build());
        event.put(EntityInit.SHINY_STRAY.get(), SStrayEntity.createAttributes().build());
        event.put(EntityInit.SHINY_HUSK.get(), Husk.createAttributes().build());
        event.put(EntityInit.SHINY_ZOMBIE.get(), Zombie.createAttributes().build());
        event.put(EntityInit.SHINY_STRIDER.get(), Strider.createAttributes().build());
        event.put(EntityInit.SHINY_SPIDER.get(), SSpiderEntity.createAttributes().build());
        event.put(EntityInit.SHINY_IRON_GOLEM.get(), SIronGolemEntity.createAttributes().build());
        event.put(EntityInit.SHINY_SHULKER.get(), Shulker.createAttributes().build());
        event.put(EntityInit.SHINY_PANDA.get(), SPandaEntity.createAttributes().build());
        event.put(EntityInit.SHINY_PIG.get(), SPigEntity.createAttributes().build());
        event.put(EntityInit.SHINY_ZOMBIFIED_PIGLIN.get(), ZombifiedPiglin.createAttributes().build());
        event.put(EntityInit.SHINY_PIGLIN.get(), Piglin.createAttributes().build());
        event.put(EntityInit.SHINY_WITHER.get(), SWitherEntity.createAttributes().build());
        event.put(EntityInit.SHINY_TURTLE.get(), Turtle.createAttributes().build());
        event.put(EntityInit.SHINY_HOGLIN.get(), SHoglinEntity.createAttributes().build());
        event.put(EntityInit.SHINY_ZOGLIN.get(), SZoglinEntity.createAttributes().build());
        event.put(EntityInit.SHINY_COD.get(), SCodEntity.createAttributes().build());
        event.put(EntityInit.SHINY_SALMON.get(), SSalmonEnity.createAttributes().build());
        event.put(EntityInit.SHINY_PUFFERFISH.get(), SPufferFishEntity.createAttributes().build());
        event.put(EntityInit.SHINY_SLIME.get(), SSlimeEntity.createAttributes().build());
        event.put(EntityInit.SHINY_MAGMA_CUBE.get(), SMagmaCubeEntity.createAttributes().build());
        event.put(EntityInit.SHINY_SQUID.get(), SSquidEntity.createAttributes().build());
        event.put(EntityInit.SHINY_SILVERFISH.get(), SSilverFishEntity.createAttributes().build());
        event.put(EntityInit.SHINY_ENDERMITE.get(), SEndermiteEntity.createAttributes().build());
        event.put(EntityInit.SHINY_RABBIT.get(), SRabbitEntity.createAttributes().build());
        event.put(EntityInit.SHINY_GLOW_SQUID.get(), SGlowSquidEntity.createAttributes().build());
        event.put(EntityInit.SHINY_GOAT.get(), SGoatEntity.createAttributes().build());
        event.put(EntityInit.SHINY_EVOKER.get(), SEvokerEntity.createAttributes().build());
        event.put(EntityInit.SHINY_VEX.get(), SVexEntity.createAttributes().build());
        event.put(EntityInit.SHINY_WITCH.get(), SWitchEntity.createAttributes().build());
        event.put(EntityInit.SHINY_COW.get(), SCowEntity.createAttributes().build());
        event.put(EntityInit.SHINY_MOOSHROOM.get(), SMushroomCowEntity.createAttributes().build());
        event.put(EntityInit.SHINY_WOLF.get(), SWolfEntity.createAttributes().build());
        event.put(EntityInit.SHINY_OCELOT.get(), SOcelotEntity.createAttributes().build());
        event.put(EntityInit.SHINY_CAT.get(), SCatEntity.createAttributes().build());
        event.put(EntityInit.SHINY_HORSE.get(), SHorseEntity.createAttributes().build());
        event.put(EntityInit.SHINY_GUARDIAN.get(), SGuardianEntity.createAttributes().build());
        event.put(EntityInit.SHINY_ELDER_GUARDIAN.get(), SElderGuardianEntity.createAttributes().build());
        event.put(EntityInit.SHINY_BAT.get(), SBatEntity.createAttributes().build());
        event.put(EntityInit.SHINY_POLAR_BEAR.get(), SPolarBearEntity.createAttributes().build());
        event.put(EntityInit.SHINY_TROPICAL_FISH.get(), STropicalFishEntity.createAttributes().build());
        event.put(EntityInit.SHINY_ZOMBIE_HORSE.get(), SZombieHorseEntity.createAttributes().build());
        event.put(EntityInit.SHINY_SKELETON_HORSE.get(), SSkeletonHorseEntity.createAttributes().build());
        event.put(EntityInit.SHINY_PHANTOM.get(), SPhantomEntity.createAttributes().build());
        event.put(EntityInit.SHINY_AXOLOTL.get(), SAxolotlEntity.createAttributes().build());
        event.put(EntityInit.SHINY_VINDICATOR.get(), SVindicatorEntity.createAttributes().build());
        event.put(EntityInit.SHINY_PILLAGER.get(), SPillagerEntity.createAttributes().build());
        event.put(EntityInit.SHINY_BEE.get(), SBeeEntity.createAttributes().build());
        event.put(EntityInit.SHINY_SNIFFER.get(), SSnifferEntity.createAttributes().build());
        event.put(EntityInit.SHINY_CHICKEN.get(), SChickenEntity.createAttributes().build());
        event.put(EntityInit.SHINY_FOX.get(), SFoxEntity.createAttributes().build());

    }
    @SubscribeEvent
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event) {
        event.register(SHINY_ENDERMAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, SEnderManEntity::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(SHINY_SHULKER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SShulkerEntity::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(SHINY_SHEEP.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(SHINY_COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(SHINY_PIG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);

    }

}
