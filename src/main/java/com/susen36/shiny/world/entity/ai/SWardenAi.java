package com.susen36.shiny.world.entity.ai;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.behavior.warden.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.schedule.Activity;

import java.util.List;

public class SWardenAi {


    private static final int SNIFFING_DURATION = Mth.ceil(83.2F);

    private static final List<SensorType<? extends Sensor<? super Warden>>> SENSOR_TYPES = List.of(SensorType.NEAREST_PLAYERS, SensorType.WARDEN_ENTITY_SENSOR);
    private static final List<MemoryModuleType<?>> MEMORY_TYPES = List.of(MemoryModuleType.NEAREST_LIVING_ENTITIES, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,  MemoryModuleType.NEAREST_VISIBLE_NEMESIS, MemoryModuleType.LOOK_TARGET, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.PATH, MemoryModuleType.ATTACK_TARGET, MemoryModuleType.ATTACK_COOLING_DOWN, MemoryModuleType.NEAREST_ATTACKABLE, MemoryModuleType.ROAR_TARGET, MemoryModuleType.DISTURBANCE_LOCATION, MemoryModuleType.IS_SNIFFING, MemoryModuleType.ROAR_SOUND_DELAY, MemoryModuleType.ROAR_SOUND_COOLDOWN, MemoryModuleType.SNIFF_COOLDOWN, MemoryModuleType.TOUCH_COOLDOWN, MemoryModuleType.VIBRATION_COOLDOWN, MemoryModuleType.SONIC_BOOM_COOLDOWN, MemoryModuleType.SONIC_BOOM_SOUND_COOLDOWN, MemoryModuleType.SONIC_BOOM_SOUND_DELAY);
    private static final BehaviorControl<Warden> DIG_COOLDOWN_SETTER = BehaviorBuilder.create((p_258953_) -> {
        return p_258953_.group(p_258953_.registered(MemoryModuleType.DIG_COOLDOWN)).apply(p_258953_, (p_258960_) -> {
            return (p_258956_, p_258957_, p_258958_) -> {
                if (p_258953_.tryGet(p_258960_).isPresent()) {
                    p_258960_.setWithExpiry(Unit.INSTANCE, 1200L);
                }

                return true;
            };
        });
    });

    public static Brain<?> makeBrain(Warden p_219521_, Dynamic<?> p_219522_) {
        Brain.Provider<Warden> provider = Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
        Brain<Warden> brain = provider.makeBrain(p_219522_);
        initCoreActivity(brain);
        initIdleActivity(brain);
        initRoarActivity(brain);
        initFightActivity(p_219521_, brain);
        initInvestigateActivity(brain);
        initSniffingActivity(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.FIGHT);
        brain.useDefaultActivity();
        return brain;
    }

    private static void initCoreActivity(Brain<Warden> p_219511_) {
        p_219511_.addActivity(Activity.CORE, 0, ImmutableList.of(new Swim(0.8F), SetWardenLookTarget.create(), new LookAtTargetSink(45, 90), new MoveToTargetSink()));
    }


    private static void initIdleActivity(Brain<Warden> p_219537_) {
        p_219537_.addActivity(Activity.IDLE, 10, ImmutableList.of(SetRoarTarget.create(Warden::getEntityAngryAt), TryToSniff.create(), new RunOne<>(ImmutableMap.of(MemoryModuleType.IS_SNIFFING, MemoryStatus.VALUE_ABSENT), ImmutableList.of(Pair.of(RandomStroll.stroll(0.5F), 2), Pair.of(new DoNothing(30, 60), 1)))));
    }

    private static void initInvestigateActivity(Brain<Warden> p_219542_) {
        p_219542_.addActivityAndRemoveMemoryWhenStopped(Activity.INVESTIGATE, 5, ImmutableList.of(SetRoarTarget.create(Warden::getEntityAngryAt), GoToTargetLocation.create(MemoryModuleType.DISTURBANCE_LOCATION, 2, 0.7F)), MemoryModuleType.DISTURBANCE_LOCATION);
    }

    private static void initSniffingActivity(Brain<Warden> p_219544_) {
        p_219544_.addActivityAndRemoveMemoryWhenStopped(Activity.SNIFF, 5, ImmutableList.of(SetRoarTarget.create(Warden::getEntityAngryAt), new Sniffing<>(SNIFFING_DURATION)), MemoryModuleType.IS_SNIFFING);
    }

    private static void initRoarActivity(Brain<Warden> p_219546_) {
        p_219546_.addActivityAndRemoveMemoryWhenStopped(Activity.ROAR, 10, ImmutableList.of(new Roar()), MemoryModuleType.ROAR_TARGET);
    }

    private static void initFightActivity(Warden p_219518_, Brain<Warden> p_219519_) {
        p_219519_.addActivityAndRemoveMemoryWhenStopped(Activity.FIGHT, 10, ImmutableList.of(DIG_COOLDOWN_SETTER, StopAttackingIfTargetInvalid.<Warden>create((p_219540_) -> {
            return !p_219518_.getAngerLevel().isAngry() || !p_219518_.canTargetEntity(p_219540_);
        }, SWardenAi::onTargetInvalid, false), SetEntityLookTarget.create((p_219535_) -> {
            return isTarget(p_219518_, p_219535_);
        }, (float)p_219518_.getAttributeValue(Attributes.FOLLOW_RANGE)), SetWalkTargetFromAttackTargetIfTargetOutOfReach.create(1.2F), new SonicBoom(), MeleeAttack.create(18)), MemoryModuleType.ATTACK_TARGET);
    }

    private static boolean isTarget(Warden p_219515_, LivingEntity target) {
        return target instanceof Monster;
    }

    private static void onTargetInvalid(Warden warden, LivingEntity target) {
        if (!warden.canTargetEntity(target)) {
            warden.clearAnger(target);
        }

        setDigCooldown(warden);
    }

    public static void setDigCooldown(LivingEntity p_219506_) {
        if (p_219506_.getBrain().hasMemoryValue(MemoryModuleType.DIG_COOLDOWN)) {
            p_219506_.getBrain().setMemoryWithExpiry(MemoryModuleType.DIG_COOLDOWN, Unit.INSTANCE, 1200L);
        }

    }

}
