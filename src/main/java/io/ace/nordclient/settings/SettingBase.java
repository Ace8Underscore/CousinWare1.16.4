package io.ace.nordclient.settings;

public class SettingBase
{
    public SettingMode toMode()
    {
        try
        {
            return (SettingMode) this;
        } catch (Exception e)
        {
            System.out.println("Unable To Parse Setting");

            return new SettingMode("PARSING ERROR");
        }
    }

    public SettingToggle toToggle()
    {
        try
        {
            return (SettingToggle) this;
        } catch (Exception e)
        {
            System.out.println("Unable To Parse Setting");

            return new SettingToggle(false, "PARSING ERROR");
        }
    }

    public SettingSlider toSlider()
    {
        try
        {
            return (SettingSlider) this;
        } catch (Exception e)
        {
            System.out.println("Unable To Parse Setting");

            return new SettingSlider(0.0D, 1.0D, 0.0D, 0, "PARSING ERROR");
        }
    }
}