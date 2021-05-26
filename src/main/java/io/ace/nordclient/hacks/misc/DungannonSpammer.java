package io.ace.nordclient.hacks.misc;

import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingSlider;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DungannonSpammer extends Hack {

    int delay = 0;

    private static final List<SettingBase> settings = Arrays.asList(new SettingSlider(1, 1000, 500, 1, "Delay"));


    public DungannonSpammer() {
        super("DungannonSpammer", Category.MISC, 8329630, settings);
    }

    @Override
    public void onUpdate() {
        delay++;
        if (delay > settings.get(0).toSlider().getValue()) {
            doMessage();
            delay = 0;
        }
    }

    private void doMessage() {
        List<String> dungWords = Arrays.asList("> Join Team Dungannon today! https://discord.gg/mt5vUMU", "> Dungannon OWNS ME AND ALL", "> STFU NEWFAG IM IN DUNGANNON AND YOU'RE NOT", "> Join Dungannon Recruitment Today!", "> JayAmazingness Was In Dungannon Join Today", "> Did You Know Team Dungannon Has Killed The Legendary Pvper Xdolf https://www.youtube.com/watch?v=iNe35crzCG4", "> Guess Who Owned 2b2t Spawn During 2019 DUNGANNON! JOIN TODAY https://discord.gg/mt5vUMU", "> Yea Im In Dungannon. Wanna Join? Okay! https://discord.gg/mt5vUMU", "> #JAYAMAZINGNESS DID NOTHING WRONG");
        Random r = new Random();

        int randomitem = r.nextInt(dungWords.size());
        String randomElement = dungWords.get(randomitem);
        mc.player.sendChatMessage(randomElement);
    } //


}
