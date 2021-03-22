package io.ace.nordclient.hacks.misc;

import io.ace.nordclient.event.PacketEvent;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.managers.HackManager;
import io.ace.nordclient.managers.RotationManager;
import io.ace.nordclient.mixin.accessor.ICPacketPlayer;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingSlider;
import io.ace.nordclient.settings.SettingToggle;
import io.ace.nordclient.utilz.InventoryUtil;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.client.CPlayerDiggingPacket;
import net.minecraft.network.play.client.CPlayerPacket;
import net.minecraft.tileentity.ShulkerBoxTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;

import java.util.Arrays;
import java.util.List;

public class AntiRegear extends Hack {

    float yaw;
    float pitch;
    boolean isSpoofing;

    private static final List<SettingBase> settings = Arrays.asList(
            new SettingToggle(true, "AutoSwitch"),
            new SettingToggle(false, "Rotate"),
            new SettingSlider(0, 8, 5.5, 0, "Range"));


    public AntiRegear() {
        super("AntiRegear", Category.MISC, 9681786, settings);
    }

    @Override
    public void onUpdate() {
        if (getTargetBlock() != null) {
            if (settings.get(1).toToggle().state && InventoryUtil.findItemInHotbar(Items.DIAMOND_PICKAXE) != -1) {
                int pickSlot = InventoryUtil.findItemInHotbar(Items.DIAMOND_PICKAXE);
                mc.player.inventory.currentItem = pickSlot;
            }
            if (settings.get(2).toToggle().state) {
                isSpoofing = true;
                lookAtPacket(getTargetBlock().getPos().getX() + .5, getTargetBlock().getPos().getY() - 1, getTargetBlock().getPos().getZ() + .5, mc.player);
            }
            mc.player.swingArm(HackManager.getHackByName("Swing").isEnabled() ? Hand.OFF_HAND : Hand.MAIN_HAND);
            mc.player.connection.sendPacket(new CPlayerDiggingPacket(CPlayerDiggingPacket.Action.START_DESTROY_BLOCK, getTargetBlock().getPos(), Direction.SOUTH));
            mc.player.connection.sendPacket(new CPlayerDiggingPacket(CPlayerDiggingPacket.Action.STOP_DESTROY_BLOCK, getTargetBlock().getPos(), Direction.SOUTH));

        }
        if (settings.get(2).toToggle().state) {
            if (getTargetBlock() == null) {
                resetRotations();
            }
        }

    }

    public TileEntity getTargetBlock() {
        TileEntity target = null;
        for (TileEntity shulker : mc.world.loadedTileEntityList) {
            if (shulker instanceof ShulkerBoxTileEntity) {
                if (mc.player.getDistanceSq(shulker.getPos().getX(), shulker.getPos().getY(), shulker.getPos().getZ()) <= (settings.get(0).toSlider().getValue() * settings.get(0).toSlider().getValue())) {
                    target = shulker;
                }

            }


        }


        return target;
    }

    public void resetRotations() {
        yaw = mc.player.rotationYaw;
        pitch = mc.player.rotationPitch;
    }

    private void lookAtPacket(double px, double py, double pz, ClientPlayerEntity me) {
        double[] v = RotationManager.calculateLookAt(px, py, pz, me);
        setYawAndPitch((float) v[0], (float) v[1]);
    }

    private void setYawAndPitch(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    @EventHandler
    private final Listener<PacketEvent.Send> listener = new Listener<>(event -> {
        if (settings.get(2).toToggle().state) {
            IPacket packet = event.getPacket();
            if (packet instanceof CPlayerPacket) {
                ((ICPacketPlayer) packet).setYaw(yaw);
                ((ICPacketPlayer) packet).setPitch(pitch);
            }
        }
    });

    @Override
    public void onDisable() {
        if (settings.get(2).toToggle().state) {
            resetRotations();
        }
    }


}
