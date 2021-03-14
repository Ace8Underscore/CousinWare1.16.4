package io.ace.nordclient.hacks;

public class Test extends Hack{

    public Test() {
        super("Test", Category.MISC, -1);
    }

    @Override
    public void onUpdate() {
        mc.player.sendChatMessage("Hi");
    }
}
