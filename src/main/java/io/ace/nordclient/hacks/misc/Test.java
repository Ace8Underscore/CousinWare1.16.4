package io.ace.nordclient.hacks.misc;

import io.ace.nordclient.command.Command;
import io.ace.nordclient.event.PacketEvent;
import io.ace.nordclient.hacks.Hack;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CPlayerTryUseItemOnBlockPacket;
import net.minecraft.util.math.BlockRayTraceResult;

public class Test extends Hack {

    public Test() {
        super("Test", Category.MISC, -1, null);
    }

    @Override
    public void onUpdate() {
        /*BlockPos pos = new BlockPos(mc.player.getPosX() + 1, mc.player.getPosY(), mc.player.getPosZ());

        final Vector3d eyesPos = new Vector3d(mc.player.getPosX(), mc.player.getPosY() + mc.player.getEyeHeight(), mc.player.getPosZ());
        for (final Direction side : Direction.values()) {
            final BlockPos neighbor = pos;
            final Direction side2 = side.getOpposite();
            final Vector3d hitVec = new Vector3d(new Vector3f(neighbor.getX(), neighbor.getY(), neighbor.getZ())).add(.5, 0, .5).add(new Vector3d(side2.toVector3f()).scale(0.5));

            if (eyesPos.squareDistanceTo(hitVec) <= 18.0625) {
                BlockInteractionHelper.faceVectorPacketInstant(hitVec);
                BlockInteractionHelper.processRightClickBlock(neighbor, Direction.WEST, hitVec);
                if (Core.settings.get(0).toMode().getModeValue(Core.settings.get(0).toMode().mode).equalsIgnoreCase("ClientSide")) mc.player.swingArm(Hand.MAIN_HAND);
                else mc.player.connection.sendPacket(new CAnimateHandPacket(Hand.MAIN_HAND));
                return;

            }
        } */
    }

    @EventHandler
    private final Listener<PacketEvent.Send> listener = new Listener<>(event -> {
        if (event.getPacket() instanceof CPlayerTryUseItemOnBlockPacket) {
            BlockRayTraceResult result = ((CPlayerTryUseItemOnBlockPacket) event.getPacket()).func_218794_c();
            Command.sendClientSideMessage(" - HitVec : " + result.getHitVec() + " - Direction : " + result.getFace() + " - Neighbor/PlaceBlock? : " + result.getPos() + " - IsInside : " + result.isInside());
        }
    });
}
