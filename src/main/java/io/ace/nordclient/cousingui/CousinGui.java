package io.ace.nordclient.cousingui;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.ace.nordclient.hacks.Hack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CousinGui extends Screen {

    public CousinGui() {
        super(new TranslationTextComponent("menu.game"));
    }

    protected void init() {
        addButtons();
    }

    private void addButtons() {
        for (final Hack.Category category : Hack.Category.values())  {
            this.addButton(new Button(this.width / 2 - 102, this.height / 4 + 24 + -16, 204, 20, new TranslationTextComponent(category.name()), (button2) -> {
                this.minecraft.displayGuiScreen((Screen)null);
                this.minecraft.mouseHelper.grabMouse();
            }));
        }
    }
    public void tick() {
        super.tick();
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);

    }
}
