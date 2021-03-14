package io.ace.nordclient.command.commands;

import io.ace.nordclient.command.Command;
import net.minecraft.util.text.TextFormatting;

public class Xray extends Command {

    /**
     * @author Ace________/Ace_#1233
     */

    @Override
    public String[] getClientAlias() {
        return new String[]{"Xray", "xray"};
    }

    @Override
    public String getClientSyntax() {
        return "Xray (add/remove) (blockname)";
    }

    @Override
    public void onClientCommand(String command, String[] args) throws Exception {

    }
}
