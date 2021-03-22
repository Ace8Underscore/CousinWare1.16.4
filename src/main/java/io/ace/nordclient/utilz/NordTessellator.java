package io.ace.nordclient.utilz;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import org.lwjgl.opengl.GL11;

public class NordTessellator
        extends Tessellator {
    public static NordTessellator INSTANCE = new NordTessellator();

    public NordTessellator() {
        super(2097152);
    }

    public static void prepare(int mode2) {
        NordTessellator.prepareGL();
        NordTessellator.begin(mode2);
    }

    public static void prepareGL() {
        GL11.glBlendFunc(770, 771);
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA.param, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA.param, GlStateManager.SourceFactor.ONE.param,  GlStateManager.DestFactor.ZERO.param);
        RenderSystem.lineWidth((float) 1.5f);
        RenderSystem.disableTexture();
        RenderSystem.depthMask((boolean) false);
        RenderSystem.enableBlend();
        RenderSystem.disableDepthTest();
        RenderSystem.disableLighting();
        RenderSystem.disableCull();
        RenderSystem.enableAlphaTest();
        RenderSystem.color4f((float) 1.0f, (float) 1.0f, (float) 1.0f, 1);
    }

    public static void begin(int mode2) {
        INSTANCE.getBuffer().begin(mode2, DefaultVertexFormats.POSITION_COLOR);
    }

    public static void release() {
        NordTessellator.render();
        NordTessellator.releaseGL();
    }

    public static void render() {
        INSTANCE.draw();
    }

    public static void releaseGL() {
        RenderSystem.enableCull();
        RenderSystem.depthMask((boolean) true);
        RenderSystem.enableTexture();
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
    }

    public static void drawBox(BlockPos blockPos, int argb, int sides) {
        int a = argb >>> 24 & 255;
        int r = argb >>> 16 & 255;
        int g = argb >>> 8 & 255;
        int b = argb & 255;
        NordTessellator.drawBox(blockPos, r, g, b, a, sides);
    }

    public static void drawBox(double x, double y, double z, int argb, int sides) {
        int a = argb >>> 24 & 255;
        int r = argb >>> 16 & 255;
        int g = argb >>> 8 & 255;
        int b = argb & 255;
        NordTessellator.drawBox(INSTANCE.getBuffer(), x, y, z, 1.0f, 1.0f, 1.0f, r, g, b, a, sides);
    }

    public static void drawBoxSmall(double x, double y, double z, int argb, int sides) {
        int a = argb >>> 24 & 255;
        int r = argb >>> 16 & 255;
        int g = argb >>> 8 & 255;
        int b = argb & 255;
        NordTessellator.drawBox(INSTANCE.getBuffer(), x, y, z, 0.25f, 0.25f, 0.25f, r, g, b, a, sides);
    }

    public static void drawBox(BlockPos blockPos, int r, int g, int b, int a, int sides) {
        NordTessellator.drawBox(INSTANCE.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0f, 1.0f, 1.0f, r, g, b, a, sides);
    }

    public static BufferBuilder getBufferBuilder() {
        return INSTANCE.getBuffer();
    }

    public static void drawBox(BufferBuilder buffer, double x, double y, double z, float w, float h, float d, int r, int g, int b, int a, int sides) {
        if ((sides & 1) != 0) {
            buffer.pos(x + (double) w, y, z).color(r, g, b, a).endVertex();
            buffer.pos(x + (double) w, y, z + (double) d).color(r, g, b, a).endVertex();
            buffer.pos(x, y, z + (double) d).color(r, g, b, a).endVertex();
            buffer.pos(x, y, z).color(r, g, b, a).endVertex();
        }
        if ((sides & 2) != 0) {
            buffer.pos(x + (double) w, y + (double) h, z).color(r, g, b, a).endVertex();
            buffer.pos(x, y + (double) h, z).color(r, g, b, a).endVertex();
            buffer.pos(x, y + (double) h, z + (double) d).color(r, g, b, a).endVertex();
            buffer.pos(x + (double) w, y + (double) h, z + (double) d).color(r, g, b, a).endVertex();
        }
        if ((sides & 4) != 0) {
            buffer.pos(x + (double) w, y, z).color(r, g, b, a).endVertex();
            buffer.pos(x, y, z).color(r, g, b, a).endVertex();
            buffer.pos(x, y + (double) h, z).color(r, g, b, a).endVertex();
            buffer.pos(x + (double) w, y + (double) h, z).color(r, g, b, a).endVertex();
        }
        if ((sides & 8) != 0) {
            buffer.pos(x, y, z + (double) d).color(r, g, b, a).endVertex();
            buffer.pos(x + (double) w, y, z + (double) d).color(r, g, b, a).endVertex();
            buffer.pos(x + (double) w, y + (double) h, z + (double) d).color(r, g, b, a).endVertex();
            buffer.pos(x, y + (double) h, z + (double) d).color(r, g, b, a).endVertex();
        }
        if ((sides & 16) != 0) {
            buffer.pos(x, y, z).color(r, g, b, a).endVertex();
            buffer.pos(x, y, z + (double) d).color(r, g, b, a).endVertex();
            buffer.pos(x, y + (double) h, z + (double) d).color(r, g, b, a).endVertex();
            buffer.pos(x, y + (double) h, z).color(r, g, b, a).endVertex();
        }
        if ((sides & 32) != 0) {
            buffer.pos(x + (double) w, y, z + (double) d).color(r, g, b, a).endVertex();
            buffer.pos(x + (double) w, y, z).color(r, g, b, a).endVertex();
            buffer.pos(x + (double) w, y + (double) h, z).color(r, g, b, a).endVertex();
            buffer.pos(x + (double) w, y + (double) h, z + (double) d).color(r, g, b, a).endVertex();
        }
    }

    public static void drawLines(BufferBuilder buffer, float x, float y, float z, float w, float h, float d, int r, int g, int b, int a, int sides) {
        if ((sides & 17) != 0) {
            buffer.pos(x, y, z).color(r, g, b, a).endVertex();
            buffer.pos(x, y, z + d).color(r, g, b, a).endVertex();
        }
        if ((sides & 18) != 0) {
            buffer.pos(x, y + h, z).color(r, g, b, a).endVertex();
            buffer.pos(x, y + h, z + d).color(r, g, b, a).endVertex();
        }
        if ((sides & 33) != 0) {
            buffer.pos(x + w, y, z).color(r, g, b, a).endVertex();
            buffer.pos(x + w, y, z + d).color(r, g, b, a).endVertex();
        }
        if ((sides & 34) != 0) {
            buffer.pos(x + w, y + h, z).color(r, g, b, a).endVertex();
            buffer.pos(x + w, y + h, z + d).color(r, g, b, a).endVertex();
        }
        if ((sides & 5) != 0) {
            buffer.pos(x, y, z).color(r, g, b, a).endVertex();
            buffer.pos(x + w, y, z).color(r, g, b, a).endVertex();
        }
        if ((sides & 6) != 0) {
            buffer.pos(x, y + h, z).color(r, g, b, a).endVertex();
            buffer.pos(x + w, y + h, z).color(r, g, b, a).endVertex();
        }
        if ((sides & 9) != 0) {
            buffer.pos(x, y, z + d).color(r, g, b, a).endVertex();
            buffer.pos(x + w, y, z + d).color(r, g, b, a).endVertex();
        }
        if ((sides & 10) != 0) {
            buffer.pos(x, y + h, z + d).color(r, g, b, a).endVertex();
            buffer.pos(x + w, y + h, z + d).color(r, g, b, a).endVertex();
        }
        if ((sides & 20) != 0) {
            buffer.pos(x, y, z).color(r, g, b, a).endVertex();
            buffer.pos(x, y + h, z).color(r, g, b, a).endVertex();
        }
        if ((sides & 36) != 0) {
            buffer.pos(x + w, y, z).color(r, g, b, a).endVertex();
            buffer.pos(x + w, y + h, z).color(r, g, b, a).endVertex();
        }
        if ((sides & 24) != 0) {
            buffer.pos(x, y, z + d).color(r, g, b, a).endVertex();
            buffer.pos(x, y + h, z + d).color(r, g, b, a).endVertex();
        }
        if ((sides & 40) != 0) {
            buffer.pos(x + w, y, z + d).color(r, g, b, a).endVertex();
            buffer.pos(x + w, y + h, z + d).color(r, g, b, a).endVertex();
        }
    }

    public static void drawRectangle(float x, float y, float w, float h, int color) {
        float r = (float) (color >> 16 & 255) / 255.0f;
        float g = (float) (color >> 8 & 255) / 255.0f;
        float b = (float) (color & 255) / 255.0f;
        float a = (float) (color >> 24 & 255) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.blendFuncSeparate((int) 770, (int) 771, (int) 1, (int) 0);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(x, h, 0.0).color(r, g, b, a).endVertex();
        bufferbuilder.pos(w, h, 0.0).color(r, g, b, a).endVertex();
        bufferbuilder.pos(w, y, 0.0).color(r, g, b, a).endVertex();
        bufferbuilder.pos(x, y, 0.0).color(r, g, b, a).endVertex();
        tessellator.draw();
        RenderSystem.disableBlend();
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public static void drawBoundingBoxBlockPos(BlockPos bp, float width, int r, int g, int b, int alpha) {
        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();
        RenderSystem.disableDepthTest();
        RenderSystem.blendFuncSeparate((int) 770, (int) 771, (int) 0, (int) 1);
        RenderSystem.disableTexture();
        RenderSystem.depthMask((boolean) false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(width);
        Minecraft mc = Minecraft.getInstance();

        //double x = bp.getX() - (mc.player.lastTickPosX + (mc.player.getPosX() - mc.player.lastTickPosX) * (double)mc.getRenderPartialTicks());
        //double y = bp.getY() - (mc.player.lastTickPosY + (mc.player.getPosY() - mc.player.lastTickPosY) * (double)mc.getRenderPartialTicks());
       // double z = bp.getZ() - (mc.player.lastTickPosZ + (mc.player.getPosZ() - mc.player.lastTickPosZ) * (double)mc.getRenderPartialTicks());

        double x = (double) bp.getX() - mc.getRenderManager().info.getProjectedView().x;
        double y = (double) bp.getY() - mc.getRenderManager().info.getProjectedView().y;
        double z = (double) bp.getZ() - mc.getRenderManager().info.getProjectedView().z;
        AxisAlignedBB bb = new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        tessellator.draw();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        tessellator.draw();
        bufferbuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
        tessellator.draw();
        GL11.glDisable(2848);
        RenderSystem.depthMask((boolean) true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        RenderSystem.popMatrix();
    }

    public static void drawBoundingBoxBottomBlockPos(BlockPos bp, float width, int r, int g, int b, int alpha) {
        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();
        RenderSystem.disableDepthTest();
        RenderSystem.blendFuncSeparate(770, 771, 0, 1);
        RenderSystem.disableTexture();
        RenderSystem.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(width);
        Minecraft mc = Minecraft.getInstance();
        double x = (double) bp.getX() - mc.getRenderManager().info.getProjectedView().x;
        double y = (double) bp.getY() - mc.getRenderManager().info.getProjectedView().y;
        double z = (double) bp.getZ() - mc.getRenderManager().info.getProjectedView().z;
        AxisAlignedBB bb = new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0);
        net.minecraft.client.renderer.Tessellator tessellator = net.minecraft.client.renderer.Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        tessellator.draw();
        GL11.glDisable(2848);
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        RenderSystem.popMatrix();
    }

    public static void drawBoundingBoxBlockPosHalf(BlockPos bp, float width, int r, int g, int b, int alpha) {
        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();
        RenderSystem.disableDepthTest();
        RenderSystem.blendFuncSeparate((int) 770, (int) 771, (int) 0, (int) 1);
        RenderSystem.disableTexture();
        RenderSystem.depthMask((boolean) false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(width);
        Minecraft mc = Minecraft.getInstance();
        double x = (double) bp.getX() - mc.getRenderManager().info.getProjectedView().x;
        double y = (double) bp.getY() - mc.getRenderManager().info.getProjectedView().y;
        double z = (double) bp.getZ() - mc.getRenderManager().info.getProjectedView().z;
        AxisAlignedBB bb = new AxisAlignedBB(x, y, z, x + 1.0, y + .5, z + 1.0);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        tessellator.draw();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        tessellator.draw();
        bufferbuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
        tessellator.draw();
        GL11.glDisable(2848);
        RenderSystem.depthMask((boolean) true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        RenderSystem.popMatrix();
    }

    public static void drawBoundingBottomBoxBlockPos(BlockPos bp, float width, int r, int g, int b, int alpha) {
        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();
        RenderSystem.disableDepthTest();
        RenderSystem.blendFuncSeparate((int) 770, (int) 771, (int) 0, (int) 1);
        RenderSystem.disableTexture();
        RenderSystem.depthMask((boolean) false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(width);
        Minecraft mc = Minecraft.getInstance();
        double x = (double) bp.getX() - mc.getRenderManager().info.getProjectedView().x;
        double y = (double) bp.getY() - mc.getRenderManager().info.getProjectedView().y;
        double z = (double) bp.getZ() - mc.getRenderManager().info.getProjectedView().z;
        AxisAlignedBB bb = new AxisAlignedBB(x, y, z, x + 1.0, y + 0.0, z + 1.0);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        tessellator.draw();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        tessellator.draw();
        bufferbuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
        tessellator.draw();
        GL11.glDisable(2848);
        RenderSystem.depthMask((boolean) true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        RenderSystem.popMatrix();
    }

    public static void drawBoundingBoxChestBlockPos(Tessellator tessellator, BufferBuilder bufferBuilder, MatrixStack matrixStack, BlockPos bp, float width, int r, int g, int b, int alpha) {
        matrixStack.push();
        prepareGL();
        Minecraft mc = Minecraft.getInstance();
        Entity entity = mc.getRenderViewEntity();
        //double d0 = mc.player.getPosX() - mc.worldRenderer.frustumUpdatePosZ;
        //double d1 = mc.player.getPosY() - mc.worldRenderer.frustumUpdatePosY;
        //double d2 = mc.player.getPosZ() - mc.worldRenderer.frustumUpdatePosZ;
        //double x = (double) bp.getX() + .06 - mc.getRenderManager().info.getRenderViewEntity().getPosX();
        //double y = (double) bp.getY() - mc.getRenderManager().info.getRenderViewEntity().getPosY();
        //double z = (double) bp.getZ() + .06 - mc.getRenderManager().info.getRenderViewEntity().getPosZ();
//no work below kinda work above
        //double x = (double) bp.getX() + .06 - mc.getRenderManager().info.getUpVector().getX();
        //double y = (double) bp.getY() - mc.getRenderManager().info.getUpVector().getY();
        //double z = (double) bp.getZ() + .06 - mc.getRenderManager().info.getUpVector().getZ();
        //Vector4f vec4f = new Vector4f((float)mc.player.getPosX(), (float)mc.player.getPosY(), (float)mc.player.getPosZ(), 1);
        //vec4f.transform();
        double x1 = mc.getRenderManager().info.getProjectedView().getX();
        double y1 = mc.getRenderManager().info.getProjectedView().getY();
        double z1 = mc.getRenderManager().info.getProjectedView().getZ();
        matrixStack.translate(x1, y1, z1);
// below kinda wok
        double x = (double) bp.getX() + .06 - x1;
        double y = (double) bp.getY() - y1;
        double z = (double) bp.getZ() + .06 - z1;
        matrixStack.translate(x, y, z);

        AxisAlignedBB bb = new AxisAlignedBB(x, y, z, x + .881, y + .875, z + .881);
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferBuilder.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferBuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferBuilder.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferBuilder.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        tessellator.draw();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferBuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferBuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferBuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferBuilder.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        tessellator.draw();
        bufferBuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferBuilder.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferBuilder.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferBuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferBuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferBuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferBuilder.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferBuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
        tessellator.draw();
        releaseGL();
        matrixStack.pop();
    }

    public static void drawBoundingBoxItem(double xloc, double yloc, double zloc, float width, int r, int g, int b, int alpha) {
        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();
        RenderSystem.disableDepthTest();
        RenderSystem.blendFuncSeparate((int) 770, (int) 771, (int) 0, (int) 1);
        RenderSystem.disableTexture();
        RenderSystem.depthMask((boolean) false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(width);
        Minecraft mc = Minecraft.getInstance();
        double x = xloc - .2 - mc.getRenderManager().info.getProjectedView().x;
        double y = yloc - mc.getRenderManager().info.getProjectedView().y;
        double z = zloc - .2 - mc.getRenderManager().info.getProjectedView().z;
        AxisAlignedBB bb = new AxisAlignedBB(x, y, z, x + .4, y + .4, z + .4);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        tessellator.draw();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        tessellator.draw();
        bufferbuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
        tessellator.draw();
        GL11.glDisable(2848);
        RenderSystem.depthMask((boolean) true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        RenderSystem.popMatrix();
    }

    public static void drawBoundingBoxFace(final AxisAlignedBB bb, final float width, final int red, final int green, final int blue, final int alpha) {
        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();
        RenderSystem.disableDepthTest();
        RenderSystem.blendFuncSeparate(770, 771, 0, 1);
        RenderSystem.disableTexture();
        RenderSystem.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(width);
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        GL11.glDisable(2848);
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        RenderSystem.popMatrix();
    }

    public static void drawFullFace(final AxisAlignedBB bb, final BlockPos blockPos, final float width, final int red, final int green, final int blue, final int alpha, final int alpha2) {
        prepare(7);
        drawFace(blockPos, red, green, blue, alpha, 63);
        release();
        drawBoundingBoxFace(bb, width, red, green, blue, alpha2);
    }

    public static void drawFace(final BlockPos blockPos, final int argb, final int sides) {
        final int a = argb >>> 24 & 0xFF;
        final int r = argb >>> 16 & 0xFF;
        final int g = argb >>> 8 & 0xFF;
        final int b = argb & 0xFF;
        drawFace(blockPos, r, g, b, a, sides);
    }

    public static void drawBoxBottom(BufferBuilder buffer, float x, float y, float z, float w, float d, int r, int g, int b, int a) {
        buffer.pos((x + w), y, z).color(r, g, b, a).endVertex();
        buffer.pos((x + w), y, (z + d)).color(r, g, b, a).endVertex();
        buffer.pos(x, y, (z + d)).color(r, g, b, a).endVertex();
        buffer.pos(x, y, z).color(r, g, b, a).endVertex();
    }

    public static void drawBoxBottom(BlockPos blockPos, int r, int g, int b, int a) {
        drawBoxBottom(INSTANCE.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0f, 1.0f, r, g, b, a);
    }

    public static void drawFace(final BlockPos blockPos, final int r, final int g, final int b, final int a, final int sides) {
        drawFace(INSTANCE.getBuffer(), (float) blockPos.getX(), (float) blockPos.getY(), (float) blockPos.getZ(), 1.0f, 1.0f, 1.0f, r, g, b, a, sides);
    }

    public static void drawFace(final BufferBuilder buffer, final float x, final float y, final float z, final float w, final float h, final float d, final int r, final int g, final int b, final int a, final int sides) {
        if ((sides & 0x1) != 0x0) {
            buffer.pos(x + w, y, z).color(r, g, b, a).endVertex();
            buffer.pos(x + w, y, z + d).color(r, g, b, a).endVertex();
            buffer.pos(x, y, z + d).color(r, g, b, a).endVertex();
            buffer.pos(x, y, z).color(r, g, b, a).endVertex();
        }
    }

    public static Vector3d getInterpolatedPos(Entity entity, float ticks) {
        return new Vector3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(getInterpolatedAmount(entity, ticks));
    }

    public static Vector3d getInterpolatedAmount(Entity entity, double ticks) {
        return getInterpolatedAmount(entity, ticks, ticks, ticks);
    }

    public static Vector3d getInterpolatedAmount(Entity entity, double x, double y, double z) {
        return new Vector3d(
                (entity.getPosX() - entity.lastTickPosX) * x,
                (entity.getPosY() - entity.lastTickPosY) * y,
                (entity.getPosZ() - entity.lastTickPosZ) * z
        );
    }
}