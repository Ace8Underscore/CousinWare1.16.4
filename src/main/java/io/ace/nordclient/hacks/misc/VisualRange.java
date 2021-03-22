package io.ace.nordclient.hacks.misc;

import io.ace.nordclient.command.Command;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.managers.FriendManager;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingToggle;
import io.ace.nordclient.utilz.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisualRange extends Hack {

    Setting noFriend;
    ArrayList<String> seenPlayers = new ArrayList<>();
    int delay = 0;

    private static final List<SettingBase> settings = Arrays.asList(new SettingToggle(true, "AntiSpam"));


    public VisualRange() {
        super("VisualRange", Category.MISC, 1, settings);

    }

    @Override
    public void onUpdate() {
        for (Entity player : mc.world.getAllEntities()) {
            if (player instanceof PlayerEntity) {
                if (!FriendManager.isFriend(player.getEntity().getScoreboardName())) {
                    if (settings.get(0).toToggle().state) delay++;
                    if (!seenPlayers.contains(player.getEntity().getScoreboardName())) {
                        if (settings.get(0).toToggle().state) {
                            if (delay > 10) {
                                Command.sendClientSideMessage(player.getEntity().getScoreboardName() + " Has Entered Visual Range");
                                seenPlayers.add(player.getEntity().getScoreboardName());
                                delay = 0;
                            }
                        } else {
                            Command.sendClientSideMessage(player.getEntity().getScoreboardName() + " Has Entered Visual Range");
                            seenPlayers.add(player.getEntity().getScoreboardName());
                        }
                    }

                    if (!isInVisualRange(player.getEntity().getScoreboardName())) {
                        if (seenPlayers.toString().contains(player.getEntity().getScoreboardName())) {
                            seenPlayers.remove(player.getEntity().getScoreboardName());
                        }
                    }
                }
            }
        }
    }
    //

    public boolean isInVisualRange(String player) {
        return mc.world.getAllEntities().toString().contains(player);
    }

}


