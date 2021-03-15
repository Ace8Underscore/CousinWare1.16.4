package io.ace.nordclient.settings;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class SettingMode extends SettingBase
{
    public String[] modes;
    public int mode;
    public String text;

    public SettingMode(String text, String... modes)
    {
        this.modes = modes;
        this.text = text;
    }

    public String getModeValue(int i) {
        return modes[i];

    }

    public int getNextMode()
    {
        return this.mode + 1 >= this.modes.length ? 0 : this.mode + 1;
    }
}