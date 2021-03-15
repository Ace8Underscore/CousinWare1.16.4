package io.ace.nordclient.command;

import net.minecraft.client.Minecraft;
import net.minecraft.command.arguments.MessageArgument;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.TextFormatting;

public abstract class Command {

    /**
     * @author Ace________/Ace_#1233
     */

    public static final Minecraft mc = Minecraft.getInstance();
    public static String prefix = ".";

    public static void sendClientSideMessage(String message) {
        if (mc.world == null || mc.player == null) return;
        mc.player.sendMessage(new StringTextComponent(TextFormatting.DARK_RED + "[CousinWare]" + " " + TextFormatting.WHITE + message), mc.player.getUniqueID());
    }

    public static String getClientPrefix() {
        return prefix;
    }

    public static void setClientPrefix(String p) {
        prefix = p;
    }

    public abstract String[] getClientAlias();

    public abstract String getClientSyntax();

    public abstract void onClientCommand(String command, String[] args) throws Exception;

//
}
