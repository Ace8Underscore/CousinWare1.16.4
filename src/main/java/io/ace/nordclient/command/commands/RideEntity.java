package io.ace.nordclient.command.commands;

import io.ace.nordclient.command.Command;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.horse.DonkeyEntity;
import net.minecraft.network.play.client.CEntityActionPacket;

public class RideEntity extends Command {
    @Override
    public String[] getClientAlias() {
        return new String[]{"ride"};
    }

    @Override
    public String getClientSyntax() {
        return "Ride";
    }

    @Override
    public void onClientCommand(String command, String[] args) throws Exception {
        for (Entity entity : mc.world.getAllEntities())
            if (entity instanceof DonkeyEntity) {
                //mc.getConnection().sendPacket(new CPacketUseEntity(CPacketUseEntity.Action.INTERACT, entity, EnumHand.MAIN_HAND, entity.getPositionVector()));
                mc.getConnection().sendPacket(new CEntityActionPacket(entity, CEntityActionPacket.Action.START_RIDING_JUMP, entity.getEntityId()));
                Command.sendClientSideMessage(String.valueOf(entity.getEntityId()));

            }
    }
}

