package io.ace.nordclient.hacks.combat;

import io.ace.nordclient.event.PacketEvent;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingMode;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CPlayerPacket;
import net.minecraft.network.play.client.CUseEntityPacket;

import java.util.Arrays;
import java.util.List;

public class Criticals extends Hack {

    private static final List<SettingBase> settings = Arrays.asList(
            new SettingMode("CritMode", "Normal", "Strict", "John", "Extra"));

    public Criticals() {
        super("Critcals", Category.COMBAT, 4241221, settings);
    }

    @EventHandler
    private final Listener<PacketEvent.Send> listener = new Listener<>(event -> {
        if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("normal")) {
            if (event.getPacket() instanceof CUseEntityPacket) {
                final CUseEntityPacket packet = (CUseEntityPacket) event.getPacket();
                if (packet.getAction() == CUseEntityPacket.Action.ATTACK && mc.player.isOnGround()) {
                    mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY() + 0.10000000149011612, mc.player.getPosZ(), false));
                    mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY(), mc.player.getPosZ(), false));

                }
            }
        }
        if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("strict")) {
            if (event.getPacket() instanceof CUseEntityPacket) {
                final CUseEntityPacket packet = (CUseEntityPacket) event.getPacket();
                if (packet.getAction() == CUseEntityPacket.Action.ATTACK && mc.player.isOnGround()) {
                    mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY() + 0.06260280169278, mc.player.getPosZ(), false));
                    mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY() + 0.0726027996066, mc.player.getPosZ(), false));
                    mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY(), mc.player.getPosZ(), false));

                }
            }
        }
        if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("john")) {
            if (event.getPacket() instanceof CUseEntityPacket) {
                final CUseEntityPacket packet = (CUseEntityPacket) event.getPacket();
                if (packet.getAction() == CUseEntityPacket.Action.ATTACK && mc.player.isOnGround()) {
                    mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY() + 0.08260280169278, mc.player.getPosZ(), false));
                    mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY() + 0.0826027996066, mc.player.getPosZ(), false));
                    mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY(), mc.player.getPosZ(), false));

                }
            }
        }
        if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("extra")) {
            if (event.getPacket() instanceof CUseEntityPacket) {
                final CUseEntityPacket packet = (CUseEntityPacket) event.getPacket();
                if (packet.getAction() == CUseEntityPacket.Action.ATTACK && mc.player.isOnGround()) {
                    mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY() + 0.06260280169278, mc.player.getPosZ(), false));
                    mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY() + 0.0726027996066, mc.player.getPosZ(), false));
                    mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY() + 0.0336027996066, mc.player.getPosZ(), false));
                    mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY(), mc.player.getPosZ(), false));

                }
            }
        }
    });

    @Override
    public String getHudInfo() {
        return "\u00A77[\u00A7f" + this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode) + "\u00A77]";
    }
}
