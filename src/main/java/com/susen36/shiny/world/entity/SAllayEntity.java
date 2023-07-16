package com.susen36.shiny.world.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
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
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.Nullable;

public class SAllayEntity extends TamableAnimal {
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
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (this.level().isClientSide) {
            boolean flag = this.isOwnedBy(player) || this.isTame() || itemstack.is(Items.AMETHYST_SHARD) && !this.isTame();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else if (this.isTame()) {
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
                this.tame(player);
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
           this.spawnAtLocation(itemstack);
    }
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return this;
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.FLYING_SPEED, (double)0.32F).add(Attributes.FOLLOW_RANGE, 32.0D);
    }
}
