package io.ace.nordclient.event;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.command.impl.data.BlockDataAccessor;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.SeparatePerspectiveModel;

public class EventRenderBlock extends EventCancellable {
    private BlockDataAccessor blockAccess;
    private SeparatePerspectiveModel.BakedModel bakedModel;
    private BlockState blockState;
    private BlockPos blockPos;
    private BufferBuilder bufferBuilder;
    private boolean checkSides;
    private long rand;
    private boolean renderable;

    public EventRenderBlock(final BlockDataAccessor blockAccess, final SeparatePerspectiveModel.BakedModel bakedModel, final BlockState blockState, final BlockPos blockPos, final BufferBuilder bufferBuilder, final boolean checkSides, final long rand) {
        this.blockAccess = blockAccess;
        this.bakedModel = bakedModel;
        this.blockState = blockState;
        this.blockPos = blockPos;
        this.bufferBuilder = bufferBuilder;
        this.checkSides = checkSides;
        this.rand = rand;
    }

    public BlockDataAccessor getBlockAccess() {
        return this.blockAccess;
    }

    public void setBlockAccess(final BlockDataAccessor blockAccess) {
        this.blockAccess = blockAccess;
    }

    public SeparatePerspectiveModel.BakedModel getBakedModel() {
        return this.bakedModel;
    }

    public void setBakedModel(final SeparatePerspectiveModel.BakedModel bakedModel) {
        this.bakedModel = bakedModel;
    }

    public BlockState getBlockState() {
        return this.blockState;
    }

    public void setBlockState(final BlockState blockState) {
        this.blockState = blockState;
    }

    public BlockPos getBlockPos() {
        return this.blockPos;
    }

    public void setBlockPos(final BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public BufferBuilder getBufferBuilder() {
        return this.bufferBuilder;
    }

    public void setBufferBuilder(final BufferBuilder bufferBuilder) {
        this.bufferBuilder = bufferBuilder;
    }

    public boolean isCheckSides() {
        return this.checkSides;
    }

    public void setCheckSides(final boolean checkSides) {
        this.checkSides = checkSides;
    }

    public long getRand() {
        return this.rand;
    }

    public void setRand(final long rand) {
        this.rand = rand;
    }

    public boolean isRenderable() {
        return this.renderable;
    }

    public void setRenderable(final boolean renderable) {
        this.renderable = renderable;
    }
}