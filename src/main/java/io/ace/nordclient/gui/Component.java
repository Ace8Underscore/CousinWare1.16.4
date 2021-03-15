package io.ace.nordclient.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.ace.nordclient.gui.components.Button;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Component {

    public int width;
    public int height;

    protected Minecraft mc = Minecraft.getInstance();

    public void renderComponent(MatrixStack stack) {
    }

    public void updateComponent(final int mouseX, final int mouseY) {
    }

    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
    }

    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
    }

    public int getParentHeight() {
        return 0;
    }

    public void keyTyped(final char typedChar, final int key) {
    }

    public void setOff(final int newOff) {
    }

    public int getHeight() {
        return 0;
    }


}