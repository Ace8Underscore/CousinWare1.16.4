package io.ace.nordclient.hacks.movement;

import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingMode;
import io.ace.nordclient.settings.SettingSlider;
import io.ace.nordclient.utilz.PlayerUtil;
import net.minecraft.network.play.client.CPlayerPacket;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
import java.util.List;

import static net.minecraft.block.Blocks.COBWEB;

/**
 * @author Ace________/Ace_#1233
 */

public class FastWeb extends Hack {

    private static final AxisAlignedBB webFloat = new AxisAlignedBB(0.D, 0.D, 0.D, 1.D, 0.999D, 1.D);

    boolean collided;
    int time = 0;
    int delay = 0;
    private static final List<SettingBase> settings = Arrays.asList(new SettingMode("DownMode: ", "2b", "Packet", "Timer", "Other", "Packet2"), new SettingSlider(1, 50, 10, 0, "Speed"));

    public FastWeb() {
        super("FastWeb", Category.MOVEMENT, 13032102, settings);

    }

    @Override
    public void onUpdate() {
        if (mc.world == null || mc.player == null)
            return;

        BlockPos currentPos = new BlockPos(mc.player.getPosX(), mc.player.getPosY(), mc.player.getPosZ());


        if (mc.world.getBlockState(PlayerUtil.getPlayerPos()).getBlock().equals(COBWEB)) {
            delay++;
            if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("2b")) {
                mc.player.setMotion(0, 1.1 / -5, 0);

            }
            if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("other")) {
                mc.player.setMotion(0, 20.7 / -5, 0);
            }
            if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("packet")) {
                if (!mc.player.collidedHorizontally && !mc.player.collidedVertically && !(time >= 9)) {
                    time++;
                    mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY(), mc.player.getPosZ() - .1, mc.player.isOnGround()));
                    mc.player.setPosition(mc.player.getPosX(), mc.player.getPosY() - .09, mc.player.getPosZ());
                }
            }
            if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("packet2")) {

                mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY(), mc.player.getPosZ() - .2, mc.player.isOnGround()));
                mc.player.setMotion(0, 5.5 / -5, 0);

            }
            if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("timer")) {
                if (!(mc.player.isOnGround())) {
                    if (delay > 5) {
                        //   ((ITimer)mc.timer).setTickLength(50f / (float) speed.getValDouble());
                        //.player.motionX *= .0001;
                       /// mc.player.motionZ *= .0001;

                    }
                } else {
                    //  ((ITimer)mc.timer).setTickLength(50f);
                    delay = 0;
                }


            }
        } else {
            // ((ITimer)mc.timer).setTickLength(50f);
            delay = 0;
        }
        if (!this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("timer")) {
            // if (!(((ITimer)mc.timer).getTickLength() == 50)) {
            // ((ITimer)mc.timer).setTickLength(50f);
            // }
        }

    }


    @Override
    public String getHudInfo() {
        return "\u00A77[\u00A7f" + this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode) + "\u00A77]";
    }

    @Override
    public void onEnable() {
        collided = false;
    }

    @Override
    public void onDisable() {
        ///  if (!(((ITimer)mc.timer).getTickLength() == 50)) {
        // ((ITimer)mc.timer).setTickLength(50f);
        //   }
    }

}
