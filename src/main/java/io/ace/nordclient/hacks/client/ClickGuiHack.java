package io.ace.nordclient.hacks.client;

import io.ace.nordclient.CousinWare;
import io.ace.nordclient.gui.ClickGUI2;
import io.ace.nordclient.hacks.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import org.lwjgl.glfw.GLFW;

public class ClickGuiHack extends Hack {
    public ClickGuiHack INSTANCE;

    public ClickGuiHack() {
        super("ClickGUI", Category.CLIENT, "Opens the ClickGUI", 12126976, null);
        setBind(GLFW.GLFW_KEY_Y);
        INSTANCE = this;
        this.drawn = false;


        /*CousinWare.INSTANCE.settingsManager.rSetting(red = new Setting("Red", this, 165, 0, 255, true, "ClickGuiHackRed"));
        CousinWare.INSTANCE.settingsManager.rSetting(green = new Setting("Green", this, 147, 0, 255, true, "ClickGuiHackGreen"));
        CousinWare.INSTANCE.settingsManager.rSetting(blue = new Setting("Blue", this, 44, 0, 255, true, "ClickGuiHackBlue"));
        CousinWare.INSTANCE.settingsManager.rSetting(alpha = new Setting("Alpha", this, 255, 0, 255, true, "ClickGuiHackAlpha"));
        CousinWare.INSTANCE.settingsManager.rSetting(descriptions = new Setting("Descriptions", this, true, "ClickGuiHackDescriptions"));
        CousinWare.INSTANCE.settingsManager.rSetting(noise = new Setting("Sound", this, true, "ClickGuiHackSound"));
        CousinWare.INSTANCE.settingsManager.rSetting(rainbow = new Setting("Rainbow", this, false, "ClickGuiHackRainbow"));
 */
    }


    @Override
    public void onEnable() {
        //this.mc.displayGuiScreen(new CousinGui());
        this.mc.displayGuiScreen(new ClickGUI2());
        try {
            if (CousinWare.INSTANCE.fontRenderer.getFontName().equalsIgnoreCase("null")) {
                CousinWare.INSTANCE.fontRenderer.setFontName("Arial");
                CousinWare.INSTANCE.fontRenderer.setFontSize(18);
            }
        } catch (Exception ignored) {

//
        }
    }

    @Override
    public void onDisable() {
        Minecraft.getInstance().displayGuiScreen((Screen) null);
    }

}

