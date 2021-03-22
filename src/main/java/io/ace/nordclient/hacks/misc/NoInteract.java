package io.ace.nordclient.hacks.misc;

import io.ace.nordclient.event.PacketEvent;
import io.ace.nordclient.hacks.Hack;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.CPlayerTryUseItemOnBlockPacket;
import net.minecraft.network.play.client.CPlayerTryUseItemPacket;
import net.minecraft.tileentity.*;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

/**
 * @author Ace________/Ace_#1233
 */

public class NoInteract extends Hack {

    public NoInteract() {
        super("NoInteract", Category.MISC, 16280575, null);
    }

    @EventHandler
    private final Listener<PacketEvent.Send> listener = new Listener<>(event -> {
        if (mc.world == null || mc.player == null)
            return;
        if (mc.player.getHeldItemMainhand().getItem().equals(Items.GOLDEN_APPLE) || mc.player.getHeldItemOffhand().getItem().equals(Items.GOLDEN_APPLE)) {
            if (event.getPacket() instanceof CPlayerTryUseItemOnBlockPacket) {
                for (TileEntity entity : mc.world.loadedTileEntityList) {
                    if (entity instanceof EnderChestTileEntity || entity instanceof BeaconTileEntity || entity instanceof FurnaceTileEntity || entity instanceof HopperTileEntity || entity instanceof ChestTileEntity)
                        if (new BlockPos(mc.objectMouseOver.getHitVec().getX(), mc.objectMouseOver.getHitVec().getY(), mc.objectMouseOver.getHitVec().getZ()).equals(entity.getPos())) {
                            if (mc.gameSettings.keyBindUseItem.isKeyDown()) {
                                event.setCanceled(true);
                                mc.getConnection().sendPacket(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));


                            }
                        }
                    //
                }
                if (mc.world.getBlockState(new BlockPos(mc.objectMouseOver.getHitVec().getX(), mc.objectMouseOver.getHitVec().getY(), mc.objectMouseOver.getHitVec().getZ())).getBlock().equals(Blocks.ANVIL)) {
                    if (mc.gameSettings.keyBindUseItem.isKeyDown()) {
                        event.setCanceled(true);
                        mc.getConnection().sendPacket(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));


                    }
                }

            }
        }


    });
}
