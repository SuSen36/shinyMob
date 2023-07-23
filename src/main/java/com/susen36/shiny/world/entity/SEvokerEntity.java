package com.susen36.shiny.world.entity;

import com.susen36.shiny.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class SEvokerEntity extends SpellcasterIllager {
    @Nullable
    private Sheep woolTarget;
    public SEvokerEntity(EntityType<? extends SpellcasterIllager> type, Level level) {
        super(type, level);
        this.xpReward = 20;
    }
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SEvokerCastingSpellGoal());
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 0.6D, 1.0D));
        this.goalSelector.addGoal(4, new SEvokerTotemSpellGoal());
        this.goalSelector.addGoal(5, new SEvokerWoolSpellGoal());
        this.goalSelector.addGoal(6, new SEvokerSummonSpellGoal());
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
        this.targetSelector.addGoal(2, (new NearestAttackableTargetGoal<>(this, Player.class, true)).setUnseenMemoryTicks(300));
        this.targetSelector.addGoal(3, (new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false)).setUnseenMemoryTicks(300));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, false));
    }
    protected void populateDefaultEquipmentSlots(RandomSource randomSource, DifficultyInstance difficulty) {
        super.populateDefaultEquipmentSlots(randomSource, difficulty);

        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.TOTEM_OF_UNDYING));
    }

    void setWoolTarget(@Nullable Sheep sheep) {
        this.woolTarget = sheep;
    }

    @Nullable
    Sheep getWoolTarget() {
        return this.woolTarget;
    }

    @Override
    protected SoundEvent getCastingSoundEvent() {
        return SoundEvents.EVOKER_CAST_SPELL;
    }
    @Override
    public void applyRaidBuffs(int p_32632_, boolean p_32633_) {
    }
    @Override
    public SoundEvent getCelebrateSound() {return SoundEvents.EVOKER_CELEBRATE;}
    class SEvokerCastingSpellGoal extends SpellcasterIllager.SpellcasterCastingSpellGoal {
        public void tick() {
            if (SEvokerEntity.this.getTarget() != null) {
                SEvokerEntity.this.getLookControl().setLookAt(SEvokerEntity.this.getTarget(), (float)SEvokerEntity.this.getMaxHeadYRot(), (float)SEvokerEntity.this.getMaxHeadXRot());
            } else if (SEvokerEntity.this.getWoolTarget() != null) {
                SEvokerEntity.this.getLookControl().setLookAt(SEvokerEntity.this.getWoolTarget(), (float)SEvokerEntity.this.getMaxHeadYRot(), (float)SEvokerEntity.this.getMaxHeadXRot());
            }

        }
    }
    class SEvokerSummonSpellGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {
        private final TargetingConditions vexCountTargeting = TargetingConditions.forNonCombat().range(16.0D).ignoreLineOfSight().ignoreInvisibilityTesting();

        public boolean canUse() {
            if (!super.canUse()) {
                return false;
            } else {
                int i = SEvokerEntity.this.level().getNearbyEntities(Vex.class, this.vexCountTargeting, SEvokerEntity.this, SEvokerEntity.this.getBoundingBox().inflate(16.0D)).size();
                return SEvokerEntity.this.random.nextInt(8) + 1 > i;
            }
        }

        protected int getCastingTime() {
            return 100;
        }

        protected int getCastingInterval() {
            return 340;
        }

        protected void performSpellCasting() {
            ServerLevel serverlevel = (ServerLevel)SEvokerEntity.this.level();

            for(int i = 0; i < 3; ++i) {
                BlockPos blockpos = SEvokerEntity.this.blockPosition().offset(-2 + SEvokerEntity.this.random.nextInt(5), 1, -2 + SEvokerEntity.this.random.nextInt(5));
                SVexEntity sVexEntityvex = EntityInit.SHINY_VEX.get().create(SEvokerEntity.this.level());
                if (sVexEntityvex != null) {
                    sVexEntityvex.moveTo(blockpos, 0.0F, 0.0F);
                    sVexEntityvex.finalizeSpawn(serverlevel, SEvokerEntity.this.level().getCurrentDifficultyAt(blockpos), MobSpawnType.MOB_SUMMONED, (SpawnGroupData)null, (CompoundTag)null);
                    sVexEntityvex.setOwner(SEvokerEntity.this);
                    sVexEntityvex.setBoundOrigin(blockpos);
                    sVexEntityvex.setLimitedLife(20 * (30 + SEvokerEntity.this.random.nextInt(90)));
                    serverlevel.addFreshEntityWithPassengers(sVexEntityvex);
                }
            }

        }

        protected SoundEvent getSpellPrepareSound() {
            return SoundEvents.EVOKER_PREPARE_SUMMON;
        }

        protected SpellcasterIllager.IllagerSpell getSpell() {
            return SpellcasterIllager.IllagerSpell.SUMMON_VEX;
        }
    }
    class SEvokerTotemSpellGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {
        private final TargetingConditions vexCountTargeting = TargetingConditions.forNonCombat().range(16.0D).ignoreLineOfSight().ignoreInvisibilityTesting();

        public boolean canUse() {
            if (!super.canUse()) {
                return false;
            } else {
                double maxHeath = SEvokerEntity.this.getAttribute(Attributes.MAX_HEALTH).getValue();
                int i = SEvokerEntity.this.level().getNearbyEntities(Vex.class, this.vexCountTargeting, SEvokerEntity.this, SEvokerEntity.this.getBoundingBox().inflate(16.0D)).size();
                return SEvokerEntity.this.getHealth()<= maxHeath/4 && SEvokerEntity.this.random.nextInt(8) + 1 > i;
            }
        }

        protected int getCastingTime() {
            return 100;
        }

        protected int getCastingInterval() {
            return 340;
        }

        protected void performSpellCasting() {
            ServerLevel serverlevel = (ServerLevel)SEvokerEntity.this.level();

            for(int i = 0; i < 2; ++i) {
                BlockPos blockpos = SEvokerEntity.this.blockPosition().offset(-2 + SEvokerEntity.this.random.nextInt(5), 1, -2 + SEvokerEntity.this.random.nextInt(5));
                SAllayEntity allay = EntityInit.SHINY_ALLAY.get().create(SEvokerEntity.this.level());
                if (allay != null) {
                    allay.moveTo(blockpos, 0.0F, 0.0F);
                    allay.finalizeSpawn(serverlevel, SEvokerEntity.this.level().getCurrentDifficultyAt(blockpos), MobSpawnType.MOB_SUMMONED, (SpawnGroupData)null, (CompoundTag)null);
                    allay.setOwner(SEvokerEntity.this);
                    serverlevel.addFreshEntityWithPassengers(allay);
                }
            }

        }

        protected SoundEvent getSpellPrepareSound() {
            return SoundEvents.EVOKER_PREPARE_SUMMON;
        }

        protected SpellcasterIllager.IllagerSpell getSpell() {
            return SpellcasterIllager.IllagerSpell.SUMMON_VEX;
        }
    }
    public class SEvokerWoolSpellGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {

        private final TargetingConditions wololoTargeting = TargetingConditions.forNonCombat().range(16.0D).selector((p_32710_) -> {
            return ((Sheep)p_32710_).getColor() == DyeColor.BLUE;
        });

        public boolean canUse() {
            if (SEvokerEntity.this.getTarget() != null) {
                return false;
            } else if (SEvokerEntity.this.isCastingSpell()) {
                return false;
            } else if (SEvokerEntity.this.tickCount < this.nextAttackTickCount) {
                return false;
            } else if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(SEvokerEntity.this.level(), SEvokerEntity.this)) {
                return false;
            } else {
                List<Sheep> list = SEvokerEntity.this.level().getNearbyEntities(Sheep.class, this.wololoTargeting, SEvokerEntity.this, SEvokerEntity.this.getBoundingBox().inflate(16.0D, 4.0D, 16.0D));
                if (list.isEmpty()) {
                    return false;
                } else {
                    SEvokerEntity.this.setWoolTarget(list.get(SEvokerEntity.this.random.nextInt(list.size())));
                    return true;
                }
            }
        }

        public boolean canContinueToUse() {
            return SEvokerEntity.this.getWoolTarget() != null && this.attackWarmupDelay > 0;
        }

        public void stop() {
            super.stop();
            SEvokerEntity.this.setWoolTarget((Sheep)null);
        }

        protected void performSpellCasting() {
            Sheep sheep = SEvokerEntity.this.getWoolTarget();
            if (sheep != null && sheep.isAlive()) {
                sheep.convertTo(EntityType.ILLUSIONER,true);
            }
        }

        protected int getCastWarmupTime() {
            return 40;
        }

        protected int getCastingTime() {
            return 60;
        }

        protected int getCastingInterval() {
            return 140;
        }

        protected SoundEvent getSpellPrepareSound() {
            return SoundEvents.EVOKER_PREPARE_WOLOLO;
        }

        protected SpellcasterIllager.IllagerSpell getSpell() {
            return SpellcasterIllager.IllagerSpell.WOLOLO;
        }
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.5D).add(Attributes.FOLLOW_RANGE, 15.0D).add(Attributes.MAX_HEALTH, 47.0D);
    }
}
