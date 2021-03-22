package io.ace.nordclient.hacks.movement;

import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingMode;
import io.ace.nordclient.utilz.Setting;
import net.minecraft.network.play.client.CPlayerPacket;

import java.util.Arrays;
import java.util.List;

public class ReverseStep extends Hack {

    public static Setting speed;
    Setting fallMode;
    int delay = 0;
    private Double y;

    private static final List<SettingBase> settings = Arrays.asList(new SettingMode("FallMode : ", "Fast", "Medium", "Slow", "2b"));


    public ReverseStep() {
        super("ReverseStep", Category.MOVEMENT, 10820258, settings);
    }

    @Override
    public void onUpdate() {
        delay++;
        if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("fast")) y = -4D;
        if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("medium")) y = -2D;
        if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("slow")) y = -1D;

        if (mc.player.isOnGround() && !mc.player.isInWater() && !mc.player.isInLava() && !this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("2b")) {
            mc.player.setMotion(mc.player.getMotion().x, y, mc.player.getMotion().z);
        }

        if (mc.player.fallDistance > .1 && !mc.player.isInWater() && !mc.player.isInLava() && this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("2b2t")) {
            mc.player.connection.sendPacket(new CPlayerPacket(mc.player.isOnGround()));
            mc.player.setMotion(mc.player.getMotion().x, mc.player.getMotion().getY() * 1.25, mc.player.getMotion().z);
            mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY(), mc.player.getPosZ(), false));

        }

    }

}


