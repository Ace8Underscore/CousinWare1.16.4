package io.ace.nordclient.command.commands;

import io.ace.nordclient.CousinWare;
import io.ace.nordclient.command.Command;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.managers.HackManager;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingMode;
import io.ace.nordclient.settings.SettingSlider;

import java.awt.*;
import java.util.Arrays;

public class Set extends Command {
    @Override
    public String[] getClientAlias() {
        return new String[]{"set"};
    }

    @Override
    public String getClientSyntax() {
        return "set <Module> <Setting> <Value>";
    }

    @Override
    public void onClientCommand(String command, String[] args) throws Exception {
        for (Hack h : HackManager.getHacks()) {
            if (h.getName().equalsIgnoreCase(args[0])) {
                for (SettingBase s : h.getSettings()) {
                    if (s instanceof SettingSlider) {
                        if (Double.parseDouble(args[2]) > ((SettingSlider) s).max) ((SettingSlider) s).setValue(((SettingSlider) s).max);
                        if (Double.parseDouble(args[2]) < ((SettingSlider) s).min) ((SettingSlider) s).setValue(((SettingSlider) s).min);
                        if (!(Double.parseDouble(args[2]) > ((SettingSlider) s).max) && !(Double.parseDouble(args[2]) < ((SettingSlider) s).min))
                            ((SettingSlider) s).setValue(Double.parseDouble(args[2]));
                        Command.sendClientSideMessage(((SettingSlider) s).text + " set to " + ((SettingSlider) s).getValue());
                    }
                   // if (s.isCheck()) {
                     //   s.setValBoolean(Boolean.parseBoolean(args[2]));
                     //   Command.sendClientSideMessage(s.getDisplayName() + " set to " + s.getValBoolean());
                   // }
                   // if (s instanceof SettingMode) {
                   //     if (!Arrays.toString(s.toMode().modes).contains(args[2])) return;
                    //    s.(args[2]);
                    //    Command.sendClientSideMessage(((SettingMode) s).text + " set to " + s.toMode().text);
                  //  }
                  //  if (s.isColorPicker()) {
                  //      s.setValColor(Color.getColor(args[2]));
                  //      Command.sendClientSideMessage(s.getDisplayName() + " set to " + s.getValColor());
                   // }
                   // if (s.isCustomString()) {
                   //     s.setCustomVal(args[2]);
                   //     Command.sendClientSideMessage(s.getDisplayName() + " set to " + s.getCustomVal());
                   // }
                }
            //}
        }
    }
    }
}