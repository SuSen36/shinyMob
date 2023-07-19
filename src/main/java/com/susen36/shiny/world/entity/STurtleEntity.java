package com.susen36.shiny.world.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;

import java.util.OptionalInt;

public class STurtleEntity extends Turtle {
    private static final EntityDataAccessor<ItemStack> DATA_ITEM = SynchedEntityData.defineId(STurtleEntity.class, EntityDataSerializers.ITEM_STACK);

    public STurtleEntity(EntityType<? extends STurtleEntity> p_30132_, Level p_30133_) {
        super(p_30132_, p_30133_);
    }
    public SlotAccess getSlot(int p_149629_) {
        return p_149629_ == 0 ? new SlotAccess() {
            public ItemStack get() {
                return STurtleEntity.this.getItem();
            }

            public boolean set(ItemStack p_149635_) {
                STurtleEntity.this.setItem(p_149635_);
                return true;
            }
        } : super.getSlot(p_149629_);
    }
    public OptionalInt getFramedMapId() {
        ItemStack itemstack = this.getItem();
        if (itemstack.is(Items.FILLED_MAP)) {
            Integer integer = MapItem.getMapId(itemstack);
            if (integer != null) {
                return OptionalInt.of(integer);
            }
        }

        return OptionalInt.empty();
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_ITEM, ItemStack.EMPTY);
    }
    public void setItem(ItemStack p_31790_) {
        if (!p_31790_.isEmpty()) {
            p_31790_ = p_31790_.copyWithCount(1);
        }
        this.onItemChanged(p_31790_);
        this.getEntityData().set(DATA_ITEM, p_31790_);
        if (!p_31790_.isEmpty()) {
            this.playSound( SoundEvents.ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
        }
    }

    private void onItemChanged(ItemStack itemStack) {
        if (!itemStack.isEmpty()) {
            itemStack.setEntityRepresentation(this);
        }
    }
    private void removeFramedMap(ItemStack p_31811_) {
        this.getFramedMapId().ifPresent((p_289456_) -> {
            MapItemSavedData mapitemsaveddata = MapItem.getSavedData(p_289456_, this.level());
            if (mapitemsaveddata != null) {
                mapitemsaveddata.removedFromFrame(this.blockPosition(), this.getId());
                mapitemsaveddata.setDirty(true);
            }

        });
        p_31811_.setEntityRepresentation((Entity)null);
    }
    public boolean hasFramedMap() {
        return this.getFramedMapId().isPresent();
    }
    public ItemStack getItem() {
        return this.getEntityData().get(DATA_ITEM);
    }
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (!this.getItem().isEmpty()) {
            tag.put("Item", this.getItem().save(new CompoundTag()));
        }
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        CompoundTag compoundtag = tag.getCompound("Item");
        if (compoundtag != null && !compoundtag.isEmpty()) {
            ItemStack itemstack = ItemStack.of(compoundtag);
            if (itemstack.isEmpty()) {
             //   LOGGER.warn("Unable to load item from: {}", (Object)compoundtag);
            }
            ItemStack itemstack1 = this.getItem();
            if (!itemstack1.isEmpty() && !ItemStack.matches(itemstack, itemstack1)) {
                this.removeFramedMap(itemstack1);
            }
            this.setItem(itemstack);
        }
    }
    public void onSyncedDataUpdated(EntityDataAccessor<?> dataAccessor) {
        super.onSyncedDataUpdated(dataAccessor);
        if (dataAccessor.equals(DATA_ITEM)) {
            this.onItemChanged(this.getItem());
        }
    }
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        super.mobInteract(player, hand);
        ItemStack itemstack = player.getItemInHand(hand);

        boolean flag = !this.getItem().isEmpty();
        boolean flag1 = !itemstack.isEmpty();
        if (!this.level().isClientSide) {
            if (!flag) {
                if (flag1 && !this.isRemoved()&&itemstack.is(Items.FILLED_MAP)) {

                        MapItemSavedData mapitemsaveddata = MapItem.getSavedData(itemstack, this.level());
                        if (mapitemsaveddata != null && !mapitemsaveddata.isTrackedCountOverLimit(256)) {
                            this.setItem(itemstack);
                            if (!player.getAbilities().instabuild) {
                                itemstack.shrink(1);
                            }
                            return InteractionResult.CONSUME;
                        }
                }
            }
            return InteractionResult.CONSUME;
        } else {
            return !flag && !flag1 ? InteractionResult.PASS : InteractionResult.SUCCESS;
        }
    }
}
