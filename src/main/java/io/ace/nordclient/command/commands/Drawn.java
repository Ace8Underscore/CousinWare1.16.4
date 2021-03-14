package io.ace.nordclient.command.commands;

import io.ace.nordclient.command.Command;
import io.ace.nordclient.managers.HackManager;
import net.minecraft.util.text.TextFormatting;

public class Drawn extends Command {

    /**
     * @author Ace________/Ace_#1233
     */

    private boolean found;

    @Override
    public String[] getClientAlias() {
        return new String[]{"draw", "drawn"};
    }

    @Override
    public String getClientSyntax() {
        return "drawn (Hack)";
    }

    @Override
    public void onClientCommand(String command, String[] args) throws Exception {


        found = false;
        HackManager.getHacks().forEach(m -> {
            if (m.getName().equalsIgnoreCase(args[0])) {
                if (m.isDrawn()) {
                    m.drawn = false;
                    found = true;
                    Command.sendClientSideMessage(args[0] + " Was" + TextFormatting.GREEN + " Drawn!");
                } else if (!m.isDrawn()) {
                    m.drawn = true;
                    found = true;
                    Command.sendClientSideMessage(args[0] + " Was" + TextFormatting.RED + " UnDrawn!");
                }
            }
        });
        if (!found && args.length == 1) Command.sendClientSideMessage(TextFormatting.DARK_RED + "Hack not found!");

    }
}



