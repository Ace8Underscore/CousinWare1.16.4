package io.ace.nordclient;

import io.ace.nordclient.event.EventProcessor;
import io.ace.nordclient.gui.ClickGUI;
import io.ace.nordclient.managers.CommandManager;
import io.ace.nordclient.managers.FriendManager;
import io.ace.nordclient.managers.HackManager;
import io.ace.nordclient.managers.SettingsManager;
import io.ace.nordclient.utilz.configz.ConfigUtils;
import io.ace.nordclient.utilz.configz.ShutDown;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.stiff.pomelo.EventManager;
import team.stiff.pomelo.impl.annotated.AnnotatedEventManager;

@Mod(modid = NordClient.MODID, name = NordClient.NAME, version = NordClient.VERSION)
public class NordClient
{
    public static final String MODID = "nordclient";
    public static final String NAME = "NordClient";
    public static final String VERSION = "a1.0";

    public static final Logger log = LogManager.getLogger(NAME);
    private EventManager eventManager;
    EventProcessor eventProcessor;
    public HackManager hackManager;
    public ConfigUtils configUtils;
    public FriendManager friends;
    public SettingsManager settingsManager;
    public ClickGUI clickGui;

    //public ClickGUI clickGui;





    @Mod.Instance
    public static NordClient INSTANCE;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        eventProcessor = new EventProcessor();
        eventProcessor.init();
        CommandManager.initClientCommands();

        settingsManager = new SettingsManager();


        hackManager = new HackManager();
        configUtils = new ConfigUtils();
        clickGui = new ClickGUI();
        friends = new FriendManager();
        Runtime.getRuntime().addShutdownHook(new ShutDown());
    }

    public EventManager getEventManager() {
        if (this.eventManager == null) {
            this.eventManager = new AnnotatedEventManager();
        }

        return this.eventManager;
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        //MinecraftForge.EVENT_BUS.register(new TestCommand());
    }

}