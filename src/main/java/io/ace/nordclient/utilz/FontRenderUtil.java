package io.ace.nordclient.utilz;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.ace.nordclient.CousinWare;
import net.minecraft.client.Minecraft;

/**
 * @author Ace________/Ace_#1233
 */

public class FontRenderUtil {
    private static final Minecraft mc = Minecraft.getInstance();



    public static float drawCenteredStringWithShadow(MatrixStack matrixStack, String text, float x, float y, int color) {
        return mc.fontRenderer.drawStringWithShadow(matrixStack, text, x - mc.fontRenderer.getStringWidth(text) / 2, y, color);
    }

    public static float drawCenteredString(MatrixStack matrixStack, String text, float x, float y, int color) {
        return mc.fontRenderer.drawString(matrixStack, text, (int) x - mc.fontRenderer.getStringWidth(text) / 2, (int) y, color);
    }

    public static float drawLeftStringWithShadow(MatrixStack matrixStack, String text, float x, float y, int color) {
        return mc.fontRenderer.drawStringWithShadow(matrixStack, text, x - mc.fontRenderer.getStringWidth(text), y, color);
    }

    public static float drawLeftString(MatrixStack matrixStack, String text, float x, float y, int color) {
        return mc.fontRenderer.drawString(matrixStack, text, mc.fontRenderer.getStringWidth(text) - (int) x, (int) y, color);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static float drawCenteredStringWithShadowCustom(String text, float x, float y, int color) {
        return CousinWare.INSTANCE.fontRenderer.drawStringWithShadow(text, x - CousinWare.INSTANCE.fontRenderer.getStringWidth(text) / 2, y, color);
    }

    public static float drawCenteredStringCustom(String text, float x, float y, int color) {
        return CousinWare.INSTANCE.fontRenderer.drawString(text, (int) x - CousinWare.INSTANCE.fontRenderer.getStringWidth(text) / 2, (int) y, color);
    }

    public static float drawLeftStringWithShadowCustom(String text, float x, float y, int color) {
        return CousinWare.INSTANCE.fontRenderer.drawStringWithShadow(text, x - CousinWare.INSTANCE.fontRenderer.getStringWidth(text), y, color);
    }

    public static float drawLeftStringCustom(String text, float x, float y, int color) {
        return CousinWare.INSTANCE.fontRenderer.drawString(text, CousinWare.INSTANCE.fontRenderer.getStringWidth(text) - (int) x, (int) y, color);
    }

}

