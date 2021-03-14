package io.ace.nordclient.utilz;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;

public class PlayerUtil {

    private final static Minecraft mc = Minecraft.getInstance();

    public static BlockPos getPlayerPos() {
        return new BlockPos(mc.player.getPosX(), mc.player.getPosY(), mc.player.getPosZ());
    }
}
