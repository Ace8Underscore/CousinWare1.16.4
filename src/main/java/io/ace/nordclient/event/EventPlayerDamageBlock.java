package io.ace.nordclient.event;

public class EventPlayerDamageBlock extends EventCancellable {
    private final net.minecraft.util.math.BlockPos BlockPos;
    private net.minecraft.util.Direction Direction;

    public EventPlayerDamageBlock(net.minecraft.util.math.BlockPos posBlock, net.minecraft.util.Direction directionFacing) {
        BlockPos = posBlock;
        setDirection(directionFacing);
    }

    public net.minecraft.util.math.BlockPos getPos() {
        return BlockPos;
    }

    /**
     * @return the direction
     */
    public net.minecraft.util.Direction getDirection() {
        return Direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(net.minecraft.util.Direction direction) {
        Direction = direction;
    }

}