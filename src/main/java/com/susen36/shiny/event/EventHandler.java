package com.susen36.shiny.event;

import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.api.ISheepRespawnPosition;
import com.susen36.shiny.init.EntityInit;
import com.susen36.shiny.world.entity.SSheepEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;
import java.util.function.Function;

@Mod.EventBusSubscriber(modid = ShinyMob.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {
    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        if(player instanceof ISheepRespawnPosition) {
            UUID bedVehicleUUID = ((ISheepRespawnPosition) player).getBedEntityUUID();
            if(bedVehicleUUID != null) {
                Entity bedVehicle = ((ServerLevel) player.level).getEntity(bedVehicleUUID);
                if (bedVehicle instanceof SSheepEntity) {
                    if ((bedVehicle).getPassengers().size() == 0) {
                        if(player.level == bedVehicle.level) {
                            player.setPos(bedVehicle.getX(), bedVehicle.getY(), bedVehicle.getZ());
                        } else {
                            player.changeDimension((ServerLevel) player.level, new ITeleporter() {
                                @Override
                                public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
                                    return repositionEntity.apply(false);
                                }
                                @Override
                                public PortalInfo getPortalInfo(Entity entity, ServerLevel destWorld, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
                                    return new PortalInfo(bedVehicle.position(), Vec3.ZERO, entity.getYRot(), entity.getXRot());
                                }
                            });
                        }
                        player.startRiding(bedVehicle);
                        return;
                    }
                } else {
                    ((ISheepRespawnPosition) player).setBedEntityUUID(null);
                }
                player.sendSystemMessage(Component.translatable("block.minecraft.spawn.not_valid"));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        Player player = event.getEntity();
        Player original = event.getOriginal();
        if(player instanceof ISheepRespawnPosition && original instanceof ISheepRespawnPosition) {
            ((ISheepRespawnPosition)player).setBedEntityUUID(((ISheepRespawnPosition)original).getBedEntityUUID());
        }
    }
    @SubscribeEvent
    public static void PlayerRightClickItem(PlayerInteractEvent.EntityInteractSpecific event) {
        Entity entity = event.getTarget();
        Player player = event.getEntity();
        Item item = player.getMainHandItem().getItem();
        ItemStack itemStack = item.getDefaultInstance();
        if(! event.getLevel().isClientSide&&event.getHand() == InteractionHand.MAIN_HAND){
            if (entity.getType() == EntityType.WARDEN  && item.equals(Items.ENDER_EYE)) {
                if (!player.isCreative()&&entity.level.random.nextInt(3)==0) {
                    item.finishUsingItem(item.getDefaultInstance(),player.level,player);
                    itemStack.shrink(1);
                }else {
                    entity.level().broadcastEntityEvent(entity, (byte) 6);
                    itemStack.shrink(1);
                }
                LivingEntity warden = EntityInit.SHINY_WARDEN.get().create(entity.level);
                entity.remove(Entity.RemovalReason.UNLOADED_WITH_PLAYER);
                warden.setPos(entity.getX(),entity.getY(),entity.getZ());
                entity.level.addFreshEntity(warden);
            }
        }
    }
}
