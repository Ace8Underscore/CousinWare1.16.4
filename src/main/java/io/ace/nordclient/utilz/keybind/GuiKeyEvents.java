package io.ace.nordclient.utilz.keybind;

public class GuiKeyEvents {
    public static int postKeyEvents = 0;

    public static void setPostKeyEvents(boolean post) {
        postKeyEvents += post ? 1 : -1;
    }
    public static boolean postKeyEvents() {
        return postKeyEvents <= 0;
    }
    public static void resetPostKeyEvents() {
        postKeyEvents = 0;
    }
}