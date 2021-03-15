package io.ace.nordclient.gui.components;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.ace.nordclient.gui.Component;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.hacks.client.ClickGuiHack;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.utilz.Setting;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.text.TextFormatting;


import java.awt.*;

public class ModeButton extends Component {
    private boolean hovered;
    private final Button parent;
    private final SettingBase set;
    private int offset;
    private int x;
    private int y;
    private final Hack hack;
    private int modeIndex;
    int maxIndex = 0;

    public ModeButton(final SettingBase set, final Button button, final Hack hack, final int offset) {
        this.set = set;
        this.parent = button;
        this.hack = hack;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
        this.modeIndex = 0;
    }

    @Override
    public void setOff(final int newOff) {
        this.offset = newOff;
    }

    @Override
    public void renderComponent(MatrixStack stack) {
        AbstractGui.fill(stack, this.parent.parent.getX(), this.parent.parent.getY() + this.offset + 1, this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset + 16, this.hovered ? new Color(29, 37, 48, 255).darker().darker().getRGB() : new Color(29, 37, 48, 255).getRGB());
        AbstractGui.fill(stack, this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset + 1, new Color(29, 37, 48, 255).getRGB());
        AbstractGui.fill(stack, this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + 1, this.parent.parent.getY() + this.offset + 16, new Color(165, 147, 44, 255).getRGB());
        //FontUtils.drawStringWithShadow(((ClickGuiModule) ModuleManager.getModuleByName("ClickGui")).customFont.getValue(), this.set.getName() + " " + ChatFormatting.GRAY + this.set.getValue(), this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset + 4, -1);
       // if (!Core.customFont.getValBoolean())
            mc.fontRenderer.drawStringWithShadow(stack, this.set.toMode().text + " " + TextFormatting.GRAY + this.set.toMode().getModeValue(set.toMode().mode), this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset + 4, -1);
       // else
       //     CousinWare.INSTANCE.fontRenderer.drawStringWithShadow(this.set.getDisplayName() + " " + ChatFormatting.GRAY + this.set.getValString(), this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset + 4, -1);
    }

    @Override
    public void updateComponent(final int mouseX, final int mouseY) {
        this.hovered = this.isMouseOnButton(mouseX, mouseY);
        this.y = this.parent.parent.getY() + this.offset;
        this.x = this.parent.parent.getX() - 10;
    }
    //if (!(set.toMode().mode == set.toMode().modes.length) ? maxIndex = this.set.toMode().mode + 1 : maxIndex = this.set.toMode().mode = 0);
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0 && this.parent.open) {
            if (maxIndex == this.set.toMode().modes.length) maxIndex = 0;
            else maxIndex = this.set.toMode().mode;

            this.set.toMode().mode = (set.toMode().getNextMode());

        }
    }

    public boolean isMouseOnButton(final int x, final int y) {
        return x > this.x && x < this.x + 88 + 15 && y > this.y && y < this.y + 16;
    }
}