package io.ace.nordclient.managers;

import io.ace.nordclient.command.Command;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;

public class CommandManager {
    public static ArrayList<Command> commands;
    boolean b;

    public static void addCommand(Command c) {
        commands.add(c);
    }

    public static ArrayList<Command> getClientCommands() {
        return commands;
    }

    public void callClientCommand(String input) {
        String[] split = input.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        String command = split[0];
        String args = input.substring(command.length()).trim();
        b = false;
        commands.forEach(c -> {
            for (String s : c.getClientAlias()) {
                if (s.equalsIgnoreCase(command)) {
                    b = true;
                    try {
                        c.onClientCommand(args, args.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"));
                    } catch (Exception e) {
                        if (!s.equalsIgnoreCase("friend")) {
                            Command.sendClientSideMessage(TextFormatting.RED + c.getClientSyntax());
                        }

                    }
                }
            }
        });
        if (!b) Command.sendClientSideMessage(TextFormatting.RED + "Unknown command!");
    }

}