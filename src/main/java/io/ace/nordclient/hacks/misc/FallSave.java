package io.ace.nordclient.hacks.misc;

import io.ace.nordclient.event.PacketEvent;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.utilz.BlockInteractionHelper;
import io.ace.nordclient.utilz.InventoryUtil;
import io.ace.nordclient.utilz.PlayerUtil;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CHeldItemChangePacket;
import net.minecraft.network.play.client.CPlayerPacket;

public class FallSave extends Hack {

    public FallSave() {
        super("FallSave", Category.MISC, 3441301, null);
    }
    int slotBefore;
    int slot;

    @Override
    public void onUpdate() {
        if (mc.world.getBlockState(PlayerUtil.getPlayerPos().down(1)).getMaterial().isReplaceable()) {
            mc.player.connection.sendPacket(new CHeldItemChangePacket(slot));
            BlockInteractionHelper.placeBlockScaffoldNoRotate(PlayerUtil.getPlayerPos().down(1));
            mc.player.connection.sendPacket(new CHeldItemChangePacket(slotBefore));
        }
       // this.disable();
    }

    @Override
    public void onEnable() {
        slotBefore = mc.player.inventory.currentItem;
        if (InventoryUtil.findRandomBlockInHotbar() == -1) {
            this.disable();
        } else {
            slot = InventoryUtil.findRandomBlockInHotbar();
            mc.player.rotationPitch += 0.0004;
        }

    }

    @EventHandler
    private final Listener<PacketEvent.Send> listener = new Listener<>(event -> {
        if (event.getPacket() instanceof CPlayerPacket) {
            ((CPlayerPacket) event.getPacket()).getPitch(90);
        }
    });
}
