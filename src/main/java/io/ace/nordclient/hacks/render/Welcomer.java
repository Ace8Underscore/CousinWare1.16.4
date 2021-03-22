package io.ace.nordclient.hacks.render;

import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingSlider;
import io.ace.nordclient.settings.SettingToggle;
import io.ace.nordclient.utilz.RainbowUtil;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class Welcomer extends Hack {

    private static final List<SettingBase> settings = Arrays.asList(new SettingSlider(0, 255, 255, 0, "Red"), new SettingSlider(0, 255, 26, 0, "Green"), new SettingSlider(0, 255, 42, 0, "Blue"), new SettingToggle(false, "Rainbow"));

    public Welcomer() {
        super("Welcomer", Category.RENDER,3093151, settings);
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Text event) {
        String timeMessage = "";
        long time = Calendar.getInstance().getTime().getHours();
        Color c = new Color((int)settings.get(0).toSlider().value, (int)settings.get(1).toSlider().value, (int)settings.get(2).toSlider().value, 255);
        if (time >= 0 && time <= 11) timeMessage = "Good Morning ";
        if (time > 11 && time <= 18) timeMessage = "Good Afternoon ";
        if (time > 18 && time < 24) timeMessage = "Good Night ";
        if (settings.get(3).toToggle().state) RainbowUtil.settingRainbow(settings.get(0), settings.get(1), settings.get(1));
        //


        //if (!Core.customFont.getValBoolean())
         mc.fontRenderer.drawStringWithShadow(event.getMatrixStack(), timeMessage + mc.player.getGameProfile().getName(), (event.getWindow().getScaledWidth() - mc.fontRenderer.getStringWidth(timeMessage + mc.player.getGameProfile().getName())) / 2, 0, c.getRGB());
        //else
            //CousinWare.INSTANCE.fontRenderer.drawStringWithShadow(timeMessage + mc.player.getName(), (event.getWindow().getScaledWidth() - CousinWare.INSTANCE.fontRenderer.getStringWidth(timeMessage + mc.player.getName())) / 2, 0, c.getRGB());
    }
}
