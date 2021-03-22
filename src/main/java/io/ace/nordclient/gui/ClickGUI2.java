package io.ace.nordclient.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.managers.HackManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.ArrayList;

@OnlyIn(Dist.CLIENT)
public class ClickGUI2 extends Screen {
    public static ArrayList<io.ace.nordclient.gui.Frame> frames;
    public static int color;
    public static int mouseX;
    public static int mouseY;
    public static int delay = 0;

    private static final ClickGUI2 GUI_RENDERER = new ClickGUI2();


    static {
        ClickGUI2.color = -1;
    }

    public ClickGUI2() {
        super(new TranslationTextComponent("menu.game"));
        ClickGUI2.frames = new ArrayList<io.ace.nordclient.gui.Frame>();
        int frameX = 5;
        for (final Hack.Category category : Hack.Category.values()) {
            final io.ace.nordclient.gui.Frame frame = new io.ace.nordclient.gui.Frame(category);
            frame.setX(frameX);
            ClickGUI2.frames.add(frame);
            frameX += frame.getWidth() + 10;
        }
        MinecraftForge.EVENT_BUS.register(this);

    }

    @Override
    public void init() {
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, final int mouseX, final int mouseY, final float partialTicks) {
        //ClickGUI2.color = new Color(ClickGuiHack.red.getValInt(), ClickGuiHack.green.getValInt(), ClickGuiHack.blue.getValInt(), ClickGuiHack.alpha.getValInt()).getRGB();
        for (final io.ace.nordclient.gui.Frame frame : ClickGUI2.frames) {
            //this.renderBackground(matrixStack);
            frame.renderFrame(matrixStack, this.font);
            frame.updatePosition(mouseX, mouseY);
            for (final Component comp : frame.getComponents()) {
                comp.updateComponent(mouseX, mouseY);
            }
        }
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        delay++;
        if (delay > 5) {
            mouseReleased(mouseX, mouseY, 0);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);

    }

    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        for (final io.ace.nordclient.gui.Frame frame : ClickGUI2.frames) {
            if (frame.isWithinHeader(mouseX, mouseY) && mouseButton == 0) {
                frame.setDrag(true);
                frame.dragX = mouseX - frame.getX();
                frame.dragY = mouseY - frame.getY();
            }
            if (frame.isWithinHeader(mouseX, mouseY) && mouseButton == 1) {
                frame.setOpen(!frame.isOpen());
            }
            if (frame.isOpen() && !frame.getComponents().isEmpty()) {
                for (final Component component : frame.getComponents()) {
                    component.mouseClicked(mouseX, mouseY, mouseButton);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(InputEvent.MouseInputEvent event) throws IOException {
        if (HackManager.getHackByName("ClickGUI").isEnabled()) {
            delay++;
            if (event.getAction() == 1) {
                if (event.getButton() == 0) {
                    if (delay > 20) {
                        mouseClicked(mouseX, mouseY, event.getButton());
                        delay = 0;
                    }
                }
                if (event.getButton() == 1) {
                    if (delay > 20) {
                        mouseClicked(mouseX, mouseY, event.getButton());
                        delay = 0;
                    }
                }
            }
        }
    }



    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        for (final io.ace.nordclient.gui.Frame frame : ClickGUI2.frames) {
            if (frame.isOpen() //&& keyCode != 1
             && !frame.getComponents().isEmpty()) {
                for (final Component component : frame.getComponents()) {
                    component.keyTyped((char) scanCode, keyCode);
                }
            }
        }
        if (keyCode == 256) {
            //Minecraft.getInstance().displayGuiScreen((Screen) null);
        }
        return false;
    }

    @Override
    public void tick() {
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    @Override
    public void closeScreen() {
        this.minecraft.displayGuiScreen((Screen)null);
    }

    protected void mouseReleased(final int mouseX, final int mouseY, final int state) {
        for (final io.ace.nordclient.gui.Frame frame : ClickGUI2.frames) {
            frame.setDrag(false);
        }
        for (final Frame frame : ClickGUI2.frames) {
            if (frame.isOpen() && !frame.getComponents().isEmpty()) {
                for (final Component component : frame.getComponents()) {
                    component.mouseReleased(mouseX, mouseY, state);
                }
            }
        }
    }



    @Override
    public boolean isPauseScreen() {
        return true;
    }
}