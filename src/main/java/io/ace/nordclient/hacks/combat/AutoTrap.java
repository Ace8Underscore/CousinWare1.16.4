package io.ace.nordclient.hacks.combat;

import io.ace.nordclient.command.Command;
import io.ace.nordclient.event.PacketEvent;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.managers.FriendManager;
import io.ace.nordclient.mixin.accessor.ICPacketPlayer;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingMode;
import io.ace.nordclient.settings.SettingSlider;
import io.ace.nordclient.settings.SettingToggle;
import io.ace.nordclient.utilz.BlockInteractionHelper;
import io.ace.nordclient.utilz.InventoryUtil;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.client.CPlayerDiggingPacket;
import net.minecraft.network.play.client.CPlayerPacket;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

import java.util.Arrays;
import java.util.List;

public class AutoTrap extends Hack {

    private static boolean isSpoofingAngles;
    private static double yaw;
    private static double pitch;
    boolean north, south, east, west;
    int delay = 0;
    int delayToggle = 0;
    int startingHand;
    int obsidianSlot = 0;

    boolean packetsBeingSent;
    private PlayerEntity closestTarget;

    private static final List<SettingBase> settings = Arrays.asList(
            new SettingSlider(0, 8, 5.5, 0, "PlaceRange"),
            new SettingSlider(0, 20, 2, 1, "PlaceDelay"),
            new SettingSlider(0, 30, 8, 2, "ToggleTicks"),
            new SettingToggle(true, "NoGhostBlocks"),
            new SettingToggle(true, "DirectionTrap"),
            new SettingMode("HeadFix", "Auto", "On", "Off"));


    public AutoTrap() {
        super("AutoTrap", Category.COMBAT, 13648212, settings);
    }

    //this modifies packets being sent so no extra ones are made. NCP used to flag with "too many packets"
    private static void setYawAndPitch(float yaw1, float pitch1) {
        yaw = yaw1;
        pitch = pitch1;
        isSpoofingAngles = true;
    }

    private static void resetRotation() {
        if (isSpoofingAngles) {
            yaw = mc.player.rotationYaw;
            pitch = mc.player.rotationPitch;
            isSpoofingAngles = false;
        }
    }

    public static double[] calculateLookAt(double px, double py, double pz, PlayerEntity me) {
        double dirx = me.getPosX() - px;
        double diry = me.getPosY() - py;
        double dirz = me.getPosZ() - pz;

        double len = Math.sqrt(dirx * dirx + diry * diry + dirz * dirz);

        dirx /= len;
        diry /= len;
        dirz /= len;

        double pitch = Math.asin(diry);
        double yaw = Math.atan2(dirz, dirx);

        //to degree
        pitch = pitch * 180.0d / Math.PI;
        yaw = yaw * 180.0d / Math.PI;

        yaw += 90f;

        return new double[]{yaw, pitch};
    }

    @Override
    public void onUpdate() {
        if (closestTarget == null) return;
        BlockPos posc = new BlockPos(closestTarget.getPositionVec());
        delay++;
        delayToggle++;
        Vector3d[] placeDirection = null;
        if (settings.get(4).toToggle().state) {
            if (BlockInteractionHelper.findBlockFacingLocationPlayer(posc) == 1)
                placeDirection = new Vector3d[]{new Vector3d(-1, 0, 0), new Vector3d(0, 0, 1), new Vector3d(0, 0, -1), new Vector3d(1, 0, 0), new Vector3d(-1, 1, 0), new Vector3d(0, 1, 1), new Vector3d(0, 1, -1), new Vector3d(0, 2, 0), new Vector3d(1, 1, 0)};
            if (BlockInteractionHelper.findBlockFacingLocationPlayer(posc) == 2)
                placeDirection = new Vector3d[]{new Vector3d(1, 0, 0), new Vector3d(0, 0, 1), new Vector3d(0, 0, -1), new Vector3d(-1, 0, 0), new Vector3d(1, 1, 0), new Vector3d(0, 1, 1), new Vector3d(0, 1, -1), new Vector3d(0, 2, 0), new Vector3d(-1, 1, 0),};
            if (BlockInteractionHelper.findBlockFacingLocationPlayer(posc) == 3)
                placeDirection = new Vector3d[]{new Vector3d(0, 0, -1), new Vector3d(-1, 0, 0), new Vector3d(1, 0, 0), new Vector3d(0, 0, 1), new Vector3d(0, 1, -1), new Vector3d(-1, 1, 0), new Vector3d(1, 1, 0), new Vector3d(0, 2, 0), new Vector3d(0, 1, 1),};

            if (BlockInteractionHelper.findBlockFacingLocationPlayer(posc) == 4)
                placeDirection = new Vector3d[]{new Vector3d(0, 0, 1), new Vector3d(-1, 0, 0), new Vector3d(1, 0, 0), new Vector3d(0, 0, -1), new Vector3d(0, 1, 1), new Vector3d(-1, 1, 0), new Vector3d(1, 1, 0), new Vector3d(0, 2, 0), new Vector3d(0, 1, -1)};
        } else {
            placeDirection = new Vector3d[]{new Vector3d(1, -1, 0), new Vector3d(1, 0, 0), new Vector3d(-1, -1, 0), new Vector3d(-1, 0, 0), new Vector3d(0, -1, 1), new Vector3d(0, 0, 1), new Vector3d(0, -1, -1), new Vector3d(0, 0, -1), new Vector3d(1, 1, 0), new Vector3d(-1, 1, 0), new Vector3d(0, 1, 1), new Vector3d(0, 1, -1), new Vector3d(1, 2, 0), new Vector3d(0, 2, 0)};
        }
        // Vector3d[] placeSouth = new Vector3d[]{ new Vector3d(1, -1, 0), new Vector3d(1, 0, 0), new Vector3d(-1, -1, 0), new Vector3d(-1, 0, 0), new Vector3d(0, -1, 1), new Vector3d(0, 0, 1), new Vector3d(0, -1, -1), new Vector3d(0, 0, -1), new Vector3d(1, 1, 0), new Vector3d(-1, 1, 0), new Vector3d(0, 1, 1), new Vector3d(0, 1, -1), new Vector3d(0, 2, 1), new Vector3d(0, 2, 0)};
        //Vector3d[] placeNorth = new Vector3d[]{ new Vector3d(1, -1, 0), new Vector3d(1, 0, 0), new Vector3d(-1, -1, 0), new Vector3d(-1, 0, 0), new Vector3d(0, -1, 1), new Vector3d(0, 0, 1), new Vector3d(0, -1, -1), new Vector3d(0, 0, -1), new Vector3d(1, 1, 0), new Vector3d(-1, 1, 0), new Vector3d(0, 1, 1), new Vector3d(0, 1, -1), new Vector3d(0, 2, -1), new Vector3d(0, 2, 0)};
        if (placeDirection == null) return;
        for (Vector3d vec3d : placeDirection) {
            if (delay >= settings.get(1).toSlider().getValue()) {
                BlockPos pos = new BlockPos(closestTarget.getPositionVec().add(vec3d));
                if (mc.world.getBlockState(pos).getMaterial().isReplaceable()) {
                    mc.player.inventory.currentItem = obsidianSlot;
                    //mc.player.connection.sendPacket(new CEntityActionPacket(mc.player, CEntityActionPacket.Action.PRESS_SHIFT_KEY));
                    if (this.getSettings().get(5).toMode().getModeValue(getSettings().get(5).toMode().mode).equalsIgnoreCase("on")) {
                        if (vec3d.equals(new Vector3d(-1, 2, 0)) || vec3d.equals(new Vector3d(1, 2, 0)) || vec3d.equals(new Vector3d(0, 2, 1)) || vec3d.equals(new Vector3d(0, 2, -1))) {
                            mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY() + 0.41999998688698D, mc.player.getPosZ(), true));
                            mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY() + 0.7531999805211997D, mc.player.getPosZ(), true));

                        }
                    }
                    if (this.getSettings().get(5).toMode().getModeValue(getSettings().get(5).toMode().mode).equalsIgnoreCase("auto")) {
                        if (mc.player.getPosY() <= (closestTarget.getPosY() - .6)) {
                            if (vec3d.equals(new Vector3d(-1, 2, 0)) || vec3d.equals(new Vector3d(1, 2, 0)) || vec3d.equals(new Vector3d(0, 2, 1)) || vec3d.equals(new Vector3d(0, 2, -1))) {
                                mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY() + 0.41999998688698D, mc.player.getPosZ(), true));
                                mc.player.connection.sendPacket(new CPlayerPacket.PositionPacket(mc.player.getPosX(), mc.player.getPosY() + 0.7531999805211997D, mc.player.getPosZ(), true));
                            }
                        }
                    }
                    if (vec3d.equals(new Vector3d(0, 2, 0)) || vec3d.equals(new Vector3d(-1, 2, 0)) || vec3d.equals(new Vector3d(1, 2, 0)) || vec3d.equals(new Vector3d(0, 2, 1)) || vec3d.equals(new Vector3d(0, 2, -1))) BlockInteractionHelper.placeBlockScaffoldStrict(pos);
                    else BlockInteractionHelper.placeBlockScaffold(pos);
                    //mc.player.connection.sendPacket(new CEntityActionPacket(mc.player, CEntityActionPacket.Action.RELEASE_SHIFT_KEY));
                    if (settings.get(3).toToggle().state) {
                        mc.player.connection.sendPacket(new CPlayerDiggingPacket(CPlayerDiggingPacket.Action.START_DESTROY_BLOCK, pos, Direction.SOUTH));
                        mc.player.connection.sendPacket(new CPlayerDiggingPacket(CPlayerDiggingPacket.Action.ABORT_DESTROY_BLOCK, pos, Direction.SOUTH));
                    }
                    delay = 0;

                }
            }
        }
        if (delayToggle >= settings.get(2).toSlider().getValue()) {
            this.toggle();
        }
        if (!packetsBeingSent) {
            Command.sendClientSideMessage("Sending Packet");
            mc.player.connection.sendPacket(new CPlayerPacket(mc.player.isOnGround()));
            packetsBeingSent = true;
        }
    }

    @EventHandler
    private final Listener<PacketEvent.Send> listener = new Listener<>(event -> {
        if (event.getPacket() instanceof CPlayerPacket) {
            if (isSpoofingAngles) {
                ((ICPacketPlayer) event.getPacket()).setYaw((float) yaw);
                ((ICPacketPlayer) event.getPacket()).setPitch((float) pitch);
            }
        } else if (isSpoofingAngles) {
            packetsBeingSent = false;

        }
    });

    @Override
    public void onEnable() {
        findClosestTarget();
        startingHand = mc.player.inventory.currentItem;
        delayToggle = 0;
        delay = 0;
        packetsBeingSent = true;
        int obsidianHand = InventoryUtil.findBlockInHotbar(Blocks.OBSIDIAN);
        if (obsidianHand == -1) {
            Command.sendClientSideMessage("No Obsidian Toggling!");
        } else {
            obsidianSlot = obsidianHand;
        }
        int var24 = MathHelper.floor((double) (Minecraft.getInstance().player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;


        float yaw = Minecraft.getInstance().player.rotationYaw;


        if (var24 == 2) {

            //North
        }

        if (var24 == 1) {

            //West
        }
        if (var24 == 3) {

            //East
        }
        if (var24 == 0) {

            //South
        }
    }

    @Override
    public void onDisable() {
        mc.player.inventory.currentItem = startingHand;

    }

    private void lookAtPacket(double px, double py, double pz, PlayerEntity me) {
        double[] v = calculateLookAt(px, py, pz, me);
        setYawAndPitch((float) v[0], (float) v[1]);
    }

    private void findClosestTarget() {
        this.closestTarget = null;
        for (final Entity target : mc.world.getAllEntities()) {
            if (target instanceof PlayerEntity) {
                if (target == mc.player) {
                    continue;
                }
                if (FriendManager.isFriend(target.getScoreboardName())) {
                    continue;
                }
                if (((PlayerEntity) target).getHealth() <= 0.0f) {
                    continue;
                }
                if (this.closestTarget == null) {
                    this.closestTarget = (PlayerEntity) target;
                } else {
                    if (mc.player.getDistance((Entity) target) >= mc.player.getDistance((Entity) this.closestTarget)) {
                        continue;
                    }
                    this.closestTarget = (PlayerEntity) target;
                }
            }
        }
    }


}
