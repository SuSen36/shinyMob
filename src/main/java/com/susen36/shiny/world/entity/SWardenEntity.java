package com.susen36.shiny.world.entity;

import com.mojang.serialization.Dynamic;
import com.susen36.shiny.world.entity.ai.LookAtStrongholdGoal;
import com.susen36.shiny.world.entity.ai.SWardenAi;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.monster.warden.WardenAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nullable;

public class SWardenEntity extends Warden{
    private int limitedLifeTicks =5000;

    public SWardenEntity(EntityType<? extends Warden> type, Level level) {
        super(type, level);
    }

    protected Brain<?> makeBrain(Dynamic<?> p_219406_) {
        return SWardenAi.makeBrain(this, p_219406_);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(7, new LookAtStrongholdGoal(this, 18.0F));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
       // this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, IronGolem.class, 6.0F, 1.0, 1.2));
    }
    public void tick() {
        super.tick();
        this.noPhysics = false;
        this.setNoGravity(false);
        if ( --this.limitedLifeTicks <= 0) {
            this.kill();
        }
    }
    protected void customServerAiStep() {
        ServerLevel serverlevel = (ServerLevel)this.level();
        this.getBrain().tick(serverlevel, this);
        if ((this.tickCount + this.getId()) % 120 == 0) {
            applyNightVisionAround(serverlevel, this.position(), this, 20);
        }

        if (this.tickCount % 20 == 0) {
            this.getAngerManagement().tick(serverlevel, this::canTargetEntity);
        }

        WardenAi.updateActivity(this);
    }
    public static void applyNightVisionAround(ServerLevel p_219376_, Vec3 p_219377_, @Nullable Entity p_219378_, int p_219379_) {
        MobEffectInstance mobeffectinstance = new MobEffectInstance(MobEffects.NIGHT_VISION, 520, 0, false, false);
        MobEffectUtil.addEffectToPlayersAround(p_219376_, p_219378_, p_219377_, (double)p_219379_, mobeffectinstance, 200);
    }

    @Contract("null->false")
    public boolean canTargetEntity(@Nullable Entity entity) {
        if (entity instanceof LivingEntity livingentity) {
            if (this.level() == entity.level() && EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(entity) && !this.isAlliedTo(entity) && livingentity instanceof Monster&& !(livingentity instanceof SWardenEntity) && !livingentity.isInvulnerable() && !livingentity.isDeadOrDying()&&livingentity.getType() != EntityType.PLAYER) {
                return true;
            }
        }

        return false;
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 250.0).add(Attributes.MOVEMENT_SPEED, 0.15000000092092847).add(Attributes.KNOCKBACK_RESISTANCE, 1.2).add(Attributes.ATTACK_KNOCKBACK, 1.2).add(Attributes.ATTACK_DAMAGE, 18.0);
    }

}
