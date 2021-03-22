package io.ace.nordclient.hacks.movement;

import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingMode;
import io.ace.nordclient.settings.SettingToggle;
import io.ace.nordclient.utilz.Setting;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ace________/Ace_#1233
 */

public class Step extends Hack {

    Setting speed;

    private static final List<SettingBase> settings = Arrays.asList(new SettingMode("Height ", "2", "3", "4"), new SettingMode("StepOn", "Collide", "Jump", "Vanilla"), new SettingToggle(true, "ToggleOnStep"));

    public Step() {
        super("Step", Category.MOVEMENT, 11731844, settings);
    }


    @Override
    public void onUpdate() {

        if (this.getSettings().get(1).toMode().getModeValue(getSettings().get(1).toMode().mode).equalsIgnoreCase("collide") && mc.player.collidedVertically && mc.player.collidedHorizontally) {
            doVelocity();
        }
        if (this.getSettings().get(1).toMode().getModeValue(getSettings().get(1).toMode().mode).equalsIgnoreCase("jump") && mc.gameSettings.keyBindJump.isPressed()) {
            doVelocity();
        }
        if (this.getSettings().get(1).toMode().getModeValue(getSettings().get(1).toMode().mode).equalsIgnoreCase("vanilla")) {
            mc.player.stepHeight = mc.player.isOnGround() ? 2.0f : 0.6f;
        }
    }

    @Override
    public void onDisable() {
        mc.player.stepHeight = 0.6f;
    }

    public void doVelocity() {
        if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("2")) {
            mc.player.setVelocity(.0, .56, .0);

            if (settings.get(2).toToggle().state) this.toggle();
        }
        if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("3")) {
            mc.player.setVelocity(.01, .7, .01);
            if (settings.get(2).toToggle().state) this.toggle();
        }
        if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("4")) {
            mc.player.setVelocity(.01, .84, .01);
            if (settings.get(2).toToggle().state) this.toggle();
        }

    }
}