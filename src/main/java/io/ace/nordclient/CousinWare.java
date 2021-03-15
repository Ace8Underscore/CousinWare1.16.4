package io.ace.nordclient;

import io.ace.nordclient.command.Command;
import io.ace.nordclient.command.commands.Xray;
import io.ace.nordclient.command.commands.*;
import io.ace.nordclient.cousingui.CousinGui;
import io.ace.nordclient.event.EventLaunch;
import io.ace.nordclient.event.EventProcessor;
import io.ace.nordclient.gui.ClickGUI2;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.hacks.Test;
import io.ace.nordclient.hacks.misc.*;
import io.ace.nordclient.hwid.HWID;
import io.ace.nordclient.managers.*;
import io.ace.nordclient.setting2.ValueManager;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingMode;
import io.ace.nordclient.settings.SettingSlider;
import io.ace.nordclient.utilz.TpsUtils;
import io.ace.nordclient.utilz.configz.ConfigUtils;
import io.ace.nordclient.utilz.configz.ShutDown;
import io.ace.nordclient.utilz.font.CFontRenderer;
import me.zero.alpine.EventBus;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.Font;
import java.lang.management.ManagementFactory;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Mod(CousinWare.MODID)
public class CousinWare {
    public static final String MODID = "cousinware";
    public static final String NAME = "CousinWare";
    public static final String VERSION = "v1.6.5";

    public static final Logger log = LogManager.getLogger(NAME);
    public static CousinWare INSTANCE;
    public ValueManager valueManager;
    public HackManager hackManager;
    //public HudManager hudManager;
    public ConfigUtils configUtils;
    public FriendManager friends;
    public SettingsManager settingsManager;
    public CousinGui cousinGui;
    public ClickGUI2 clickGui2;
    //public ClickGuiHUD clickGuiHUD;
    public CFontRenderer fontRenderer;
    public HWID hwid;
    EventProcessor eventProcessor;

    public static final EventBus EVENT_BUS = new me.zero.alpine.EventManager();

    public CousinWare() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::postInit);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void setup(FMLConstructModEvent event){

//
    }

    public void init(FMLClientSetupEvent event) {
        eventProcessor = new EventProcessor();
        eventProcessor.init();
        hackManager = new HackManager();
        loadClientCommands();
        TpsUtils tpsUtils = new TpsUtils();
       // settingsManager = new SettingsManager();
        for (Hack h : HackManager.getHacks()) {
            for (SettingBase s : h.getSettings()) {
                if (s instanceof SettingMode) {
                    s.toMode().mode = MathHelper.clamp(s.toMode().mode, 0, s.toMode().modes.length - 1);
                } else if (s instanceof SettingSlider) {
                    s.toSlider().value = MathHelper.clamp(s.toSlider().value, s.toSlider().min, s.toSlider().max);
                }
            }
        }
        friends = new FriendManager();
        loadHuds();

        fontRenderer = new CFontRenderer(new Font("Verdana", Font.PLAIN, 17), true, false);
        cousinGui = new CousinGui();
        clickGui2 = new ClickGUI2();
        //clickGuiHUD = new ClickGuiHUD();


        try {
            utils();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }


    public void utils() throws URISyntaxException {
        EventLaunch.init();
    }
    //

    public void postInit(FMLLoadCompleteEvent event) {
        /*String currentHWID = String.valueOf(Runtime.getRuntime().availableProcessors() +
                //System.getenv("PROCESSOR_IDENTIFIER") +
                //System.getenv("PROCESSOR_ARCHITECTURE") +
                //System.getenv("PROCESSOR_ARCHITEW6432") +
                ////System.getenv("NUMBER_OF_PROCESSORS") +
                ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize());
        if (!HWID.isGoodHWID(currentHWID)) {
            //FMLCommonHandler.instance().exitJava(0, true);
            //System.exit(0);
        } */
        //configUtils = new ConfigUtils();
        //Runtime.getRuntime().addShutdownHook(new ShutDown());

    }

    public static String getTitle(String in) {
        in = Character.toUpperCase(in.toLowerCase().charAt(0)) + in.toLowerCase().substring(1);
        return in;
    }

    public void loadClientCommands() {
        CommandManager.commands = new ArrayList<>();
        CommandManager.addCommand(new Help());
        CommandManager.addCommand(new Prefix());
        CommandManager.addCommand(new AllCommands());
        CommandManager.addCommand(new Toggle());
        CommandManager.addCommand(new Hacks());
        CommandManager.addCommand(new Drawn());
        CommandManager.addCommand(new Bind());
        CommandManager.addCommand(new Friend());
        CommandManager.addCommand(new FriendList());
        CommandManager.addCommand(new Ping());
        CommandManager.addCommand(new Description());
        CommandManager.addCommand(new Stack());
        CommandManager.addCommand(new Summon());
        CommandManager.addCommand(new Pyro());
        CommandManager.addCommand(new Set());
       // CommandManager.addCommand(new SetHud());
        CommandManager.addCommand(new Setting());
        CommandManager.addCommand(new ReloadSound());
        CommandManager.addCommand(new Spotify());
        CommandManager.addCommand(new RideEntity());
        CommandManager.addCommand(new io.ace.nordclient.command.commands.Font());
        CommandManager.addCommand(new Xray());
        CommandManager.addCommand(new Crash());

    }

    public void loadHuds() {
        //HudManager.hudElement = new ArrayList<>();

       // HudManager.addHud(new ArmorHud());
       // HudManager.addHud(new io.ace.nordclient.hud.hudcomponets.Crystal());
       // HudManager.addHud(new Exp());
       // HudManager.addHud(new Gapple());
       // HudManager.addHud(new GoonSquad());
       // HudManager.addHud(new InventoryPreview());
       // HudManager.addHud(new Obsidian());
       // HudManager.addHud(new Totem());

    }

}

