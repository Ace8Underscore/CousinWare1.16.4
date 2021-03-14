package io.ace.nordclient.event;


import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.PacketDispatcher;

public class PacketEvent extends EventCancellable {

    /**
     * @author zeroeightsix/kami
     */

    private final IPacket packet;

    public PacketEvent(IPacket packet) {
        this.packet = packet;
    }

    public IPacket getPacket() {
        return packet;
    }

    public static class Receive extends PacketEvent {
        public Receive(IPacket packet) {
            super(packet);
        }
    }

    public static class Send extends PacketEvent {
        public Send(IPacket packet) {
            super(packet);
        }
    }
}