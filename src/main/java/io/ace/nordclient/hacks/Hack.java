package io.ace.nordclient.hacks;

import io.ace.nordclient.CousinWare;
import io.ace.nordclient.command.Command;
import io.ace.nordclient.managers.HackManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.glfw.GLFW;
//import org.lwjgl.input.Keyboard;


public class Hack {
    public static final Minecraft mc = Minecraft.getInstance();

    public String name;
    public String description;
    public Category category;
    public int bind;
    public boolean enabled;
    public boolean drawn;
    public int color;
    public double anima;
    public boolean visableOnArray;

    public Hack(String hackName, Category hackCategory, int decimalColor) {
        name = hackName;
        description = " ";
        category = hackCategory;
        bind = GLFW.GLFW_KEY_UNKNOWN;
        enabled = false;
        drawn = true;
        this.color = decimalColor;
        anima = 0;
        visableOnArray = false;
    }

    public Hack(String hackName, Category hackCategory, String hackDescription, int decimalColor) {
        name = hackName;
        description = hackDescription;
        category = hackCategory;
        bind = GLFW.GLFW_KEY_UNKNOWN;
        enabled = false;
        drawn = true;
        this.color = decimalColor;

    }

    public int getBind() {
        return bind;
    }

    public void setBind(int b) {
        bind = b;
    }

    public void onUpdate() {
    }

   // public void onWorldRender(RenderEvent event) {
    //}

    protected void onEnable() {
    }

    protected void onDisable() {
    }
//

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean e) {
        enabled = e;
    }

    public boolean isDisabled() {
        return !enabled;
    }

    public boolean isVisableOnArray() {
        return visableOnArray;
    }

    public void toggle() {
        if (isEnabled()) {
            disable();
        } else if (!isEnabled()) {
            enable();
        }
    }

    public void enable() {
        CousinWare.EVENT_BUS.subscribe(this);
        MinecraftForge.EVENT_BUS.register(this);
        visableOnArray = true;
        anima = 0;
        setEnabled(true);
        //if (HackManager.getHackByName("ToggleMsgs").isEnabled() && !this.name.equalsIgnoreCase("clickgui")) {
            Command.sendClientSideMessage("Enabled " + TextFormatting.GREEN + this.name);
        //}
        //MinecraftForge.EVENT_BUS.register(this);
        onEnable();
    }

    public void disable() {
        CousinWare.EVENT_BUS.unsubscribe(this);
        MinecraftForge.EVENT_BUS.unregister(this);
        setEnabled(false);
        //if (HackManager.getHackByName("ToggleMsgs").isEnabled() && !this.name.equalsIgnoreCase("clickgui")) {
            Command.sendClientSideMessage("Disabled " + TextFormatting.RED + this.name);
        //}
        //MinecraftForge.EVENT_BUS.unregister(this);
        onDisable();
    }

    public boolean isDrawn() {
        return drawn;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category c) {
        category = c;
    }

    public int getLength(String name) {
        return HackManager.getHackByName(name).getLength(name);
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getHudInfo() {
        return "";
    }


    public enum Category {
        COMBAT,
        PLAYER,
        MOVEMENT,
        MISC,
        RENDER,
        CLIENT,
        EXPLOIT,
        GUI
    }
}


