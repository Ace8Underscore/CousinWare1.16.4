package io.ace.nordclient.hacks.movement;

import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingMode;
import io.ace.nordclient.settings.SettingSlider;
import io.ace.nordclient.settings.SettingToggle;
import io.ace.nordclient.utilz.MathUtil;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.CEntityActionPacket;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ace________/Ace_#1233
 */

public class ElytraFly extends Hack {

    private int sendPacketDelay = 0;

    private static final List<SettingBase> settings = Arrays.asList(new SettingMode("FlyMode: ", "2b", "Creative", "Plane"), new SettingSlider(0, 10, 2, 0, "Speed"), new SettingToggle(true, "Boost"), new SettingSlider(0, 1, 2.4, 0, "GlideSpeed"), new SettingToggle(true, "Glide"), new SettingToggle(true, "NoGlideAFK"), new SettingToggle(true, "AutoTakeOff"));

    public ElytraFly() {
        super("ElytraFly", Category.MOVEMENT, 16111998, settings);
    }

    @Override
    public void onUpdate() {
        if (mc.player.isElytraFlying() && !mc.gameSettings.keyBindSneak.isKeyDown()) {
            final float yaw = GetRotationYawForCalc();
            if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("2b")) {
                mc.player.connection.sendPacket(new CEntityActionPacket(mc.player, CEntityActionPacket.Action.START_FALL_FLYING));

            }

            if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("plane")) {
                final double[] dir = MathUtil.directionSpeed(settings.get(1).toSlider().value);
                mc.player.setMotion(dir[0], 0, dir[1]);
            }
            if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("creative")) {
                final double[] dir = MathUtil.directionSpeed(settings.get(1).toSlider().value);
                if (!settings.get(2).toToggle().state) {
                    mc.player.setMotion(dir[0], 0, dir[1]);
                } else {
                    if (mc.player.rotationPitch > 0) {
                        mc.player.setMotion(dir[0], 0, dir[1]);

                    }
                    if (mc.player.rotationPitch < 0) {
                        mc.player.setMotion(MathHelper.sin(yaw) * .08 / 10,  0,MathHelper.cos(yaw) * .08 / 10);
                    }

                }

            }

            if (settings.get(2).toToggle().state) {
                if (mc.player.rotationPitch < 0) {
                    mc.player.setMotion(MathHelper.sin(yaw) * .08 / 10,  0,MathHelper.cos(yaw) * .08 / 10);
                }
            }


            if (settings.get(4).toToggle().state) {
                mc.player.setMotion(0, -(settings.get(3).toSlider().value / 10000), 0);
            }

        }
        if (settings.get(6).toToggle().state) {
            if (mc.player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == Items.ELYTRA && mc.gameSettings.keyBindJump.isKeyDown() && !mc.player.isElytraFlying()) {
                sendPacketDelay++;
                if (sendPacketDelay > 5) {
                    mc.player.connection.sendPacket(new CEntityActionPacket(mc.player, CEntityActionPacket.Action.START_FALL_FLYING));
                    sendPacketDelay = 0;
                    //
                }
            }
        }

        if (mc.player.isElytraFlying() && mc.gameSettings.keyBindSneak.isKeyDown()) {
            mc.player.setMotion(mc.player.getMotion().x, -.51, mc.player.getMotion().z);
        }


        if (!this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("2b")) {
            if (mc.player.isElytraFlying() && !mc.gameSettings.keyBindForward.isKeyDown() && !mc.gameSettings.keyBindRight.isKeyDown() && !mc.gameSettings.keyBindLeft.isKeyDown() && !mc.gameSettings.keyBindBack.isKeyDown() && !mc.gameSettings.keyBindSneak.isKeyDown() && !mc.gameSettings.keyBindJump.isKeyDown()) {
                mc.player.setMotion(0, 0, 0);
                if (settings.get(5).toToggle().state) {
                    mc.player.setMotion(mc.player.getMotion().x, 0, mc.player.getMotion().z);
                }
            }


            //
            //
        }

    }

    /*
        thx ionar
    */
    private float GetRotationYawForCalc() {
        float rotationYaw = mc.player.rotationYaw;
        if (mc.player.moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        float n = 1.0f;
        if (mc.player.moveForward < 0.0f) {
            n = -0.5f;
        } else if (mc.player.moveForward > 0.0f) {
            n = 0.5f;
        }
        if (mc.player.moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * n;
        }
        if (mc.player.moveStrafing < 0.0f) {
            rotationYaw += 90.0f * n;
        }
        return rotationYaw * 0.017453292f;
    }


    @Override
    public String getHudInfo() {
        return "\u00A77[\u00A7f" + this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode) + "\u00A77]";
    }
    //
}
