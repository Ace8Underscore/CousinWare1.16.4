package io.ace.nordclient.managers;

import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.hacks.Test;
import io.ace.nordclient.hacks.client.ClickGuiHack;
import io.ace.nordclient.hacks.misc.ChatSuffix;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author Ace________/Ace_#1233
 */

public class HackManager {

    public static ArrayList<Hack> hacks;
    private static final String allHackNames = "Hacks: ";
    private static String officialAllHackNames;

    public HackManager() {
        hacks = new ArrayList<>();
        //client
       /* HackManager.addHack(new ClickGuiHack());
        HackManager.addHack(new ClickGuiHack2());
        HackManager.addHack(new ClickGuiHudHack());
        HackManager.addHack(new Core());
        //combat
        HackManager.addHack(new Aura());
        HackManager.addHack(new AutoBedBombDumb());
        HackManager.addHack(new AutoOffHand());
        HackManager.addHack(new AutoPressurePlate());
        HackManager.addHack(new AutoTntMinecart());
        HackManager.addHack(new AutoTotem());
        HackManager.addHack(new AutoTrap());
        HackManager.addHack(new Burrow());
        HackManager.addHack(new Criticals());
        HackManager.addHack(new CrystalAura());
        HackManager.addHack(new FastXp());
        HackManager.addHack(new HoleFiller());
        HackManager.addHack(new KeyPearl());
        HackManager.addHack(new PacketXp());
        HackManager.addHack(new PistonAura2());
        HackManager.addHack(new io.ace.nordclient.hacks.combat.SpeedMine());
        HackManager.addHack(new Surround());
        HackManager.addHack(new Surround2());
        HackManager.addHack(new WebFiller());
        //exploit
        //HackManager.addHack(new AutoMinecartRefill());
        HackManager.addHack(new AntiDesync());
        HackManager.addHack(new Blink());
        //HackManager.addHack(new LagBar());
        HackManager.addHack(new Lagger());
        HackManager.addHack(new NoBreakLoss());
        HackManager.addHack(new NoFallingAni());
        HackManager.addHack(new NoMinecartLag());
        HackManager.addHack(new NoSlowBypass());
        HackManager.addHack(new SpeedMine());
        //misc
        HackManager.addHack(new AntiRegear());
        HackManager.addHack(new AutoWither());
        //HackManager.addHack(new BedrockFinder());
        //HackManager.addHack(new BoatBypass()); */
        addHack(new ClickGuiHack());
        addHack(new Test());
        addHack(new ChatSuffix()); /*

        HackManager.addHack(new DonkeyAlert());
        HackManager.addHack(new DungannonSpammer());
        HackManager.addHack(new EnchantColor());
        HackManager.addHack(new FakePlayer());
        //HackManager.addHack(new HotbarRefill());
        HackManager.addHack(new FancyChat());
        HackManager.addHack(new IllegalFinder());
        HackManager.addHack(new LogoutCoords());
        HackManager.addHack(new MCF());
        HackManager.addHack(new NoEntityTrace());
        HackManager.addHack(new NotResponding());
        // HackManager.addHack(new PlayerEffects());
        HackManager.addHack(new NoInteract());
        HackManager.addHack(new QuickDrop());
        HackManager.addHack(new ShulkerMod());
        HackManager.addHack(new Spammer());
        HackManager.addHack(new ToggleMsgs());
        HackManager.addHack(new VisualRange());
        //HackManager.addHack(new TwoBeePacketLogger());
        //movement
        //
        HackManager.addHack(new ElytraFly());
        HackManager.addHack(new FastSwim());
        HackManager.addHack(new FastWeb());
        HackManager.addHack(new Fly());
        HackManager.addHack(new Jesus());
        HackManager.addHack(new ReverseStep());
        HackManager.addHack(new Sprint());

        HackManager.addHack(new Step());
        HackManager.addHack(new Strafe());
        HackManager.addHack(new Velocity());
        //player
        HackManager.addHack(new AntiVoid());
        HackManager.addHack(new Freecam());
        HackManager.addHack(new GhostGap());
        HackManager.addHack(new NoSlow());
        HackManager.addHack(new NoSlow2b());
        HackManager.addHack(new PacketCanceller());
        HackManager.addHack(new Scaffold());
        HackManager.addHack(new Timer());
        //render
        HackManager.addHack(new AntiFog());
        HackManager.addHack(new io.ace.nordclient.hacks.render.ArrayList());
        HackManager.addHack(new BlockHighlight());
        HackManager.addHack(new ClientName());
        HackManager.addHack(new Crystal());
        HackManager.addHack(new FOVchanger());
        HackManager.addHack(new FriendTab());
        HackManager.addHack(new FullBright());
        HackManager.addHack(new HoleESP());
        HackManager.addHack(new InfiniteChatlength());
        //HackManager.addHack(new ItemESP());
        //HackManager.addHack(new NameTags());
        HackManager.addHack(new NoLag());
        HackManager.addHack(new Overlay());
        HackManager.addHack(new PlayerESP());
        HackManager.addHack(new SelfParticle());
        HackManager.addHack(new SkyColor());
        HackManager.addHack(new StorageESP());
        HackManager.addHack(new Swing());
        HackManager.addHack(new ViewModelChanger());
        HackManager.addHack(new Welcomer());
        HackManager.addHack(new io.ace.nordclient.hacks.render.Xray()); */
    }

    public static void addHack(Hack h) {
        hacks.add(h);
    }

    public static ArrayList<Hack> getHacks() {
        return hacks;
    }

    public static Hack getHackByName(String name) {
        return getHacks().stream().filter(hm -> hm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public static void onUpdate() {
        hacks.stream().filter(Hack::isEnabled).forEach(Hack::onUpdate);
    }

    public static String getAllHackList() {
        HackManager.getHacks()
                .forEach(hack -> {
                    officialAllHackNames = allHackNames + " " + hack.name;

                });

        return officialAllHackNames;
    }

    public static ArrayList<Hack> getHacksInCategory(Hack.Category c) {
        return (ArrayList<Hack>) getHacks().stream().filter(h -> h.getCategory().equals(c)).collect(Collectors.toList());
    }

    public Hack getHack(final Class<? extends Hack> clazz) {
        return HackManager.hacks.stream().filter(hack -> hack.getClass() == clazz).findFirst().orElse(null);
    }

    /*public static void onWorldRender(RenderWorldLastEvent event) {
        Minecraft.getInstance().getProfiler().startSection("nordClient");

        Minecraft.getInstance().getProfiler().startSection("setup");
        GlStateManager.disableTexture();
        GlStateManager.enableBlend();
        GlStateManager.disableAlphaTest();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        GlStateManager.disableDepthTest();

        GlStateManager.glLineWidth(1f);
        Vec3d renderPos = NordTessellator.getInterpolatedPos(Minecraft.getMinecraft().player, event.getPartialTicks());

        RenderEvent e = new RenderEvent(NordTessellator.INSTANCE, renderPos, event.getPartialTicks());
        e.resetTranslation();
        Minecraft.getMinecraft().profiler.endSection();

        hacks.stream().filter(Hack::isEnabled).forEach(module -> {
            Minecraft.getMinecraft().profiler.startSection(module.getName());
            module.onWorldRender(e);
            Minecraft.getMinecraft().profiler.endSection();
        });

        Minecraft.getMinecraft().profiler.startSection("release");
        GlStateManager.glLineWidth(1f);
        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
        NordTessellator.releaseGL();
        Minecraft.getMinecraft().profiler.endSection();
        Minecraft.getMinecraft().profiler.endSection();
    } */


    public static void onBind(int key) {
        if (key == 0) return;
        hacks.forEach(hack -> {
            if (hack.getBind() == key) {
                hack.toggle();
            }
        });
    }
}
