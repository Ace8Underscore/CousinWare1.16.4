package io.ace.nordclient.hacks.misc;


import io.ace.nordclient.command.Command;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingMode;
import io.ace.nordclient.settings.SettingToggle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.horse.DonkeyEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.passive.horse.MuleEntity;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ace________/Ace_#1233
 */

public class DonkeyAlert extends Hack {
    private int antiSpam;

   /* private Setting<Boolean> donkeyAlert = register(Settings.b("Donkey", true));
    private Setting<Boolean> muleAlert = register(Settings.b("Mule", true));
    private Setting<Boolean> llamaAlert = register(Settings.b("Llama", true));
    private Setting<Boolean> horseAlert = register(Settings.b("Horse", false));
*/
    private static final List<SettingBase> settings = Arrays.asList(
           new SettingMode("Mode", "Black", "Red", "Aqua", "Blue", "Gold", "Gray", "White", "Green", "Yellow", "Dark_Red", "Dark_Aqua", "Dark_Blue", "Dark_Gray", "Dark_Green", "Dark_Purple", "Light_Purple"),
           new SettingToggle(true, "Donkey"),
           new SettingToggle(true, "Mule"),
           new SettingToggle(true, "Llama"),
           new SettingToggle(true, "Horse"));


    public DonkeyAlert() {
        super("DonkeyAlert", Category.MISC, "Announces the location of any donkeys in the players render distance", 15726257, settings);
    }

    @Override
    public void onUpdate() {
        antiSpam++;

        for (Entity e : mc.world.getAllEntities()) {
            if (e instanceof DonkeyEntity && settings.get(1).toToggle().state) {
                if (antiSpam >= 100) {
                    Command.sendClientSideMessage(colorchoice() + " Found Donkey!" + " X:" + e.getPosX() + " Z:" + e.getPosZ());
                    antiSpam = -600;
                }
            }
            if (e instanceof MuleEntity && settings.get(2).toToggle().state) {
                if (antiSpam >= 100) {
                    Command.sendClientSideMessage(colorchoice() + " Found Mule!" + " X:" + e.getPosX() + " Z:" + e.getPosZ());
                    antiSpam = -600;
                }

            }
            if (e instanceof LlamaEntity && settings.get(3).toToggle().state) {
                if (antiSpam >= 100) {
                    Command.sendClientSideMessage(colorchoice() + " Found Llama!" + " X:" + e.getPosX() + " Z:" + e.getPosZ());
                    antiSpam = -600;
                }

            }
            if (e instanceof HorseEntity && settings.get(4).toToggle().state) {
                if (antiSpam >= 100) {
                    Command.sendClientSideMessage(colorchoice() + " Found Horse!" + " X:" + e.getPosX() + " Z:" + e.getPosZ());
                    antiSpam = -600;
                }

            }

        }
    }

    private TextFormatting colorchoice() {
        switch (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).toUpperCase()) {
            case "BLACK":
                return TextFormatting.BLACK;
            case "RED":
                return TextFormatting.RED;
            case "AQUA":
                return TextFormatting.AQUA;
            case "BLUE":
                return TextFormatting.BLUE;
            case "GOLD":
                return TextFormatting.GOLD;
            case "GRAY":
                return TextFormatting.GRAY;
            case "WHITE":
                return TextFormatting.WHITE;
            case "GREEN":
                return TextFormatting.GREEN;
            case "YELLOW":
                return TextFormatting.YELLOW;
            case "DARK_RED":
                return TextFormatting.DARK_RED;
            case "DARK_AQUA":
                return TextFormatting.DARK_AQUA;
            case "DARK_BLUE":
                return TextFormatting.DARK_BLUE;
            case "DARK_GRAY":
                return TextFormatting.DARK_GRAY;
            case "DARK_GREEN":
                return TextFormatting.DARK_GREEN;
            case "DARK_PURPLE":
                return TextFormatting.LIGHT_PURPLE;
            case "LIGHT_PURPLE":
                return TextFormatting.DARK_PURPLE;
            default:
                return TextFormatting.WHITE;
        }


    }

    private enum color {
        BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE
    }

}





