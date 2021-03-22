package io.ace.nordclient.gui.components;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.ace.nordclient.gui.Component;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.utilz.FontRenderUtil;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.text.TextFormatting;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleSlider extends Component {
    private boolean hovered;
    private final SettingBase set;
    private final Button parent;
    private int offset;
    private int x;
    private int y;
    private boolean dragging;
    private double renderWidth;

    public DoubleSlider(final SettingBase value, final Button button, final int offset) {
        this.dragging = false;
        this.set = value;
        this.parent = button;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
    }

    private static double roundToPlace(final double value, final int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public void renderComponent(MatrixStack stack) {
        AbstractGui.fill(stack, this.parent.parent.getX(), this.parent.parent.getY() + this.offset + 1, this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset + 16, this.hovered ? new Color(29, 37, 48, 255).darker().getRGB() : new Color(29, 37, 48, 255).getRGB());
        final int drag = (int) (this.set.toSlider().getValue() / this.set.toSlider().max * this.parent.parent.getWidth() + 15);
        AbstractGui.fill(stack, this.parent.parent.getX(), this.parent.parent.getY() + this.offset + 15, this.parent.parent.getX() + (int) this.renderWidth, this.parent.parent.getY() + this.offset + 16, this.hovered ? new Color(165, 147, 44, 255).getRGB() : new Color(165, 147, 44, 255).getRGB());
        AbstractGui.fill(stack, this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset + 1, new Color(29, 37, 48, 255).getRGB());
        ////FontUtils.drawStringWithShadow(((ClickGuiModule) ModuleManager.getModuleByName("ClickGui")).customFont.getValue(), this.set.getName() + " " + ChatFormatting.GRAY + this.set.getValue(), (int)(this.parent.parent.getX() + 2), this.parent.parent.getY() + this.offset + 4, -1);
        AbstractGui.fill(stack, this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + 1, this.parent.parent.getY() + this.offset + 16, new Color(165, 147, 44).getRGB());


      // if (!Core.customFont.getValBoolean())
            mc.fontRenderer.drawStringWithShadow(stack, this.set.toSlider().text, (int) (this.parent.parent.getX() + 2), this.parent.parent.getY() + this.offset + 4, -1);
      //  else
         //   CousinWare.INSTANCE.fontRenderer.drawStringWithShadow(this.set.getDisplayName(), this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset + 4, -1);
//
       // if (!Core.customFont.getValBoolean())
            FontRenderUtil.drawLeftStringWithShadow(stack, TextFormatting.GRAY + String.valueOf(this.set.toSlider().value), this.parent.parent.getX() + 95, this.parent.parent.getY() + this.offset + 4, -1);
        //else
        //    FontRenderUtil.drawLeftStringWithShadowCustom(ChatFormatting.GRAY + String.valueOf(this.set.getValDouble()), this.parent.parent.getX() + 94, this.parent.parent.getY() + this.offset + 4, -1);

    }

    @Override
    public void setOff(final int newOff) {
        this.offset = newOff;
    }

    @Override
    public void updateComponent(final int mouseX, final int mouseY) {
        this.hovered = (this.isMouseOnButtonD(mouseX, mouseY) || this.isMouseOnButtonI(mouseX, mouseY));
        this.y = this.parent.parent.getY() + this.offset;
        this.x = this.parent.parent.getX();
        final double diff = Math.min(88, Math.max(0, mouseX - this.x));
        final double min = this.set.toSlider().min;
        final double max = this.set.toSlider().max;
        this.renderWidth = 95 * (this.set.toSlider().value - min) / (max - min);
        if (this.dragging) {
            if (diff == 0.0) {
                this.set.toSlider().setValue(this.set.toSlider().min);
            } else {
                final double newValue = roundToPlace(diff / 88.0 * (max - min) + min, 2);
                this.set.toSlider().setValue(newValue);
            }
        }
    }

    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButtonD(mouseX, mouseY) && button == 0 && this.parent.open) {
            this.dragging = true;
        }
        if (this.isMouseOnButtonI(mouseX, mouseY) && button == 0 && this.parent.open) {
            this.dragging = true;
        }
    }

    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        this.dragging = false;
    }

    public boolean isMouseOnButtonD(final int x, final int y) {
        return x > this.x && x < this.x + (this.parent.parent.getWidth() / 2 + 1) && y > this.y && y < this.y + 16;
    }

    public boolean isMouseOnButtonI(final int x, final int y) {
        return x > this.x + this.parent.parent.getWidth() / 2 && x < this.x + this.parent.parent.getWidth() + 15 && y > this.y && y < this.y + 16;
    }
}