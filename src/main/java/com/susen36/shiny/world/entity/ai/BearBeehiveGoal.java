package com.susen36.shiny.world.entity.ai;

import com.susen36.shiny.world.entity.SPolarBearEntity;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class BearBeehiveGoal extends MoveToBlockGoal {

    private SPolarBearEntity bear;
    private int idleAtHiveTime = 0;
    private boolean isAboveDestinationBear;

    public BearBeehiveGoal(SPolarBearEntity bear) {
        super(bear, 1D, 32, 8);
        this.bear = bear;
    }

    public boolean canUse() {
        return !bear.isBaby() && super.canUse();
    }

    public void stop() {
        idleAtHiveTime = 0;
    }

    public double acceptedDistance() {
        return 2D;
    }

    public void tick() {
        super.tick();
        BlockPos blockpos = this.getMoveToTarget();
        if (!isWithinXZDist(blockpos)) {
            this.isAboveDestinationBear = false;
            ++this.tryTicks;
            if (this.shouldRecalculatePath()) {
                this.mob.getNavigation().moveTo((double) ((float) blockpos.getX()) + 0.5D, blockpos.getY(), (double) ((float) blockpos.getZ()) + 0.5D, this.speedModifier);
            }
        } else {
            this.isAboveDestinationBear = true;
            --this.tryTicks;
        }

        if (this.isReachedTarget() && Math.abs(bear.getY() - blockPos.getY()) <= 3) {
            bear.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(blockPos.getX() + 0.5D, blockPos.getY(), blockPos.getZ() + 0.5));
            if (bear.getY() + 2 < blockPos.getY()) {
                bear.setStanding(true);
            }
            if (this.idleAtHiveTime >= 20) {
                this.eatHive();
            } else {
                ++this.idleAtHiveTime;
            }
        }

    }

    private boolean isWithinXZDist(BlockPos blockpos) {
        return blockpos.closerToCenterThan(this.mob.position(), this.acceptedDistance());
    }

    protected boolean isReachedTarget() {
        return this.isAboveDestinationBear;
    }

    private void eatHive() {
        if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(bear.level, bear)) {
            BlockState blockstate = bear.level.getBlockState(this.blockPos);
            if (blockstate.is(Blocks.BEE_NEST)||blockstate.is(Blocks.BEEHIVE)) {

                    final RandomSource rand = this.bear.getRandom();
                    BeehiveBlockEntity beehivetileentity = (BeehiveBlockEntity) bear.level.getBlockEntity(this.blockPos);
                    if (beehivetileentity != null) {
                        beehivetileentity.emptyAllLivingFromHive(null, blockstate, BeehiveBlockEntity.BeeReleaseStatus.EMERGENCY);
                    }
                bear.level.updateNeighbourForOutputSignal(this.blockPos, blockstate.getBlock());
                    ItemStack stack = new ItemStack(Items.HONEYCOMB);
                    int level = 0;
                    if (blockstate.getBlock() instanceof BeehiveBlock) {
                        level = blockstate.getValue(BeehiveBlock.HONEY_LEVEL);
                    }
                    for (int i = 0; i < level; i++) {
                        ItemEntity itementity = new ItemEntity(bear.level, blockPos.getX() + rand.nextFloat(), blockPos.getY() + rand.nextFloat(), blockPos.getZ() + rand.nextFloat(), stack);
                        if(this.bear.level.random.nextInt(2)==0){
                            itementity.setDefaultPickUpDelay();
                            bear.level.addFreshEntity(itementity);}else {
                            bear.heal(4);
                        }
                    }
                    bear.level.destroyBlock(blockPos, false);
                    if (blockstate.getBlock() instanceof BeehiveBlock) {
                        bear.level.setBlockAndUpdate(blockPos, blockstate.setValue(BeehiveBlock.HONEY_LEVEL, 0));
                    }
                    double d0 = 15;
                    for (Bee bee : bear.level.getEntitiesOfClass(Bee.class, new AABB((double) blockPos.getX() - d0, (double) blockPos.getY() - d0, (double) blockPos.getZ() - d0, (double) blockPos.getX() + d0, (double) blockPos.getY() + d0, (double) blockPos.getZ() + d0))) {
                        bee.setRemainingPersistentAngerTime(100);
                        bee.setTarget(bear);
                        bee.setStayOutOfHiveCountdown(400);
                    }
                    stop();
                }
            }
    }

    @Override
    protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
            if (worldIn.getBlockEntity(pos) instanceof BeehiveBlockEntity && worldIn.getBlockState(pos).getBlock() instanceof BeehiveBlock) {
                int i = worldIn.getBlockState(pos).getValue(BeehiveBlock.HONEY_LEVEL);
                return i > 0;
            }
        return false;
    }
}