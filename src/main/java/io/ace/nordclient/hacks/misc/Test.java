package io.ace.nordclient.hacks.misc;

import io.ace.nordclient.hacks.Hack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Test extends Hack {

    public Test() {
        super("Test", Category.MISC, -1, null);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Text event) {
        mc.fontRenderer.drawStringWithShadow(event.getMatrixStack(), "Test", 100, 100, 13319279);
    }
}
