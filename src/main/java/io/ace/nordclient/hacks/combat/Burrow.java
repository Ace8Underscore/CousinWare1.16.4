package io.ace.nordclient.hacks.combat;

import io.ace.nordclient.command.Command;
import io.ace.nordclient.event.PacketEvent;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingMode;
import io.ace.nordclient.settings.SettingSlider;
import io.ace.nordclient.settings.SettingToggle;
import io.ace.nordclient.utilz.BlockInteractionHelper;
import io.ace.nordclient.utilz.InventoryUtil;
import io.ace.nordclient.utilz.MotionUtil;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.block.Blocks;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.client.CConfirmTeleportPacket;
import net.minecraft.network.play.client.CPlayerPacket;
import net.minecraft.network.play.client.CPlayerTryUseItemOnBlockPacket;
import net.minecraft.network.play.server.SPlayerPositionLookPacket;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ace________/Ace_#1233
 */

public class Burrow extends Hack {


    int delayPlace;
    boolean blockPlaced;
    boolean noForce;
    int startingHand;
    double startingY;
    int useItem;

    private static final List<SettingBase> settings = Arrays.asList(
            new SettingSlider(0, 8, 0, 0, "Delay"),
            new SettingSlider(.5, 10, 1, 0, "LagBackPower"),
            new SettingToggle(true, "NoForceRotate"),
            new SettingMode("BlockMode ", "Obi", "Echest"),
            new SettingMode("LagMode", "Packet", "Fly"),
            new SettingToggle(true, "NoJump"));


    public Burrow() {
        super("Burrow", Category.COMBAT, 5254300, settings);
    }

    @Override
    public void onUpdate() {
        if (mc.player == null || mc.world == null) this.disable();
        delayPlace++;
        if (delayPlace >= settings.get(0).toSlider().getValue() && !noForce) {
            BlockPos pos = new BlockPos(mc.player.getPosX(), mc.player.getPosY() - 1, mc.player.getPosZ());
            if (settings.get(5).toToggle().state) {
                mc.player.inventory.currentItem = useItem;
                mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY() + 0.41999998688698D, mc.player.getPosZ(), true));
                mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY() + 0.7531999805211997D, mc.player.getPosZ(), true));
                mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY() + 1.00133597911214D, mc.player.getPosZ(), true));
                mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY() + 1.16610926093821D, mc.player.getPosZ(), true));
                BlockInteractionHelper.placeBlockScaffold(pos.up());
                mc.player.connection.sendPacket(new CPlayerTryUseItemOnBlockPacket(Hand.MAIN_HAND, new BlockRayTraceResult(new Vector3d(0, 0, 0), Direction.UP, pos, false)));
                //mc.player.inventory.currentItem = startingHand;
            } else {
                mc.player.inventory.currentItem = useItem;
                BlockInteractionHelper.placeBlockScaffold(pos);
                mc.player.inventory.currentItem = startingHand;
            }
            if (getSettings().get(4).toMode().getModeValue(getSettings().get(4).toMode().mode).equalsIgnoreCase("fly")) {
                MotionUtil.setMotionY(settings.get(1).toSlider().value);
            } else if (getSettings().get(4).toMode().getModeValue(getSettings().get(4).toMode().mode).equalsIgnoreCase("packet")) {
                mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY() + settings.get(1).toSlider().value, mc.player.getPosZ(), false));
           // } else if (lagMode.getValString().equalsIgnoreCase("Auto")) {
               // mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY() + (127 - mc.player.getPosY()) + 1, mc.player.getPosZ(), false));

            }
            if (settings.get(2).toToggle().state) this.disable();


        }
    }

    public void onEnable() {
        startingHand = mc.player.inventory.currentItem;
        if (this.getSettings().get(3).toMode().getModeValue(getSettings().get(3).toMode().mode).equalsIgnoreCase("obi")) {
            if (InventoryUtil.findBlockInHotbar(Blocks.OBSIDIAN) == -1) {
                Command.sendClientSideMessage("No Obsidian Found");
                this.disable();
            } else {
                useItem = InventoryUtil.findBlockInHotbar(Blocks.OBSIDIAN);
                blockPlaced = false;
                noForce = false;
                if (!settings.get(5).toToggle().state) mc.player.jump();
                delayPlace = 0;
            }
            mc.player.inventory.currentItem = useItem;
        }

        if (this.getSettings().get(3).toMode().getModeValue(getSettings().get(3).toMode().mode).equalsIgnoreCase("echest")) {
            if (InventoryUtil.findBlockInHotbar(Blocks.ENDER_CHEST) == -1) {
                Command.sendClientSideMessage("No EChest Found");
                this.disable();
            } else {
                useItem = InventoryUtil.findBlockInHotbar(Blocks.ENDER_CHEST);
                blockPlaced = false;
                noForce = false;
                if (!settings.get(5).toToggle().state) mc.player.jump();
                delayPlace = 0;
            }
        }

    }

    @EventHandler
    private final Listener<PacketEvent.Receive> listener = new Listener<>(event -> {
        if (mc.world == null || mc.player == null)
            this.disable();
        if (settings.get(2).toToggle().state) {
            IPacket packet = event.getPacket();

            if (event.getPacket() instanceof SPlayerPositionLookPacket) {
                event.setCanceled(true);
                //this.toggle();
                double d0 = ((SPlayerPositionLookPacket) event.getPacket()).getX();
                double d1 = ((SPlayerPositionLookPacket) event.getPacket()).getY();
                double d2 = ((SPlayerPositionLookPacket) event.getPacket()).getZ();


                if (((SPlayerPositionLookPacket) event.getPacket()).getFlags().contains(SPlayerPositionLookPacket.Flags.X)) {
                    d0 += mc.player.getPosX();
                } else {
                    MotionUtil.setMotionX(0);
                }

                if (((SPlayerPositionLookPacket) event.getPacket()).getFlags().contains(SPlayerPositionLookPacket.Flags.Y)) {
                    d1 += mc.player.getPosY();
                } else {
                    MotionUtil.setMotionY(0);
                }

                if (((SPlayerPositionLookPacket) event.getPacket()).getFlags().contains(SPlayerPositionLookPacket.Flags.Z)) {
                    d2 += mc.player.getPosZ();
                } else {
                    MotionUtil.setMotionZ(0);
                }

                mc.player.setPosition(d0, d1, d2);
                mc.getConnection().sendPacket(new CConfirmTeleportPacket(((SPlayerPositionLookPacket) event.getPacket()).getTeleportId()));
                mc.getConnection().sendPacket(new CPlayerPacket.PositionRotationPacket(mc.player.getPosX(), mc.player.getBoundingBox().minY, ((SPlayerPositionLookPacket) event.getPacket()).getZ(), ((SPlayerPositionLookPacket) event.getPacket()).getYaw(), ((SPlayerPositionLookPacket) event.getPacket()).getPitch(), false));
                this.disable();
            }
        }
    });
    //

}