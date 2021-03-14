package io.ace.nordclient.managers;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;

/**
 * @author Ace________/Ace_#1233
 */

public class RotationManager {

    private static final Minecraft mc = Minecraft.getInstance();

    public static double[] calculateLookAt(final double px, final double py, final double pz, final PlayerEntity me) {
        double dirx = me.getPosX() - px;
        double diry = me.getPosY() - py;
        double dirz = me.getPosZ() - pz;
        final double len = Math.sqrt(dirx * dirx + diry * diry + dirz * dirz);
        dirx /= len;
        diry /= len;
        dirz /= len;
        double pitch = Math.asin(diry);
        double yaw = Math.atan2(dirz, dirx);
        pitch = pitch * 180.0 / 3.141592653589793;
        yaw = yaw * 180.0 / 3.141592653589793;
        yaw += 90.0;
        return new double[]{yaw, pitch};
    }
}
