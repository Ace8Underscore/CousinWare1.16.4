package io.ace.nordclient.mixin.mixins;

import io.ace.nordclient.CousinWare;
import io.ace.nordclient.event.PacketEvent;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.IPacket;
import net.minecraft.network.NetworkManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetworkManager.class)
public abstract class MixinNetworkManager {

    @Inject(method = "sendPacket(Lnet/minecraft/network/IPacket;)V", at = @At("HEAD"), cancellable = true)
    private void onSendPacket(IPacket<?> packetIn, CallbackInfo ci) {
        PacketEvent.Send event = new PacketEvent.Send(packetIn);
        CousinWare.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    private void onChannelRead(ChannelHandlerContext p_channelRead0_1_, IPacket<?> p_channelRead0_2_, CallbackInfo ci) {
        PacketEvent.Receive event = new PacketEvent.Receive(p_channelRead0_2_);
        CousinWare.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

}
