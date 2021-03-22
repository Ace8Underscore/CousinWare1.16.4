package io.ace.nordclient.hacks.movement;

import io.ace.nordclient.event.PacketEvent;
import io.ace.nordclient.hacks.Hack;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.server.SEntityVelocityPacket;

public class Velocity extends Hack {

    public Velocity() {
        super("Velocity", Category.MOVEMENT, 8760553, null);
    }

    @EventHandler
    private final Listener<PacketEvent.Receive> listener = new Listener<>(event -> {
        if (mc.player == null || mc.world == null) return;
        if (event.getPacket() instanceof SEntityVelocityPacket) {
            if (((SEntityVelocityPacket) event.getPacket() == null)) return;
            if(((SEntityVelocityPacket) event.getPacket()).getEntityID() == mc.player.getEntityId())
                event.setCanceled(true);
        }
        if(event.getPacket() instanceof SEntityVelocityPacket)
            event.setCanceled(true);

    });

}
