package io.ace.nordclient.hacks.movement;

import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingMode;
import io.ace.nordclient.utilz.Setting;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ace________/Ace_#1233
 */

public class FastSwim extends Hack {

    Setting speed;
    int divider = 5;
    boolean only2b = false;
    //boolean sprint = true;
    boolean water = false;
    boolean lava = true;
    boolean up = true;
    boolean down = true;

    private static final List<SettingBase> settings = Arrays.asList(
            new SettingMode("SwimMode", "Spam", "NoSwim", "Swim"));


    public FastSwim() {
        super("FastSwim", Category.MOVEMENT, 4264952, settings);
       // CousinWare.INSTANCE.settingsManager.rSetting(speed = new Setting("Speed", this, .7, 0, 1, false, "FastSwimSpeed"));

    }
    //speed

    int delay = 0;

    @Override
    public void onUpdate() {
        if (mc.player.isInWater() && mc.player.isInLava()) {
            if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("NoSwim")) {
                mc.player.setSprinting(false);
            }
            if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("Spam")) {
               delay++;
                if (delay < 10) mc.player.setSprinting(true);
                if (delay > 10) {
                    mc.player.setMotion(0, 0, 0);
                    mc.player.setSprinting(false);
                    delay = 0;
                }

            }
        }
        //mc.player.connection.sendPacket(new CPacket(mc.player, , 1));
        if (only2b) {
            if (!mc.isSingleplayer()) {
                if (mc.getCurrentServerData().serverIP.equalsIgnoreCase("2b2t.org")) {
                    if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("Swim")) {
                        if (mc.player.isInLava() || mc.player.isInWater()) {
                            mc.player.setSprinting(true);
                        }
                    }

                    if (mc.player.isInWater() || mc.player.isInLava()) {
                        if (mc.gameSettings.keyBindJump.isKeyDown() && up) {
                            //mc.player.motionY = .725 / divider;
                            mc.player.setMotion(0, .725 / divider ,0);
                        }
                    }
                    if (mc.player.isInWater() && water && !mc.player.isOnGround()) {
                        if (mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown()) {
                            final float yaw = GetRotationYawForCalc();
                            //mc.player.motionX -= MathHelper.sin(yaw) * .25 / 10;
                            //mc.player.motionZ += MathHelper.cos(yaw) * .25 / 10;
                        }
                    }

                    if (mc.player.isInLava() && lava && !mc.player.isOnGround()) {
                        if (mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown()) {
                            final float yaw = GetRotationYawForCalc();
                            mc.player.setMotion(MathHelper.sin(yaw) * .7 / 10, 0, MathHelper.cos(yaw) * speed.getValDouble() / 10); //-= MathHelper.sin(yaw) * speed.getValDouble() / 10;
                            //mc.player.motionZ += MathHelper.cos(yaw) * speed.getValDouble() / 10;
                        }
                    }


                    if (mc.player.isInWater() && down) {
                        if (mc.gameSettings.keyBindSneak.isKeyDown()) {
                            int divider2 = divider * -1;
                            //mc.player.motionY = 2.2 / divider2;
                            mc.player.setMotion(0, 2.2 / divider2, 0);
                        }
                    }
                    if (mc.player.isInLava() && down) {
                        if (mc.gameSettings.keyBindSneak.isKeyDown()) {
                            int divider2 = divider * -1;
                            //mc.player.motionY = .91 / divider2;
                            mc.player.setMotion(0, .91 / divider2, 0);
                        }
                        //
                    }
                    if (!mc.player.isInWater() && !mc.player.isInLava()) {
                        //mc.player.motionX -= 0;
                        //mc.player.motionZ += 0;
                    }
                }
            }
        }
        ///
        if (!only2b) {
            if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("swim")) {
                if (mc.player.isInLava() || mc.player.isInWater()) {
                    mc.player.setSprinting(true);
                }
            }

            if (mc.player.isInWater() || mc.player.isInLava()) {
                if (mc.gameSettings.keyBindJump.isKeyDown() && up) {
                    //mc.player.motionY = .725 / divider;
                    mc.player.setMotion(0, .725 / divider, 0);
                }
            }
            if (mc.player.isInWater() && water) {
                    //if (mc.player.isVisuallySwimming()) {
                        if (mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown()) {
                            final float yaw = GetRotationYawForCalc();
                            mc.player.setMotion(MathHelper.sin(yaw) * 10 / 10, 0, MathHelper.cos(yaw) * 10 / 10);

                       // }
                    }
            }

            if (mc.player.isInLava() && lava && !mc.player.isOnGround()) {
                if (mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown()) {
                    final float yaw = GetRotationYawForCalc();
                    //mc.player.motionX -= MathHelper.sin(yaw) * .7 / 10;
                    //mc.player.motionZ += MathHelper.cos(yaw) * .7 / 10;

                    mc.player.setMotion(MathHelper.sin(yaw) * .7 / 10, 0, MathHelper.cos(yaw) * .7 / 10);
                }
            }

            if (mc.player.isInWater() && down && !mc.player.isOnGround()) {
                if (mc.gameSettings.keyBindSneak.isKeyDown()) {
                    int divider2 = divider * -1;
                    //mc.player.motionY = 2.2 / divider2;
                    mc.player.setMotion(0, 2.2 / divider2, 0);
                }
            }
            if (mc.player.isInLava() && down) {
                if (mc.gameSettings.keyBindSneak.isKeyDown()) {
                    int divider2 = divider * -1;
                    //mc.player.motionY = .91 / divider2;
                    mc.player.setMotion(0, .91 / divider2, 0);
                }
            }
            if (!mc.player.isInWater() && !mc.player.isInLava()) {
                //mc.player.motionX -= 0;
                // mc.player.motionZ += 0;
            }
        }
    }

    /**
     * @author ionar
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
}


