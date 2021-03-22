package io.ace.nordclient.hacks.misc;

import io.ace.nordclient.event.PacketEvent;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.managers.HackManager;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingSlider;
import io.ace.nordclient.settings.SettingToggle;
import io.ace.nordclient.utilz.RainbowUtil;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class NotResponding extends Hack {

    int counter = 0;

    private static final List<SettingBase> settings = Arrays.asList(
            new SettingSlider(0, 255, 255, 1, "Red"),
            new SettingSlider(0, 255, 26, 1, "Green"),
            new SettingSlider(0, 255, 42, 1, "Blue"),
            new SettingToggle(false, "Rainbow"));


    public NotResponding() {
        super("NotResponding", Category.MISC, 14707765, settings);
    }

    @Override
    public void onUpdate() {
        counter++;

    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Text event) {
        Color c = new Color((int)settings.get(0).toSlider().value, (int)settings.get(1).toSlider().value, (int)settings.get(2).toSlider().value, 255);
        if (settings.get(3).toToggle().state) RainbowUtil.settingRainbow(settings.get(0), settings.get(1), settings.get(2));
        double counterRemainder = (counter % 20) / 2;
        double timeNotRespond = counter / 20 + (counterRemainder / 10);

        if (counter >= 20) {
            if (!HackManager.getHackByName("Welcomer").isEnabled()) {
               // if (!Core.customFont.getValBoolean())
                    mc.fontRenderer.drawStringWithShadow(event.getMatrixStack(), "Server is not responding " + timeNotRespond + "s", (event.getWindow().getScaledWidth() - mc.fontRenderer.getStringWidth("Server is not responding " + timeNotRespond + "s")) / 2, 0, c.getRGB());
               // else
               //    CousinWare.INSTANCE.fontRenderer.drawStringWithShadow("Server is not responding " + timeNotRespond + "s", (event.getWindow().getScaledWidth() - CousinWare.INSTANCE.fontRenderer.getStringWidth("Server is not responding " + timeNotRespond + "s")) / 2, 0, c.getRGB());
            } else {
              //  if (!Core.customFont.getValBoolean())
                    mc.fontRenderer.drawStringWithShadow(event.getMatrixStack(), "Server is not responding " + timeNotRespond + "s", (event.getWindow().getScaledWidth() - mc.fontRenderer.getStringWidth("Server is not responding " + timeNotRespond + "s")) / 2, 15, c.getRGB());
              //  else
                   // CousinWare.INSTANCE.fontRenderer.drawStringWithShadow("Server is not responding " + timeNotRespond + "s", (event.getWindow().getScaledWidth() - CousinWare.INSTANCE.fontRenderer.getStringWidth("Server is not responding " + timeNotRespond + "s")) / 2, 15, c.getRGB());

            }

        }


    }

    @EventHandler
    private final Listener<PacketEvent.Receive> listener = new Listener<>(event -> {
        counter = 0;


    });
}
