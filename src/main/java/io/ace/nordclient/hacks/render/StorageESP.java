package io.ace.nordclient.hacks.render;

import io.ace.nordclient.event.RenderEvent;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingToggle;
import io.ace.nordclient.utilz.NordTessellator;
import io.ace.nordclient.utilz.RenderUtils;
import io.ace.nordclient.utilz.Setting;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.EnderChestTileEntity;
import net.minecraft.tileentity.ShulkerBoxTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ace________/Ace_#1233
 */

public class StorageESP extends Hack {

    Setting eChest;
    Setting chest;
    Setting shulker;

    private static final List<SettingBase> settings = Arrays.asList(
            new SettingToggle(true, "EChest"),
            new SettingToggle(true, "Chest"),
            new SettingToggle(true, "Shulker"));


    public StorageESP() {
        super("StorageESP", Category.RENDER, 1696806, settings);
    }

    @Override
    public void onWorldRender(RenderEvent event) {
        double d0 = mc.player.getPosX() - mc.worldRenderer.frustumUpdatePosZ;
        double d1 = mc.player.getPosY() - mc.worldRenderer.frustumUpdatePosY;
        double d2 = mc.player.getPosZ() - mc.worldRenderer.frustumUpdatePosZ;
        for (TileEntity e : mc.world.loadedTileEntityList) {
            if (e instanceof EnderChestTileEntity && settings.get(0).toToggle().state) {
                BlockPos eChestPos = e.getPos();
                NordTessellator.drawBoundingBoxChestBlockPos(event.getTes(), event.getBuffer(), event.getStack(), eChestPos, 1, 145, 43, 173, 255);
            }
            if (e instanceof ShulkerBoxTileEntity && settings.get(2).toToggle().state) {
                BlockPos shulkerPos = e.getPos();
                NordTessellator.drawBoundingBoxBlockPos(shulkerPos, 1, 243, 0, 127, 255);
            }
            if (e instanceof ChestTileEntity && settings.get(1).toToggle().state) {
                BlockPos chestPos = e.getPos();
                RenderUtils.drawLine(event.getStack(), chestPos.getX(), chestPos.getY(), chestPos.getZ(), chestPos.getX() + 1, chestPos.getY() + 1, chestPos.getZ() + 1, 243, 0, 127, 255);

            }
        }
    }
}
//

