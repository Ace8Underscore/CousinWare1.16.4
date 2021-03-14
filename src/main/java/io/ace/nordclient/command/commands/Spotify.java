package io.ace.nordclient.command.commands;

import io.ace.nordclient.command.Command;
import net.minecraft.util.SoundEvents;

public class Spotify extends Command {

    @Override
    public String[] getClientAlias() {
        return new String[]{"spotify"};
    }

    @Override
    public String getClientSyntax() {
        return "spotify (songs/play) (music)";
    }

    @Override
    public void onClientCommand(String command, String[] args) throws Exception {

        if (args[0].equalsIgnoreCase("songs")) {
            Command.sendClientSideMessage("Songs: 11, 13, Blocks, Cat, Chirp, Far, Mall, Mellohi, Stal, Strad, Wait, Ward, PigSteps");
        }

        if (args[0].equalsIgnoreCase("play")) {

            mc.player.getSoundCategory().getName();
            switch (args[1]) {
                case "11":
                    mc.player.playSound(SoundEvents.MUSIC_DISC_11, 10, 1);
                    break;

                case "13":
                    mc.player.playSound(SoundEvents.MUSIC_DISC_13, 10, 1);
                    break;

                case "Blocks":
                    mc.player.playSound(SoundEvents.MUSIC_DISC_BLOCKS, 10, 1);
                    break;

                case "Cat":
                    mc.player.playSound(SoundEvents.MUSIC_DISC_CAT, 10, 1f);
                    break;

                case "Chirp":
                    mc.player.playSound(SoundEvents.MUSIC_DISC_CHIRP, 10, 1);
                    break;

                case "Far":
                    mc.player.playSound(SoundEvents.MUSIC_DISC_FAR, 10, 1);
                    break;

                case "Mall":
                    mc.player.playSound(SoundEvents.MUSIC_DISC_MALL, 10, 1);
                    break;

                case "Mellohi":
                    mc.player.playSound(SoundEvents.MUSIC_DISC_MELLOHI, 10, 1);
                    break;

                case "Stal":
                    mc.player.playSound(SoundEvents.MUSIC_DISC_STAL, 10, 1);
                    break;

                case "Strad":
                    mc.player.playSound(SoundEvents.MUSIC_DISC_STRAD, 10, 1);
                    break;

                case "Wait":
                    mc.player.playSound(SoundEvents.MUSIC_DISC_WAIT, 10, 1);
                    break;

                case "Ward":
                    mc.player.playSound(SoundEvents.MUSIC_DISC_WARD, 10, 1);
                    break;

                case "PigSteps":
                    mc.player.playSound(SoundEvents.MUSIC_DISC_PIGSTEP, 10, 1);
                    break;


            }
        }


    }
}
