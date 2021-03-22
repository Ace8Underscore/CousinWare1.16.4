package io.ace.nordclient.utilz;

import net.minecraft.client.Minecraft;

public class MotionUtil {

    private static final Minecraft mc = Minecraft.getInstance();

    public static boolean isMoving() {
        return mc.player.getMotion().getX() > .05 || mc.player.getMotion().getX() < -.05 || mc.player.getMotion().getZ() > .05 || mc.player.getMotion().getZ() < -.05;
    }

    public static void setMotionX(double motionX) {
        mc.player.setMotion(motionX, mc.player.getMotion().getY(), mc.player.getMotion().getZ());
    }

    public static void setMotionY(double motionY) {
        mc.player.setMotion(mc.player.getMotion().getX(), motionY, mc.player.getMotion().getZ());
    }

    public static void setMotionZ(double motionZ) {
        mc.player.setMotion(mc.player.getMotion().getX(), mc.player.getMotion().getY(), motionZ);
    }

}
