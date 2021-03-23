package io.ace.nordclient.hacks.render;

import io.ace.nordclient.hacks.Hack;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AntiFog extends Hack {

    /**
     * @author Ace________/Ace_#1233
     */

    public AntiFog() {
        super("AntiFog", Category.RENDER, "Removes Fog From Le Word", 3570712, null);
    }

    @SubscribeEvent
    public void fogDensity(EntityViewRenderEvent.FogDensity event) {
        event.setCanceled(true);

    }
}
