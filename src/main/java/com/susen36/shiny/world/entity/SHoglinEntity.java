package com.susen36.shiny.world.entity;

import com.susen36.shiny.init.EntityInit;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.hoglin.HoglinAi;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class SHoglinEntity extends Hoglin {
    public SHoglinEntity(EntityType<? extends Hoglin> type, Level level) {
        super(type, level);
    }
    protected void customServerAiStep() {
        this.level().getProfiler().push("hoglinBrain");
        this.getBrain().tick((ServerLevel)this.level(), this);
        this.level().getProfiler().pop();
        HoglinAi.updateActivity(this);
        if (this.isConverting()||this.getLastHurtByMob() instanceof SZoglinEntity) {
            this.playSoundEvent(SoundEvents.HOGLIN_CONVERTED_TO_ZOMBIFIED);
                this.finishConversion((ServerLevel)this.level());
            }
    }
    private void finishConversion(ServerLevel serverLevel) {
        Zoglin zoglin = this.convertTo(EntityType.ZOGLIN, true);
        if (zoglin != null) {
            zoglin.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 500, 0));
            net.minecraftforge.event.ForgeEventFactory.onLivingConvert(this, zoglin);
        }
    }
    public boolean isConverting() {
        return this.level().dimensionType().piglinSafe() && !this.isNoAi()&& !this.isImmuneToZombification();
    }
    @Nullable
    public Hoglin getBreedOffspring(ServerLevel p_149001_, AgeableMob p_149002_) {
        return EntityInit.SHINY_HOGLIN.get().create(p_149001_);
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 40.0D).add(Attributes.MOVEMENT_SPEED, (double)0.3F).add(Attributes.KNOCKBACK_RESISTANCE, (double)0.5F).add(Attributes.ATTACK_KNOCKBACK, 0.75D).add(Attributes.ATTACK_DAMAGE, 5.0D);
    }
}
