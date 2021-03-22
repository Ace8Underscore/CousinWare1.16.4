package io.ace.nordclient.utilz.configz;

public class ShutDown extends Thread {

    /**
     * @author Finz0
     *
     **/

    @Override
    public void run(){
        saveConfig();
    }

    public static void saveConfig() {
        FileMang.saveSettings();
        FileMang.saveModules();
        FileMang.saveBinds();
        FileMang.saveFriends();
        FileMang.saveDrawn();
        //CousinWare.INSTANCE.configUtils.saveHudPos();
        // CousinWare.INSTANCE.configUtils.saveXray();

    }
}