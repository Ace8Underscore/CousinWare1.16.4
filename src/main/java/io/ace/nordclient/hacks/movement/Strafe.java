package io.ace.nordclient.hacks.movement;

import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingMode;
import io.ace.nordclient.settings.SettingSlider;
import io.ace.nordclient.settings.SettingToggle;
import io.ace.nordclient.utilz.MathUtil;
import io.ace.nordclient.utilz.MotionUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ace________/Ace_#1233
 */

public class Strafe extends Hack {

    int delay = 0;
    boolean lowHopped;

    private static final List<SettingBase> settings = Arrays.asList(new SettingMode("Mode ", "Launch", "Strafe", "StrafeAcel", "LowHop"), new SettingSlider(0, .6, .5, 0, "Speed"), new SettingToggle(true, "SmartFall"));

    public Strafe() {
        super("Speed", Category.MOVEMENT, 16617836, settings);
    }

    @Override
    public void onUpdate() {
        mc.player.setSprinting(true);
        if (settings.get(2).toToggle().state) {
            if (mc.player.getMotion().getY() < 0 && mc.player.getMotion().getY() > -.5) MotionUtil.setMotionY(mc.player.getMotion().getY() * 1.10);
            else MotionUtil.setMotionY(mc.player.getMotion().getY() * 1);
        }
        delay++;
        if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("launch")) doSpeedLaunch();
        if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("strafe")) doSpeedStrafe();
        if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("strafeacel")) doSpeedStafeAcel();
        if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("lowhop")) doSpeedLowHop();

//

    }

    public void doSpeedLowHop() {
        MotionUtil.setMotionY(mc.player.getMotion().getY() * .985);
        if (mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown()) {
            delay++;
            if (mc.player.isOnGround()) {
                mc.player.jump();
                lowHopped = false;
                final double[] dir3 = MathUtil.directionSpeed(settings.get(1).toSlider().value);
                mc.player.setMotion(dir3[0], mc.player.getMotion().getY(), dir3[1]);
            } else {
                final double[] dir = MathUtil.directionSpeed(.26);
                mc.player.setMotion(dir[0], mc.player.getMotion().getY(), dir[1]);
            }
            if (delay > 10 && !lowHopped) {
                final double[] dir2 = MathUtil.directionSpeed(.3);
                mc.player.setMotion(dir2[0], -.1, dir2[1]);
                delay = 0;
                lowHopped = true;
            }
        }
    }
    //

    public void doSpeedLaunch() {
        mc.player.setMotion(mc.player.getMotion().getX(), mc.player.getMotion().getY() * 1, mc.player.getMotion().getZ());
        if (mc.player.isOnGround()) {
            if (mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown()) {
                mc.player.jump();
                final double[] dir = MathUtil.directionSpeed(.43);
                mc.player.setMotion(dir[0], mc.player.getMotion().getY(), dir[1]);
            }
        }
    }

    public void doSpeedStrafe() {
        mc.player.setMotion(mc.player.getMotion().getX(), mc.player.getMotion().getY() * .985, mc.player.getMotion().getZ());
        if (mc.player.isOnGround()) {
            if (mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown()) {
                mc.player.jump();
                final double[] dir = MathUtil.directionSpeed(settings.get(1).toSlider().value);
                mc.player.setMotion(dir[0], mc.player.getMotion().getY(), dir[1]);
            }
        } else {
            final double[] dir = MathUtil.directionSpeed(.26);
            mc.player.setMotion(dir[0], mc.player.getMotion().getY(), dir[1]);
        }

    }

    public void doSpeedStafeAcel() {
        mc.player.setMotion(mc.player.getMotion().getX(), mc.player.getMotion().getY() * 1, mc.player.getMotion().getZ());
        if (mc.player.isOnGround()) {
            if (mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown()) {
                mc.player.jump();
                final double[] dir = MathUtil.directionSpeed(.26);
                mc.player.setMotion(dir[0], mc.player.getMotion().getY(), dir[1]);
            }
        } else {
            if (delay == 0 || delay == 1 || delay == 2 || delay == 3) {
                final double[] dir = MathUtil.directionSpeed(.26);
                mc.player.setMotion(dir[0], mc.player.getMotion().getY(), dir[1]);
            }
            if (delay >= 4) {
                final double[] dir = MathUtil.directionSpeed(settings.get(1).toSlider().value);
                mc.player.setMotion(dir[0], mc.player.getMotion().getY(), dir[1]);
                delay = 0;
            }
        }
    }

    public void onDisable() {
        delay = 0;
    }

    @Override
    public String getHudInfo() {
        return "\u00A77[\u00A7f" + this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode) + "\u00A77]";
    }

//
}
