package io.ace.nordclient.utilz;

import io.ace.nordclient.CousinWare;
import io.ace.nordclient.event.PacketEvent;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.SUpdateTimePacket;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;

/*
 * @author 086
 */
public class TpsUtils {
    private static final float[] tickRates = new float[20];
    private int nextIndex = 0;
    private long timeLastTimeUpdate;

    public TpsUtils() {
        nextIndex = 0;
        timeLastTimeUpdate = -1L;
        Arrays.fill(tickRates, 0.0F);
        Minecraft mc = Minecraft.getInstance();
        CousinWare.EVENT_BUS.subscribe(this);
    }

    public static float getTickRate() {
        float numTicks = 0.0F;
        float sumTickRates = 0.0F;
        for (float tickRate : tickRates) {
            if (tickRate > 0.0F) {
                sumTickRates += tickRate;
                numTicks += 1.0F;
            }
        }
        return MathHelper.clamp(sumTickRates / numTicks, 0.0F, 20.0F);
    }

    private void onTimeUpdate() {
        if (this.timeLastTimeUpdate != -1L) {
            float timeElapsed = (float) (System.currentTimeMillis() - this.timeLastTimeUpdate) / 1000.0F;
            tickRates[(this.nextIndex % tickRates.length)] = MathHelper.clamp(20.0F / timeElapsed, 0.0F, 20.0F);
            this.nextIndex += 1;
        }
        this.timeLastTimeUpdate = System.currentTimeMillis();
    }

    @EventHandler
    private Listener<PacketEvent.Receive> listener = new Listener<>(event -> {
            if (event.getPacket() instanceof SUpdateTimePacket) {
                onTimeUpdate();
        }
    });
}
