package com.susen36.shiny.world.entity;

import com.susen36.shiny.api.ISheepRespawnPosition;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SSheepEntity extends Sheep {
    public SSheepEntity(EntityType<? extends Sheep> sheep, Level level) {
        super(sheep, level);
    }
    @Override
    public double getPassengersRidingOffset() {
        return -0.1D;
    }
    @Nonnull
    public Item getDropItem() {
        return switch (this.getColor()) {
            case WHITE -> Items.WHITE_BED;
            case ORANGE -> Items.ORANGE_BED;
            case MAGENTA -> Items.MAGENTA_BED;
            case LIGHT_BLUE -> Items.LIGHT_BLUE_BED;
            case YELLOW -> Items.YELLOW_BED;
            case LIME -> Items.LIME_BED;
            case PINK -> Items.PINK_BED;
            case GRAY -> Items.GRAY_BED;
            case LIGHT_GRAY -> Items.LIGHT_GRAY_BED;
            case CYAN -> Items.CYAN_BED;
            case PURPLE -> Items.PURPLE_BED;
            case BLUE -> Items.BLUE_BED;
            case BROWN -> Items.BROWN_BED;
            case GREEN -> Items.GREEN_BED;
            case RED -> Items.RED_BED;
            case BLACK -> Items.BLACK_BED;
        };
    }
    protected void dropEquipment() {
        super.dropEquipment();
        this.spawnAtLocation(this.getDropItem());
    }

    @Override @Nonnull
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
         if (player.isSecondaryUseActive()) {
             return InteractionResult.PASS;
         }
            if (!this.level.isClientSide) {
                if (!BedBlock.canSetSpawn(this.level)) {
                    // boolean mobGriefing = ForgeEventFactory.getMobGriefingEvent(this.level(), this);
                    this.level.explode(this, this.getX() + 0.5D, this.getY() + 0.125D, this.getZ() + 0.5D, 5.0F, Level.ExplosionInteraction.NONE);
                    this.remove(RemovalReason.DISCARDED);
                    return InteractionResult.SUCCESS;
                }
                InteractionResult ret;
                if(player.startRiding(this) &&this.hasCustomName()) {
                    ret = InteractionResult.CONSUME;
                    if(player instanceof ISheepRespawnPosition) {
                        ((ISheepRespawnPosition)player).setBedEntityUUID(this.uuid);
                         player.sendSystemMessage(Component.translatable("block.minecraft.set_spawn"));
                    }
                } else {
                    ret = InteractionResult.PASS;
                }
                return ret;
            }
            return InteractionResult.PASS;
    }
    @Nullable
    public LivingEntity getControllingPassenger() {

            Entity entity = this.getFirstPassenger();
            if (entity instanceof Player) {
                Player player = (Player)entity;
                if (player.getMainHandItem().is(Items.WHEAT) || player.getOffhandItem().is(Items.WHEAT)) {
                    return player;
                }
            }

        return null;
    }
    protected void tickRidden(Player player, Vec3 vec3) {
        super.tickRidden(player, vec3);
        this.setRot(player.getYRot(), player.getXRot() * 0.5F);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
    }
    @NotNull
    protected Vec3 getRiddenInput( Player player, Vec3 vec3) {
        return new Vec3(0.0, 0.0, 1.0);
    }

    protected float getRiddenSpeed(Player player) {
        return (float)(this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.283f );
    }
    @Override
    protected void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
        if (this.hasPassenger(entity)) {
            float f = 1.0f;
            moveFunction.accept(entity, this.getX(), this.getY() + (double)f, this.getZ());
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH).add(Attributes.MOVEMENT_SPEED).add(Attributes.ATTACK_DAMAGE);
    }
}
