package io.ace.nordclient.gui.components;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import io.ace.nordclient.CousinWare;
import io.ace.nordclient.command.Command;
import io.ace.nordclient.gui.ClickGUI2;
import io.ace.nordclient.gui.Component;
import io.ace.nordclient.gui.Frame;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.hacks.client.ClickGuiHack;
import io.ace.nordclient.managers.HackManager;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingMode;
import io.ace.nordclient.settings.SettingSlider;
import io.ace.nordclient.settings.SettingToggle;
import io.ace.nordclient.utilz.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Button extends Component {
    public Hack hack;
    public Frame parent;
    public int offset;
    public boolean open;
    private boolean isHovered;
    private final ArrayList<Component> subcomponents;
    private final int height;
    public static int mouseX1;
    public static int mouseY1;
    public static boolean clicked = false;
    public static int delay = 0;

    public Button(final Hack hack, final Frame parent, final int offset) {
        this.hack = hack;
        this.parent = parent;
        this.offset = offset;
        this.subcomponents = new ArrayList<Component>();
        this.open = false;
        this.height = 16;
        int opY = offset + 16;
        /*if (CousinWare.INSTANCE.settingsManager.getSettingsByMod(hack) != null && !CousinWare.INSTANCE.settingsManager.getSettingsByMod(hack).isEmpty()) {
            for (final Setting s : CousinWare.INSTANCE.settingsManager.getSettingsByMod(hack)) {
                if (s.isCombo()) {
                    this.subcomponents.add(new ModeButton(s, this, hack, opY));
                    opY += 16;
                    continue;
                }
                   /* case STRING: {
                        this.subcomponents.add(new StringButton((Setting.s)s, this, opY));
                        opY += 16;
                        continue;
                    } */
              /*  else if (s.isCheck()) {
                    this.subcomponents.add(new CheckBox(s, this, opY));
                    opY += 16;
                    continue;
                } else if (s.isSlider()) {
                    this.subcomponents.add(new DoubleSlider(s, this, opY));
                    opY += 16;
                    continue;
                } */
                  /*  case INT: {
                        this.subcomponents.add(new IntSlider((Setting)s, this, opY));
                        opY += 16;
                        continue;
                    } */
        for (Iterator i = (hack).getSettings().iterator(); i.hasNext(); ) {
            SettingBase s = (SettingBase) i.next();

            if (s instanceof SettingMode) {
                this.subcomponents.add(new ModeButton(s.toMode(), this, hack, opY));
                opY += 16;
                continue;
            }

            if (s instanceof SettingToggle) {
                this.subcomponents.add(new CheckBox(s.toToggle(), this, opY));
                opY += 16;
                continue;
            }

            if (s instanceof SettingSlider) {
                this.subcomponents.add(new DoubleSlider(s.toSlider(), this, opY));
                opY += 16;
                continue;
            }
        }

        this.subcomponents.add(new Keybind(this, opY));
    }




    @Override
    public void setOff(final int newOff) {
        this.offset = newOff;
        int opY = this.offset + 16;
        for (final Component comp : this.subcomponents) {
            comp.setOff(opY);
            opY += 16;
        }
    }

    @Override
    public void renderComponent(MatrixStack stack) {
        Color c = new Color(165, 147, 44, 255);
       // AbstractGui.fill(stack, this.parent.getX(), this.parent.getY() + this.offset + 1, this.parent.getX() + this.parent.getWidth(), this.parent.getY() + 16 + this.offset, this.isHovered ? (this.hack.isEnabled() ? new Color(29, 37, 48, ClickGuiHack.alpha.getValInt()).getRGB() : new Color(29, 37, 48, ClickGuiHack.alpha.getValInt()).darker().darker().getRGB()) : (this.hack.isEnabled() ? new Color(29, 37, 48, ClickGuiHack.alpha.getValInt()).getRGB() : new Color(29, 37, 48, ClickGuiHack.alpha.getValInt()).getRGB()));
       // AbstractGui.fill(stack, this.parent.getX(), this.parent.getY() + this.offset, this.parent.getX() + this.parent.getWidth(), this.parent.getY() + this.offset + 1, new Color(29, 37, 48, ClickGuiHack.alpha.getValInt()).getRGB());
        //FontUtils.drawStringWithShadow(((ClickGuiModule) ModuleManager.getModuleByName("ClickGui")).customFont.getValInt(), this.mod.getName(), this.parent.getX() + 2, this.parent.getY() + this.offset + 2 + 2, -1);
        AbstractGui.fill(stack, this.parent.getX(), this.parent.getY() + this.offset + 1, this.parent.getX() + this.parent.getWidth(), this.parent.getY() + 16 + this.offset, this.isHovered ? (this.hack.isEnabled() ? new Color(29, 37, 48, 255).getRGB() : new Color(29, 37, 48, 255).darker().darker().getRGB()) : (this.hack.isEnabled() ? new Color(29, 37, 48, 255).getRGB() : new Color(29, 37, 48, 255).getRGB()));
        AbstractGui.fill(stack, this.parent.getX(), this.parent.getY() + this.offset, this.parent.getX() + this.parent.getWidth(), this.parent.getY() + this.offset + 1, new Color(29, 37, 48, 255).getRGB());

        // if (!Core.customFont.getValBoolean()) {
            if (this.hack.isEnabled())
                mc.fontRenderer.drawStringWithShadow(stack,this.hack.getName(), this.parent.getX() + 2, this.parent.getY() + this.offset + 2 + 2, c.getRGB());
            else
                mc.fontRenderer.drawStringWithShadow(stack,this.hack.getName(), this.parent.getX() + 2, this.parent.getY() + this.offset + 2 + 2, -1);
       // } else {
          //  if (this.hack.isEnabled())
          //      CousinWare.INSTANCE.fontRenderer.drawStringWithShadow(this.hack.getName(), this.parent.getX() + 2, this.parent.getY() + this.offset + 2 + 2, c.getRGB());
          //  else
           //     CousinWare.INSTANCE.fontRenderer.drawStringWithShadow(this.hack.getName(), this.parent.getX() + 2, this.parent.getY() + this.offset + 2 + 2, -1);
 //}


        if (this.subcomponents.size() > 1) {
            GlStateManager.pushMatrix();
            GlStateManager.color4f(1, 1, 1, 1);
            GlStateManager.translatef(0, 0, 4);
            GlStateManager.lineWidth(100);
            GlStateManager.popMatrix();
            //FontUtils.drawStringWithShadow(((ClickGuiModule) ModuleManager.getModuleByName("ClickGui")).customFont.getValInt(), this.open ? "-" : "+", this.parent.getX() + this.parent.getWidth() - 10, this.parent.getY() + this.offset + 2 + 2, -1);
          //  if (!Core.customFont.getValBoolean())
                mc.fontRenderer.drawStringWithShadow(stack, this.open ? "v" : ">", this.parent.getX() + this.parent.getWidth() - 10, this.parent.getY() + this.offset + 2 + 2, -1);
          //  else
            //    CousinWare.INSTANCE.fontRenderer.drawStringWithShadow(this.open ? "v" : ">", this.parent.getX() + this.parent.getWidth() - 10, this.parent.getY() + this.offset + 2 + 2, -1);
        }
        if (this.open && !this.subcomponents.isEmpty()) {
            for (final Component comp : this.subcomponents) {
                comp.renderComponent(stack);
                //Gui.drawRect(this.parent.getX(), this.parent.getY() + this.offset + 1, this.parent.getX() + 1, this.parent.getY() + this.offset + 16, new Color(ClickGuiHack.red.getValInt(), ClickGuiHack.green.getValInt(), ClickGuiHack.blue.getValInt(), ClickGuiHack.alpha.getValInt()).getRGB());
            }
        }
        if (clicked) {
            delay++;
            if (delay > 20) {
                clicked = false;
                delay = 0;
            }
        }
       // if (this.isHovered && mc.gameSettings.keyBindAttack.isPressed()) {
         //   hack.toggle();
       // }
    }

    @Override
    public int getHeight() {
        if (this.open) {
            return 16 * (this.subcomponents.size() + 1);
        }
        return 16;
    }

    @Override
    public void updateComponent(final int mouseX, final int mouseY) {
        this.isHovered = this.isMouseOnButton(mouseX, mouseY);
        this.mouseX1 = mouseX;
        this.mouseY1 = mouseY;
        if (this.isHovered //&& ClickGuiHack.descriptions.getValBoolean()
        ) {
          //  if (!Core.customFont.getValBoolean())
          //      mc.fontRenderer.drawStringWithShadow(this.hack.getDescription(), mouseX + 12, mouseY + 4, -1);
          //  else
           //     CousinWare.INSTANCE.fontRenderer.drawStringWithShadow(this.hack.getDescription(), mouseX + 12, mouseY + 4, -1);

        }
        if (!this.subcomponents.isEmpty()) {
            for (final Component comp : this.subcomponents) {
                comp.updateComponent(mouseX, mouseY);
            }
        }
    }

/*    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(InputEvent.MouseInputEvent event) {
        if (event.getAction() == 0) {
            if (isHovered && event.getButton() == 0) {
                this.hack.toggle();
                Minecraft.getInstance().player.playSound(SoundEvents.UI_BUTTON_CLICK, 2f, 1f);
            }
        }
    } */

    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0) {
            if (!clicked) {
                this.hack.toggle();
                //if (ClickGuiHack.noise.getValBoolean()) {
                if (mc.player == null || mc.world == null) return;
                Minecraft.getInstance().player.playSound(SoundEvents.UI_BUTTON_CLICK, 2f, 1f);
                clicked = true;
            }

            //}
        }
        if (this.isMouseOnButton(mouseX, mouseY) && button == 1) {
            this.open = !this.open;
            this.parent.refresh();
        }
        for (final Component comp : this.subcomponents) {
            comp.mouseClicked(mouseX, mouseY, button);
        }
    }

    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        for (final Component comp : this.subcomponents) {
            comp.mouseReleased(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    public void keyTyped(final char typedChar, final int key) {
        for (final Component comp : this.subcomponents) {
            comp.keyTyped(typedChar, key);
        }
    }

    public boolean isMouseOnButton(final int x, final int y) {
        return x > this.parent.getX() && x < this.parent.getX() + this.parent.getWidth() && y > this.parent.getY() + this.offset && y < this.parent.getY() + 16 + this.offset;
    }
}