package io.ace.nordclient.hacks.misc;

import io.ace.nordclient.command.Command;
import io.ace.nordclient.event.PacketEvent;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingMode;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CChatMessagePacket;

import java.util.Arrays;
import java.util.List;

public class ChatSuffix extends Hack {


    String suffix = "";
//private static final List<SettingBase> settings = Arrays.asList(new SettingMode("Mode: ", "Auto", "Looking"), new SettingMode("Protect: ", "Off", "Hopper", "Obby"), new SettingToggle(true, "Aura"), new SettingSlider(0.0D, 20.0D, 10.0D, 0, "CPS: "), new SettingMode("CPS: ", "Clicks/Sec", "Clicks/Tick", "Tick Delay"), new SettingToggle(false, "SafeShuker"), new SettingToggle(false, "AntiAim"), new SettingToggle(true, "2b Bypass"));
private static final List<SettingBase> settings = Arrays.asList(new SettingMode("Mode", "2b2t", "AceHack", "CousinWare", "Dungannon"));

    //private Value<String> mode;

    public ChatSuffix() {
        super("ChatSuffix", Category.MISC, 13319279, settings);
    }

    public void onEnable() {

    }



    @EventHandler
    private final Listener<PacketEvent.Send> listener = new Listener<>(event -> {
        if (event.getPacket() instanceof CChatMessagePacket) {
            if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("2b2t")) {
                suffix = " | CousinWare";
            }
            if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("AceHack")) {
                suffix = " \uFF5C \u1d00\u1d04\u1d07\u029c\u1d00\u1d04\u1d0b";
            }
            if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("CousinWare")) {
                suffix = " \uFF5C \u1d04\u1d0f\u1d1c\ua731\u026a\u0274\u1d21\u1d00\u0280\u1d07";
            }
            if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("Dungannon")) {
                suffix = " \uFF5C \u1d05\u1d1c\u0274\u0262\u1d00\u0274\u0274\u1d0f\u0274";
            }
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
