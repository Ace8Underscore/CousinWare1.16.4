package io.ace.nordclient.utilz;

import net.minecraft.client.Minecraft;

public class MotionUtil {

    private static final Minecraft mc = Minecraft.getInstance();

    public static boolean isMoving() {
        return mc.player.getMotion().getX() > .05 || mc.player.getMotion().getX() < -.05 || mc.player.getMotion().getZ() > .05 || mc.player.getMotion().getZ() < -.05;
    }

}
