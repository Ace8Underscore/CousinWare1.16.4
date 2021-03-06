package io.ace.nordclient.gui.components;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.ace.nordclient.gui.Component;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.utilz.FontRenderUtil;
import net.minecraft.client.gui.AbstractGui;

import java.awt.*;

public class CheckBox extends Component {
    private boolean hovered;
    private final SettingBase op;
    private final io.ace.nordclient.gui.components.Button parent;
    private int offset;
    private int x;
    private int y;

    public CheckBox(final SettingBase option, final Button button, final int offset) {
        this.op = option;
        this.parent = button;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
    }

    @Override
    public void renderComponent(MatrixStack stack) {
        Color click = new Color(165, 147, 44, 255);
        AbstractGui.fill(stack, this.parent.parent.getX(), this.parent.parent.getY() + this.offset + 1, this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset + 16, this.hovered ? (this.op.toToggle().state ?
                new Color(29, 37, 48, 255).getRGB() :
                new Color(29, 37, 48, 255).darker().getRGB()) : (this.op.toToggle().state ?
                new Color(29, 37, 48, 255).getRGB() :
                new Color(29, 37, 48, 255).getRGB()));
        AbstractGui.fill(stack, this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset + 1, new Color(29, 37, 48, 255).getRGB());
        //FontUtils.drawStringWithShadow(((ClickGuiModule) ModuleManager.getModuleByName("ClickGui")).customFont.getValInt(), this.op.getName(), this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset + 4, -1);
        AbstractGui.fill(stack, this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + 1, this.parent.parent.getY() + this.offset + 16, new Color(165, 147, 44, 255).getRGB());

       // if (!Core.customFont.getValBoolean())
            FontRenderUtil.drawCenteredStringWithShadow(stack, this.op.toToggle().text, (float) (this.parent.parent.getX() + 47.5), this.parent.parent.getY() + this.offset + 4, -1);
       // else
       //     FontRenderUtil.drawCenteredStringWithShadowCustom(this.op.getDisplayName(), (float) (this.parent.parent.getX() + 47.5), this.parent.parent.getY() + this.offset + 4, -1);

        //if (!Core.customFont.getValBoolean()) {
            if (this.op.toToggle().state) FontRenderUtil.drawCenteredStringWithShadow(stack, this.op.toToggle().text, (float) (this.parent.parent.getX() + 47.5), this.parent.parent.getY() + this.offset + 4, click.getRGB());
            else FontRenderUtil.drawCenteredStringWithShadow(stack, this.op.toToggle().text, (float) (this.parent.parent.getX() + 47.5), this.parent.parent.getY() + this.offset + 4, -1);
       // } else {
       //     if (this.op.getValBoolean())
        //        FontRenderUtil.drawCenteredStringWithShadowCustom(this.op.getDisplayName(), (float) (this.parent.parent.getX() + 47.5), this.parent.parent.getY() + this.offset + 4, click.getRGB());
        //    else
         //       FontRenderUtil.drawCenteredStringWithShadowCustom(this.op.getDisplayName(), (float) (this.parent.parent.getX() + 47.5), this.parent.parent.getY() + this.offset + 4, -1);

       // }
    }

    @Override
    public void setOff(final int newOff) {
        this.offset = newOff;
    }

    @Override
    public void updateComponent(final int mouseX, final int mouseY) {
        this.hovered = this.isMouseOnButton(mouseX, mouseY);
        this.y = this.parent.parent.getY() + this.offset;
        this.x = this.parent.parent.getX() - 10;
    }

    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0 && this.parent.open) {
            this.op.toToggle().state = (!this.op.toToggle().state);
        }
    }

    public boolean isMouseOnButton(final int x, final int y) {
        return x > this.x && x < this.x + 88 + 15 && y > this.y && y < this.y + 16;
    }
}