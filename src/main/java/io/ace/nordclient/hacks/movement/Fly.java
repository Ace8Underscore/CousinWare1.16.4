package io.ace.nordclient.hacks.movement;

import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingSlider;
import io.ace.nordclient.utilz.Setting;

import java.util.Arrays;
import java.util.List;

public class Fly extends Hack {

    Setting speed;

    private static final List<SettingBase> settings = Arrays.asList(new SettingSlider(0, 5, 1, 0, "FlySpeed"));


    public Fly() {
        super("Fly", Category.MOVEMENT, 11813813, settings);
        //CousinWare.INSTANCE.settingsManager.rSetting(speed = new Setting("Speed", this, 1, 0, 3, false, "FlySpeed"));
    }

    @Override
    public void onUpdate() {
        //mc.player..flySpeed = (float) speed.getValDouble();

    }

    @Override
    public void onEnable() {
       // mc.player.allowFlying = true;
        //mc.player.capabilities.isFlying = true;
    }

    @Override
    public void onDisable() {
       // mc.player.capabilities.allowFlying = false;
        //mc.player.capabilities.isFlying = false;
    }
}
