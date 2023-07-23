package com.susen36.shiny.world.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.Nullable;

public class SAllayEntity extends TamableAnimal {
    @Nullable
    LivingEntity owner;
    public SAllayEntity(EntityType<? extends TamableAnimal> allay, Level level) {
        super(allay, level);
        this.moveControl = new FlyingMoveControl(this, 20, true);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2, Ingredient.of(new ItemLike[]{Items.AMETHYST_SHARD}), false));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 0.3, 5.0F, 2.0F, true));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Monster.class, 6.0F, 1.0, 1.2));
    }
    @Nullable
    public LivingEntity getOwner() {
        return this.owner;
    }

    public void setOwner(Mob owner) {
        this.owner = owner;
    }
    public void setOwner(Player owner) {
        this.owner = owner;
    }
    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (this.level().isClientSide) {
            boolean flag = this.isOwnedBy(player) || this.getOwner()==player || itemstack.is(Items.AMETHYST_SHARD) && !this.isTame();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else if (this.getOwner()==player ) {
            if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                this.heal(this.getMaxHealth() / 5);
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }
                this.gameEvent(GameEvent.EAT, this);
                return InteractionResult.SUCCESS;
            }
        } else if (itemstack.is(Items.AMETHYST_SHARD)) {
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            if (this.random.nextInt(5) == 0 && !ForgeEventFactory.onAnimalTame(this, player)) {
                this.setOwner(player);
                this.navigation.stop();
                this.level().broadcastEntityEvent(this, (byte) 7);
            } else {
                this.level().broadcastEntityEvent(this, (byte) 6);
            }
            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(player, hand);
        }
        return InteractionResult.PASS;
    }

    protected void dropEquipment() {
        super.dropEquipment();
        ItemStack itemstack = Items.TOTEM_OF_UNDYING.getDefaultInstance();
        if(this.random.nextInt(3)==0) {
            this.spawnAtLocation(itemstack);
        }
    }
    protected void checkFallDamage(double p_19911_, boolean p_19912_, BlockState p_19913_, BlockPos p_19914_) {return;}

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return this;
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.FLYING_SPEED, (double)0.32F).add(Attributes.FOLLOW_RANGE, 32.0D);
    }
}
