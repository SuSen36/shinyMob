package com.susen36.shiny.world.entity;

import com.susen36.shiny.init.EntityInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class SBeeEntity extends Animal {


    public SBeeEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.HONEYCOMB), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!this.level.isClientSide &&this.isFood(itemstack)) {
            this.usePlayerItem(player, hand, itemstack);
            if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
                this.ageUp();
                this.navigation.stop();
                this.setTarget((LivingEntity) null);
                this.level().broadcastEntityEvent(this, (byte) 7);
            } else {
                this.level().broadcastEntityEvent(this, (byte) 6);
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(player, hand);
        }
    }

    private void ageUp() {
        Level level = this.level();
        if (level instanceof ServerLevel serverlevel) {
            Bee bee = EntityType.BEE.create(this.level());
            if (bee != null) {
                bee.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
                bee.finalizeSpawn(serverlevel, this.level().getCurrentDifficultyAt(bee.blockPosition()), MobSpawnType.CONVERSION, (SpawnGroupData)null, (CompoundTag)null);
                bee.setNoAi(this.isNoAi());
                if (this.hasCustomName()) {
                    bee.setCustomName(this.getCustomName());
                    bee.setCustomNameVisible(this.isCustomNameVisible());
                }
                bee.setPersistenceRequired();
                this.playSound(SoundEvents.TADPOLE_GROW_UP, 0.15F, 1.0F);
                serverlevel.addFreshEntityWithPassengers(bee);
                this.discard();
            }
        }

    }

    public boolean shouldDropExperience() {
        return false;
    }
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(Items.HONEYCOMB);
    }
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob p_146744_) {
        return EntityInit.SHINY_BEE.get().create(level);
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.15D).add(Attributes.MAX_HEALTH, 2.0D);
    }
}
