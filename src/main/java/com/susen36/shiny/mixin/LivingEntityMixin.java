package com.susen36.shiny.mixin;

import com.susen36.shiny.world.entity.SAllayEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(value = LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {


    @Shadow protected abstract boolean checkTotemDeathProtection(DamageSource p_21263_);

    @Shadow public abstract boolean isDeadOrDying();

    @Shadow public abstract void setHealth(float p_21154_);

    @Shadow public abstract boolean removeAllEffects();

    @Shadow public abstract boolean addEffect(MobEffectInstance p_21165_);

    public LivingEntityMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }
    @Redirect(method = "hurt", at = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/LivingEntity;checkTotemDeathProtection(Lnet/minecraft/world/damagesource/DamageSource;)Z"))
    public boolean tryUseTotemInject(LivingEntity instance, DamageSource interactionhand) {
            return !this.checkTotemDeathProtection(interactionhand)&&!checkSAllayDeathProtection(interactionhand);
        }
    private boolean checkSAllayDeathProtection(DamageSource source) {
        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return false;
        } else {

            float inflate = 30.0f;
            AABB entityAABB = this.getBoundingBox().inflate(inflate, inflate, inflate);
            List<SAllayEntity> allay = this.level.getEntitiesOfClass(SAllayEntity.class, entityAABB);
            if (!allay.isEmpty()) {
                boolean flag = !this.level().isClientSide;

                    SAllayEntity entity = allay.get(0);
                    if (this.isDeadOrDying() && entity != null) {
                        if (flag && entity.getOwner() != null && entity.getOwner().getUUID().equals(this.getUUID()) && this.isDeadOrDying()) {
                            if (entity.getOwner() instanceof ServerPlayer serverplayer) {
                                serverplayer.awardStat(Stats.ITEM_USED.get(Items.TOTEM_OF_UNDYING), 1);
                            }

                            this.setHealth(1.0F);
                            this.removeAllEffects();
                            this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
                            this.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
                            this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0));
                            this.level().broadcastEntityEvent(this, (byte) 35);
                            entity.kill();

                        }
                    }
                }
            return allay.isEmpty();
        }
    }
}


