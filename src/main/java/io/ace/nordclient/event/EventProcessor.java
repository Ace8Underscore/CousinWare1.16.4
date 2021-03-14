package io.ace.nordclient.event;

import io.ace.nordclient.CousinWare;
import io.ace.nordclient.command.Command;
import io.ace.nordclient.command.commands.Bind;
import io.ace.nordclient.managers.CommandManager;
import io.ace.nordclient.managers.HackManager;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.NewChatGui;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.IngameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;
//import org.lwjgl.input.Keyboard;


public class EventProcessor {
    /**
     * thx finz0
     */


    public static final Minecraft mc = Minecraft.getInstance();

    CommandManager commandManager = new CommandManager();

    public void init() {
        CousinWare.EVENT_BUS.subscribe(this);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChatSent(ClientChatEvent event) {

        if (event.getMessage().startsWith(Command.getClientPrefix())) {
            event.setCanceled(true);
            try {
                mc.ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
                commandManager.callClientCommand(event.getMessage().substring(1));
            } catch (Exception e) {
                e.printStackTrace();
                Command.sendClientSideMessage(TextFormatting.DARK_RED + "Error: " + e.getMessage());
            }

        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (event.getAction() == 1) {
            if (event.toString().equals(" ") || event.toString().equalsIgnoreCase("SPACE")) return;
            int key = event.getKey();
            //mc.player.sendChatMessage(String.valueOf(event.getKey()));
            if (mc.currentScreen == null) {
                HackManager.getHacks().forEach(h -> {
                    if (h.getBind() == key) {
                        HackManager.onBind(key);
                    }
                });

            }
        }
    }

    @SubscribeEvent
    public void onWorldRender(RenderWorldLastEvent event) {
        if (event.isCanceled()) return;
        //HackManager.onWorldRender(event);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        //rainbow stuff
        if (mc.player != null || mc.world != null) {
            HackManager.onUpdate();
        }
    }

    @SubscribeEvent
    public void onInputUpdate(InputUpdateEvent event) {
        if (mc.player != null && mc.world != null) CousinWare.EVENT_BUS.post(event);
    }

    @EventHandler
    private final Listener<UpdateEvent> listener = new Listener<>(event -> {
        if (mc.player != null || mc.world != null) {
            //HackManager.onUpdate();
        }
    });

}









