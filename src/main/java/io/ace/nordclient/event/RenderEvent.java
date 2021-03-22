package io.ace.nordclient.event;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.math.vector.Vector3d;

public class RenderEvent {
    private static MatrixStack stack;
    private static Vector3d renderPos;
    private static float partialTicks;
    private static final RenderEvent INSTANCE = new RenderEvent(stack, renderPos, partialTicks);

    public RenderEvent(MatrixStack stack, Vector3d renderPos, float partialTicks) {
        RenderEvent.stack = stack;
        RenderEvent.renderPos = renderPos;
        RenderEvent.partialTicks = partialTicks;
    }

    public BufferBuilder getBuffer() {
        return Tessellator.getInstance().getBuffer();
    }

    public MatrixStack getStack() { return stack;}

    public Tessellator getTes() {return Tessellator.getInstance();}

   /* public double getScreenWidth() {
        return resolution.getScaledWidth();
    }

    public double getScreenHeight() {
        return resolution.getScaledHeight();
    }

    public BufferBuilder getBuffer() {
        return tessellator.getBuffer();
    }

    public void setTranslation(Vector3d translation) {
        getBuffer().setTranslation(-translation.x, -translation.y, -translation.z);
    }

    public void resetTranslation() {
        setTranslation();
    }
    /*

    public float getPartialTicks() {
        return partialTicks;
    } */
}
