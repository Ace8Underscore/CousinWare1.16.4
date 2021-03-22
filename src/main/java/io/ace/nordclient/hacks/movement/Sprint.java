package io.ace.nordclient.hacks.movement;

import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingToggle;
import io.ace.nordclient.utilz.MotionUtil;

import java.util.Arrays;
import java.util.List;

public class Sprint extends Hack {

    String returnMessage = "";

    private static final List<SettingBase> settings = Arrays.asList(new SettingToggle(true, "Strict"));


    public Sprint() {
        super("Sprint", Category.MOVEMENT, 11592432, settings);
    }

    @Override
    public void onUpdate() {
        if (settings.get(0).toToggle().state) {
            returnMessage = "Strict";
            if (mc.player.moveForward > 0 && !mc.player.collidedHorizontally) {
                mc.player.setSprinting(true);
            }
        } else {
            returnMessage = "Rage";
            if (MotionUtil.isMoving()) {
                mc.player.setSprinting(true);

            }
        }

    }

    @Override
    public String getHudInfo() {
        return "\u00A77[\u00A7f" + returnMessage + "\u00A77]";
    }
}
