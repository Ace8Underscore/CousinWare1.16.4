package io.ace.nordclient.utilz.keybind;

import org.lwjgl.glfw.GLFW;

public class Input {
    private static final boolean[] keys = new boolean[512];
    private static final boolean[] buttons = new boolean[16];



    public static void setKeyState(int key, boolean pressed) {
        if (key >= 0 && key < keys.length) keys[key] = pressed;
    }

    public static void setButtonState(int button, boolean pressed) {
        if (button >= 0 && button < buttons.length) buttons[button] = pressed;
    }

    public static boolean isKeyPressed(int key) {
        if (!GuiKeyEvents.postKeyEvents()) return false;

        if (key == GLFW.GLFW_KEY_UNKNOWN) return false;
        return key < keys.length && keys[key];
    }

    public static boolean isButtonPressed(int button) {
        if (button == -1) return false;
        return button < buttons.length && buttons[button];
    }

}