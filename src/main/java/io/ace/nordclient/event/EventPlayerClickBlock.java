package io.ace.nordclient.event;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class EventPlayerClickBlock extends EventCancellable {
    public BlockPos Location;
    public Direction Facing;

    public EventPlayerClickBlock(BlockPos loc, Direction face) {
        Location = loc;
        Facing = face;
    }
}