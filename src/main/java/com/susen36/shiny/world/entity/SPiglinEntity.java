package com.susen36.shiny.world.entity;

import com.susen36.shiny.init.EntityInit;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class SPiglinEntity extends Piglin {
    public SPiglinEntity(EntityType<? extends AbstractPiglin> type, Level level) {
        super(type, level);
    }
    protected void populateDefaultEquipmentSlots(RandomSource p_219189_, DifficultyInstance p_219190_) {
        if (this.isAdult()) {
            this.maybeWearArmor(EquipmentSlot.HEAD, new ItemStack(Items.GOLDEN_HELMET), p_219189_);
            this.maybeWearArmor(EquipmentSlot.CHEST, new ItemStack(Items.GOLDEN_CHESTPLATE), p_219189_);
            this.maybeWearArmor(EquipmentSlot.LEGS, new ItemStack(Items.GOLDEN_LEGGINGS), p_219189_);
            this.maybeWearArmor(EquipmentSlot.FEET, new ItemStack(Items.GOLDEN_BOOTS), p_219189_);
        }
    }
    protected void customServerAiStep() {
        if (this.isConverting()||this.getLastHurtByMob() instanceof SZombifiedPiglinEntity) {
            this.playConvertedSound();
            this.finishConversion((ServerLevel) this.level());
        }
        super.customServerAiStep();
    }
    protected void finishConversion(ServerLevel p_34663_) {
        ZombifiedPiglin zombifiedpiglin = this.convertTo(EntityInit.SHINY_ZOMBIFIED_PIGLIN.get(), true);
        if (zombifiedpiglin != null) {
            zombifiedpiglin.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 500, 0));
            net.minecraftforge.event.ForgeEventFactory.onLivingConvert(this, zombifiedpiglin);
        }
    }
    public boolean isConverting() {
        return this.level().dimensionType().piglinSafe() && !this.isImmuneToZombification() && !this.isNoAi();
    }
    private void maybeWearArmor(EquipmentSlot slot, ItemStack itemStack, RandomSource random) {
        if (random.nextFloat() < 0.6F) {
            this.setItemSlot(slot, itemStack);
        }
    }
}
