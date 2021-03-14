package io.ace.nordclient.event;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by 086 on 11/12/2017.
 */
public class AddCollisionBoxToListEvent extends EventCancellable {
    private final Block block;
    private final BlockState state;
    private final World world;
    private final BlockPos pos;
    private final AxisAlignedBB entityBox;
    private final List<AxisAlignedBB> collidingBoxes;
    private final Entity entity;
    private final boolean bool;

    public AddCollisionBoxToListEvent(Block block, BlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean bool) {
        super();
        this.block = block;
        this.state = state;
        this.world = worldIn;
        this.pos = pos;
        this.entityBox = entityBox;
        this.collidingBoxes = collidingBoxes;
        this.entity = entityIn;
        this.bool = bool;
    }

    public Block getBlock() {
        return block;
    }

    public BlockState getState() {
        return state;
    }

    public World getWorld() {
        return world;
    }

    public BlockPos getPos() {
        return pos;
    }

    public AxisAlignedBB getEntityBox() {
        return entityBox;
    }

    public List<AxisAlignedBB> getCollidingBoxes() {
        return collidingBoxes;
    }

    public Entity getEntity() {
        return entity;
    }

    public boolean isBool() {
        return bool;
    }
}