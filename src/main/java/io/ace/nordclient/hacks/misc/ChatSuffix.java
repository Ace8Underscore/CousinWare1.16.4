package io.ace.nordclient.hacks.misc;

import io.ace.nordclient.CousinWare;
import io.ace.nordclient.command.Command;
import io.ace.nordclient.event.PacketEvent;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.utilz.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CChatMessagePacket;

import java.util.ArrayList;

public class ChatSuffix extends Hack {

    Setting mode;
    String suffix = "";

    public ChatSuffix() {
        super("ChatSuffix", Category.MISC, 13319279);
        ArrayList<String> modes = new ArrayList<>();
        modes.add("2b2t");
        modes.add("CousinWare");
        modes.add("AceHack");
        modes.add("Dungannon");
        //CousinWare.INSTANCE.settingsManager.rSetting(mode = new Setting("Mode", this, "2b2t", modes, "ChatSuffixModes"));


    }



    @EventHandler
    private final Listener<PacketEvent.Send> listener = new Listener<>(event -> {
        if (event.getPacket() instanceof CChatMessagePacket) {
           // if (mode.getValString().equalsIgnoreCase("2b2t")) {
                suffix = " | CousinWare";
           // }
            //if (mode.getValString().equalsIgnoreCase("AceHack")) {
           //     suffix = " \uFF5C \u1d00\u1d04\u1d07\u029c\u1d00\u1d04\u1d0b";
           // }
           // if (mode.getValString().equalsIgnoreCase("CousinWare")) {
            //    suffix = " \uFF5C \u1d04\u1d0f\u1d1c\ua731\u026a\u0274\u1d21\u1d00\u0280\u1d07";
            //}
          // if (mode.getValString().equalsIgnoreCase("Dungannon")) {
           //     suffix = " \uFF5C \u1d05\u1d1c\u0274\u0262\u1d00\u0274\u0274\u1d0f\u0274";
          //  }
            String message = ((CChatMessagePacket) event.getPacket()).getMessage();
            if (!message.contains(suffix) && !message.startsWith("/") && !message.startsWith(Command.getClientPrefix())) {
                event.setCanceled(true);
                mc.player.sendChatMessage(message + suffix);
            }
            //String message = ((CPacketChatMessage) event.getPacket()).getMessage();
            //mc.player.sendChatMessage(message + " | CousinWare");
        }
    });
}
