package io.ace.nordclient.command.commands;

import io.ace.nordclient.command.Command;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EnderCrystalEntity;

public class Pyro extends Command {


    /**
     * @author Ace________/Ace_#1233
     */

    @Override
    public String[] getClientAlias() {
        return new String[]{"Pyro"};
    }

    @Override
    public String getClientSyntax() {
        return "Pyro";
    }

    @Override
    public void onClientCommand(String command, String[] args) throws Exception {
        mc.world.removeAllEntities();
        mc.world.removeEntityFromWorld(200);
        for (Entity e : mc.world.getAllEntities()) {
            if (e instanceof EnderCrystalEntity) {
                mc.world.removeEntityFromWorld(e.getEntityId());
            }

        }
    }
}









