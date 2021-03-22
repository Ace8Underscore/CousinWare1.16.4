package io.ace.nordclient.hacks.render;

import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.managers.HackManager;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingMode;
import io.ace.nordclient.settings.SettingSlider;
import io.ace.nordclient.settings.SettingToggle;
import io.ace.nordclient.utilz.FontRenderUtil;
import io.ace.nordclient.utilz.RainbowUtil;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ArrayList extends Hack {

    /**
     * @author Ace________/Ace_#1233
     */

    int hackCount;

    Color c;

    int displayX;
    int anima = 0;
    int delay = 0;

    private static final List<SettingBase> settings = Arrays.asList(
            new SettingSlider(0, 255, 255, 0, "Red"),
            new SettingSlider(0, 255, 26, 0, "Green"),
            new SettingSlider(0, 255, 42, 0, "Blue"),
            new SettingToggle(false, "Rainbow"),
            new SettingSlider(0, 2000, 1, 0, "X"),
            new SettingSlider(0, 2000, 3, 0, "Y"),
            new SettingMode("Side", "Left", "Right"),
            new SettingMode("Order", "Up", "Down"),
            new SettingToggle(true, "Static"),
            new SettingToggle(true, "Animation"),
            new SettingSlider(1, 20, 1, 0, "AnimationDelay"));

    public ArrayList() {
        super("ArrayList", Category.RENDER, 29700, settings);
        this.drawn = false;
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Text event) {
        if (mc.world == null)
            return;
        hackCount = 0;
        //anima = 0;
        HackManager.getHacks()
                .stream()
                .filter(Hack::isVisableOnArray)
                .filter(Hack::isDrawn)
                .sorted(Comparator.comparing(hack -> mc.fontRenderer.getStringWidth(hack.getName() + hack.getHudInfo()) * (-1)))
                .forEach(h -> {
                    if (settings.get(9).toToggle().state) {
                        if (this.getSettings().get(6).toMode().getModeValue(getSettings().get(6).toMode().mode).equalsIgnoreCase("left")) {
                            if (h.anima < 50 && h.isEnabled()) h.anima += 1 / settings.get(10).toSlider().getValue();
                            if (h.anima != 0 && h.isDisabled()) h.anima -= 1 / settings.get(10).toSlider().getValue();
                            displayX = (int) ((float)settings.get(4).toSlider().getValue() - 50 + h.anima);
                        } else {
                            if (h.anima < 50 && h.isEnabled()) h.anima += 1 / settings.get(10).toSlider().getValue();
                            if (h.anima != 0 && h.isDisabled()) h.anima -= 1 / settings.get(10).toSlider().getValue();
                            displayX = (int) ((float)settings.get(4).toSlider().getValue() + 50 - h.anima);
                        }
                    }

                    if (h.isDisabled() && h.anima <= 0) h.visableOnArray = false;
                    //if (!Core.customFont.getValBoolean()) {
                        if (this.getSettings().get(7).toMode().getModeValue(getSettings().get(7).toMode().mode).equalsIgnoreCase("up") && this.getSettings().get(6).toMode().getModeValue(getSettings().get(6).toMode().mode).equalsIgnoreCase("left")) {
                            if (settings.get(3).toToggle().state) {
                                c = new Color((int)settings.get(0).toSlider().value, (int)settings.get(1).toSlider().value, (int)settings.get(2).toSlider().value);
                                mc.fontRenderer.drawStringWithShadow(event.getMatrixStack(),h.getName() + TextFormatting.GRAY + " " + h.getHudInfo(), settings.get(9).toToggle().state ? displayX : (float)(float)settings.get(4).toSlider().getValue(), (float) settings.get(5).toSlider().getValue() + (hackCount * 10), RainbowUtil.getRainbow(hackCount * 150));
                                hackCount++;
                                RainbowUtil.settingRainbow(settings.get(0), settings.get(1), settings.get(2));
                            } else {
                                c = new Color((int)settings.get(0).toSlider().value, (int)settings.get(1).toSlider().value, (int)settings.get(2).toSlider().value);
                                mc.fontRenderer.drawStringWithShadow(event.getMatrixStack(),h.getName() + TextFormatting.GRAY + " " + h.getHudInfo(), settings.get(9).toToggle().state ? displayX : (float)settings.get(4).toSlider().getValue(), (float) settings.get(5).toSlider().getValue() + (hackCount * 10), settings.get(8).toToggle().state ? h.color : settings.get(8).toToggle().state ? h.color : c.getRGB());
                                hackCount++;

                            }
                        }
                        if (this.getSettings().get(7).toMode().getModeValue(getSettings().get(7).toMode().mode).equalsIgnoreCase("down") && this.getSettings().get(6).toMode().getModeValue(getSettings().get(6).toMode().mode).equalsIgnoreCase("right")) {
                            if (settings.get(3).toToggle().state) {
                                c = new Color((int)settings.get(0).toSlider().value, (int)settings.get(1).toSlider().value, (int)settings.get(2).toSlider().value);
                                FontRenderUtil.drawLeftStringWithShadow(event.getMatrixStack(),h.getName() + TextFormatting.GRAY + " " + h.getHudInfo(), settings.get(9).toToggle().state ? displayX : (float)settings.get(4).toSlider().getValue(), (float) settings.get(5).toSlider().getValue() - (hackCount * 10), RainbowUtil.getRainbow(hackCount * 150));
                                hackCount++;
                                RainbowUtil.settingRainbow(settings.get(0), settings.get(1), settings.get(2));
                            } else {
                                c = new Color((int)settings.get(0).toSlider().value, (int)settings.get(1).toSlider().value, (int)settings.get(2).toSlider().value);
                                FontRenderUtil.drawLeftStringWithShadow(event.getMatrixStack(),h.getName() + TextFormatting.GRAY + " " + h.getHudInfo(), settings.get(9).toToggle().state ? displayX : (float)settings.get(4).toSlider().getValue(), (float) settings.get(5).toSlider().getValue() - (hackCount * 10), settings.get(8).toToggle().state ? h.color : c.getRGB());
                                hackCount++;

                            }
                        }

                        if (this.getSettings().get(7).toMode().getModeValue(getSettings().get(7).toMode().mode).equalsIgnoreCase("up") && this.getSettings().get(6).toMode().getModeValue(getSettings().get(6).toMode().mode).equalsIgnoreCase("right")) {
                            if (settings.get(3).toToggle().state) {
                                c = new Color((int)settings.get(0).toSlider().value, (int)settings.get(1).toSlider().value, (int)settings.get(2).toSlider().value);
                                FontRenderUtil.drawLeftStringWithShadow(event.getMatrixStack(),h.getName() + TextFormatting.GRAY + " " + h.getHudInfo(), settings.get(9).toToggle().state ? displayX : (float)settings.get(4).toSlider().getValue(), (float) settings.get(5).toSlider().getValue() + (hackCount * 10), RainbowUtil.getRainbow(hackCount * 150));
                                hackCount++;
                                RainbowUtil.settingRainbow(settings.get(0), settings.get(1), settings.get(2));
                            } else {
                                c = new Color((int)settings.get(0).toSlider().value, (int)settings.get(1).toSlider().value, (int)settings.get(2).toSlider().value);
                                FontRenderUtil.drawLeftStringWithShadow(event.getMatrixStack(),h.getName() + TextFormatting.GRAY + " " + h.getHudInfo(), settings.get(9).toToggle().state ? displayX : (float)settings.get(4).toSlider().getValue(), (float) settings.get(5).toSlider().getValue() + (hackCount * 10), settings.get(8).toToggle().state ? h.color : c.getRGB());
                                hackCount++;

                            }
                        }

                        if (this.getSettings().get(7).toMode().getModeValue(getSettings().get(7).toMode().mode).equalsIgnoreCase("down") && this.getSettings().get(6).toMode().getModeValue(getSettings().get(6).toMode().mode).equalsIgnoreCase("left")) {
                            if (settings.get(3).toToggle().state) {
                                c = new Color((int)settings.get(0).toSlider().value, (int)settings.get(1).toSlider().value, (int)settings.get(2).toSlider().value);
                                mc.fontRenderer.drawStringWithShadow(event.getMatrixStack(),h.getName() + TextFormatting.GRAY + " " + h.getHudInfo(), settings.get(9).toToggle().state ? displayX : (float)settings.get(4).toSlider().getValue(), (float) settings.get(5).toSlider().getValue() + (hackCount * -10), RainbowUtil.getRainbow(hackCount * 150));
                                hackCount++;
                                RainbowUtil.settingRainbow(settings.get(0), settings.get(1), settings.get(2));
                            } else {
                                c = new Color((int)settings.get(0).toSlider().value, (int)settings.get(1).toSlider().value, (int)settings.get(2).toSlider().value);
                                mc.fontRenderer.drawStringWithShadow(event.getMatrixStack(), h.getName() + TextFormatting.GRAY + " " + h.getHudInfo(), settings.get(9).toToggle().state ? displayX : (float)settings.get(4).toSlider().getValue(), (float) settings.get(5).toSlider().getValue() + (hackCount * -10), settings.get(8).toToggle().state ? h.color : c.getRGB());
                                hackCount++;

                            }
                        }
                    //}
                   /* if (Core.customFont.getValBoolean()) {
                        if (orderMode.getValString().equalsIgnoreCase("up") && sideMode.getValString().equalsIgnoreCase("left")) {
                            if (rainbow.getValBoolean()) {
                                c = new Color(settings.get(0).toSlider().value, settings.get(1).toSlider().value, b.getValInt());
                                CousinWare.INSTANCE.fontRenderer.drawStringWithShadow(h.getName() + TextFormatting.GRAY + " " + h.getHudInfo(), settings.get(9).toToggle().state ? displayX : (float)settings.get(4).toSlider().getValue(), (float) settings.get(5).toSlider().getValue() + (hackCount * 10), RainbowUtil.getRainbow(hackCount * 150));
                                hackCount++;
                                RainbowUtil.settingRainbow(r, g, b);
                            } else {
                                c = new Color(settings.get(0).toSlider().value, settings.get(1).toSlider().value, b.getValInt());
                                CousinWare.INSTANCE.fontRenderer.drawStringWithShadow(h.getName() + TextFormatting.GRAY + " " + h.getHudInfo(), settings.get(9).toToggle().state ? displayX : (float)settings.get(4).toSlider().getValue(), (float) settings.get(5).toSlider().getValue() + (hackCount * 10), staticc.getValBoolean() ? h.color : c.getRGB());
                                hackCount++;

                            }
                        }
                        if (orderMode.getValString().equalsIgnoreCase("down") && sideMode.getValString().equalsIgnoreCase("right")) {
                            if (rainbow.getValBoolean()) {
                                c = new Color(settings.get(0).toSlider().value, settings.get(1).toSlider().value, b.getValInt());
                                FontRenderUtil.drawLeftStringWithShadowCustom(h.getName() + TextFormatting.GRAY + " " + h.getHudInfo(), settings.get(9).toToggle().state ? displayX : (float)settings.get(4).toSlider().getValue(), (float) settings.get(5).toSlider().getValue() - (hackCount * 10), RainbowUtil.getRainbow(hackCount * 150));
                                hackCount++;
                                RainbowUtil.settingRainbow(r, g, b);
                            } else {
                                c = new Color(settings.get(0).toSlider().value, settings.get(1).toSlider().value, b.getValInt());
                                FontRenderUtil.drawLeftStringWithShadowCustom(h.getName() + TextFormatting.GRAY + " " + h.getHudInfo(), settings.get(9).toToggle().state ? displayX : (float)settings.get(4).toSlider().getValue(), (float) settings.get(5).toSlider().getValue() - (hackCount * 10), staticc.getValBoolean() ? h.color : c.getRGB());
                                hackCount++;


                            }
                        }

                        if (orderMode.getValString().equalsIgnoreCase("up") && sideMode.getValString().equalsIgnoreCase("right")) {
                            if (rainbow.getValBoolean()) {
                                c = new Color(settings.get(0).toSlider().value, settings.get(1).toSlider().value, b.getValInt());
                                FontRenderUtil.drawLeftStringWithShadowCustom(h.getName() + TextFormatting.GRAY + " " + h.getHudInfo(), settings.get(9).toToggle().state ? displayX : (float)settings.get(4).toSlider().getValue(), (float) settings.get(5).toSlider().getValue() + (hackCount * 10), RainbowUtil.getRainbow(hackCount * 150));
                                hackCount++;
                                if (anima < 200 && h.isEnabled()) anima++;
                                RainbowUtil.settingRainbow(r, g, b);
                            } else {
                                c = new Color(settings.get(0).toSlider().value, settings.get(1).toSlider().value, b.getValInt());
                                FontRenderUtil.drawLeftStringWithShadowCustom(h.getName() + TextFormatting.GRAY + " " + h.getHudInfo(), settings.get(9).toToggle().state ? displayX : (float)settings.get(4).toSlider().getValue(), (float) settings.get(5).toSlider().getValue() + (hackCount * 10), staticc.getValBoolean() ? h.color : c.getRGB());
                                hackCount++;
                                if (anima < 200 && h.isEnabled()) anima++;

                            }
                        }

                        if (orderMode.getValString().equalsIgnoreCase("down") && sideMode.getValString().equalsIgnoreCase("left")) {
                            if (rainbow.getValBoolean()) {
                                c = new Color(settings.get(0).toSlider().value, settings.get(1).toSlider().value, b.getValInt());
                                CousinWare.INSTANCE.fontRenderer.drawStringWithShadow(h.getName() + TextFormatting.GRAY + " " + h.getHudInfo(), settings.get(9).toToggle().state ? displayX : (float)settings.get(4).toSlider().getValue(), (float) settings.get(5).toSlider().getValue() + (hackCount * -10), RainbowUtil.getRainbow(hackCount * 150));
                                hackCount++;
                                if (anima < 200 && h.isEnabled()) anima++;
                                RainbowUtil.settingRainbow(r, g, b);
                            } else {
                                c = new Color(settings.get(0).toSlider().value, settings.get(1).toSlider().value, b.getValInt());
                                CousinWare.INSTANCE.fontRenderer.drawStringWithShadow(h.getName() + TextFormatting.GRAY + " " + h.getHudInfo(), settings.get(9).toToggle().state ? displayX : (float)settings.get(4).toSlider().getValue(), (float) settings.get(5).toSlider().getValue() + (hackCount * -10), staticc.getValBoolean() ? h.color : c.getRGB());
                                hackCount++;
                                if (anima < 200 && h.isEnabled()) anima++;

                            }
                        }
                    } */

                });


    }

}
