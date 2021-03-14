package io.ace.nordclient.utilz.keybind;

import io.ace.nordclient.command.commands.Bind;
import net.minecraft.command.arguments.NBTCompoundTagArgument;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_RIGHT;

public class Keybind implements ISerializable<Keybind> {
    private boolean isKey;
    private int value;

    private Keybind(boolean isKey, int value) {
        set(isKey, value);
    }

    public static Keybind fromKey(int key) {
        return new Keybind(true, key);
    }

    public static Keybind fromButton(int button) {
        return new Keybind(false, button);
    }

    public boolean isSet() {
        return value != -1;
    }

    public boolean canBindTo(boolean isKey, int value) {
        if (isKey) return true;
        return value != GLFW_MOUSE_BUTTON_LEFT && value != GLFW_MOUSE_BUTTON_RIGHT;
    }

    public void set(boolean isKey, int value) {
        this.isKey = isKey;
        this.value = value;
    }

    public boolean matches(boolean isKey, int value) {
        if (this.isKey != isKey) return false;
        return this.value == value;
    }

    public boolean isPressed() {
        return isKey ? Input.isKeyPressed(value) : Input.isButtonPressed(value);
    }

    @Override
    public String toString() {
        if (value == -1) return "None";
        return isKey ? Bind.getKeyName(value) : Bind.getButtonName(value);
    }

    // Serialization


}