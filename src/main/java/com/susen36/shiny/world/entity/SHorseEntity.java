package com.susen36.shiny.world.entity;

import com.google.common.collect.Maps;
import com.susen36.shiny.init.EntityInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class SHorseEntity extends AbstractHorse{
    private static final EntityDataAccessor<String> DATA_TYPE = SynchedEntityData.defineId(SHorseEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Byte> DATA_DYE = SynchedEntityData.defineId(SHorseEntity.class, EntityDataSerializers.BYTE);

    public SHorseEntity(EntityType<? extends AbstractHorse> type, Level level) {
        super(type, level);
    }
    private static final Map<DyeColor, float[]> COLORARRAY_BY_COLOR = Maps.<DyeColor, float[]>newEnumMap(Arrays.stream(DyeColor.values()).collect(Collectors.toMap((p_29868_) -> {
        return p_29868_;
    }, SHorseEntity::createColor)));

    public static ArmorType getRandomType(RandomSource randomSource) {
        int i = randomSource.nextInt(100);
        if (i < 15) {
            return ArmorType.LEATHER;
        } else if (i < 35) {
            return ArmorType.GOLD;
        } else if (i < 45) {
            return ArmorType.DIAMOND;
        }
        return ArmorType.IRON;
    }
    public DyeColor getColor() {
        return DyeColor.byId(this.entityData.get(DATA_DYE) & 15);
    }

    public void setColor(DyeColor p_29856_) {
        byte b0 = this.entityData.get(DATA_DYE);
        this.entityData.set(DATA_DYE, (byte)(b0 & 240 | p_29856_.getId() & 15));
    }
    @Override
    public boolean canWearArmor() {
        return false;
    }
    private static float[] createColor(DyeColor p_29866_) {
        if (p_29866_ == DyeColor.WHITE) {
            return new float[]{0.9019608F, 0.9019608F, 0.9019608F};
        } else {
            float[] afloat = p_29866_.getTextureDiffuseColors();
            float f = 0.75F;
            return new float[]{afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
        }
    }
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance p_29836_, MobSpawnType p_29837_, @Nullable SpawnGroupData p_29838_, @Nullable CompoundTag p_29839_) {
        this.setVariant(getRandomType(levelAccessor.getRandom()));
        if(this.getVariant()==ArmorType.LEATHER){
            this.setColor(getRandomSheepColor(levelAccessor.getRandom()));
        }
        this.setArmor();
        return super.finalizeSpawn(levelAccessor, p_29836_, p_29837_, p_29838_, p_29839_);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TYPE, ArmorType.IRON.type);
        this.entityData.define(DATA_DYE, (byte)0);
    }
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("Type", this.getVariant().getSerializedName());
        tag.putByte("Color", (byte)this.getColor().getId());
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setVariant(ArmorType.byType(tag.getString("Type")));
        this.setColor(DyeColor.byId(tag.getByte("Color")));
        this.setArmor();
       }
    public void setArmor(){
        int i = getVariant().armor;
        this.getAttribute(Attributes.ARMOR).setBaseValue((double)(i));
    }
    public static DyeColor getRandomSheepColor(RandomSource p_218262_) {
        int i = p_218262_.nextInt(100);
        if (i < 20) {
            return DyeColor.RED;
        } else if (i < 40) {
            return DyeColor.GREEN;
        } else if (i < 80) {
            return DyeColor.BLUE;
        } else {
            return  DyeColor.WHITE;
        }
    }
    private static CraftingContainer makeContainer(DyeColor p_29832_, DyeColor p_29833_) {
        CraftingContainer craftingcontainer = new TransientCraftingContainer(new AbstractContainerMenu((MenuType)null, -1) {
            public ItemStack quickMoveStack(Player p_218264_, int p_218265_) {
                return ItemStack.EMPTY;
            }

            public boolean stillValid(Player p_29888_) {
                return false;
            }
        }, 2, 1);
        craftingcontainer.setItem(0, new ItemStack(DyeItem.byColor(p_29832_)));
        craftingcontainer.setItem(1, new ItemStack(DyeItem.byColor(p_29833_)));
        return craftingcontainer;
    }
    public void setVariant(ArmorType p_28929_) {
        this.entityData.set(DATA_TYPE, p_28929_.type);
    }

    public ArmorType getVariant() {
        return ArmorType.byType(this.entityData.get(DATA_TYPE));
    }
    @Nullable
    public SHorseEntity getBreedOffspring(ServerLevel p_149001_, AgeableMob p_149002_) {
        return EntityInit.SHINY_HORSE.get().create(p_149001_);
    }
    public static AttributeSupplier.Builder createAttributes() {
        return createBaseHorseAttributes().add(Attributes.FOLLOW_RANGE, 40.0).add(Attributes.MAX_HEALTH,32);
    }
    public static enum ArmorType implements StringRepresentable {
        LEATHER("leather",3),
        IRON("iron",5),
        GOLD("gold", 7),
        DIAMOND("diamond", 11);

        public static final StringRepresentable.EnumCodec<ArmorType> CODEC = StringRepresentable.fromEnum(ArmorType::values);
        final String type;
        final int armor;

        private ArmorType(String type, int armor) {
            this.type = type;
            this.armor = armor;
        }
        @NotNull
        public  String getSerializedName() {
            return this.type;
        }

        static ArmorType byType(String p_28977_) {
            return CODEC.byName(p_28977_, IRON);
        }
    }
}
