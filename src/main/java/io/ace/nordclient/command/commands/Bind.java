package io.ace.nordclient.command.commands;

import io.ace.nordclient.command.Command;
import io.ace.nordclient.managers.HackManager;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.glfw.GLFW;

public class Bind extends Command {

    boolean bind = false;
    String hackToBind = "";

    @Override
    public String[] getClientAlias() {
        return new String[]{"bind", "b"};
    }

    @Override
    public String getClientSyntax() {
        return "bind (Hack) (Key)";
    }

    @Override
    public void onClientCommand(String command, String[] args) throws Exception {
            HackManager.getHacks().forEach(h -> {
                if (args[0].equalsIgnoreCase(h.getName())) {
                    h.setBind(getKeyInt(args[1]));
                    Command.sendClientSideMessage(args[0] + " binded to " + args[1].toUpperCase());
                }

            });

    }

    public static String getKeyName(int key) {
        switch (key) {
            case GLFW.GLFW_KEY_UNKNOWN: return "Unknown";
            case GLFW.GLFW_KEY_ESCAPE: return "Esc";
            case GLFW.GLFW_KEY_PRINT_SCREEN: return "Print Screen";
            case GLFW.GLFW_KEY_PAUSE: return "Pause";
            case GLFW.GLFW_KEY_INSERT: return "Insert";
            case GLFW.GLFW_KEY_HOME: return "Home";
            case GLFW.GLFW_KEY_PAGE_UP: return "Page Up";
            case GLFW.GLFW_KEY_PAGE_DOWN: return "Page Down";
            case GLFW.GLFW_KEY_END: return "End";
            case GLFW.GLFW_KEY_TAB: return "Tab";
            case GLFW.GLFW_KEY_LEFT_CONTROL: return "Left Control";
            case GLFW.GLFW_KEY_RIGHT_CONTROL: return "Right Control";
            case GLFW.GLFW_KEY_LEFT_ALT: return "Left Alt";
            case GLFW.GLFW_KEY_RIGHT_ALT: return "Right Alt";
            case GLFW.GLFW_KEY_LEFT_SHIFT: return "Left Shift";
            case GLFW.GLFW_KEY_RIGHT_SHIFT: return "Right Shift";
            case GLFW.GLFW_KEY_UP: return "Arrow Up";
            case GLFW.GLFW_KEY_DOWN: return "Arrow Down";
            case GLFW.GLFW_KEY_LEFT: return "Arrow Left";
            case GLFW.GLFW_KEY_RIGHT: return "Arrow Right";
            case GLFW.GLFW_KEY_APOSTROPHE: return "Apostrophe";
            case GLFW.GLFW_KEY_BACKSPACE: return "Backspace";
            case GLFW.GLFW_KEY_CAPS_LOCK: return "Caps Lock";
            case GLFW.GLFW_KEY_MENU: return "Menu";
            case GLFW.GLFW_KEY_LEFT_SUPER: return "Left Super";
            case GLFW.GLFW_KEY_RIGHT_SUPER: return "Right Super";
            case GLFW.GLFW_KEY_ENTER: return "Enter";
            case GLFW.GLFW_KEY_NUM_LOCK: return "Num Lock";
            case GLFW.GLFW_KEY_SPACE: return "Space";
            case GLFW.GLFW_KEY_F1: return "F1";
            case GLFW.GLFW_KEY_F2: return "F2";
            case GLFW.GLFW_KEY_F3: return "F3";
            case GLFW.GLFW_KEY_F4: return "F4";
            case GLFW.GLFW_KEY_F5: return "F5";
            case GLFW.GLFW_KEY_F6: return "F6";
            case GLFW.GLFW_KEY_F7: return "F7";
            case GLFW.GLFW_KEY_F8: return "F8";
            case GLFW.GLFW_KEY_F9: return "F9";
            case GLFW.GLFW_KEY_F10: return "F10";
            case GLFW.GLFW_KEY_F11: return "F11";
            case GLFW.GLFW_KEY_F12: return "F12";
            case GLFW.GLFW_KEY_F13: return "F13";
            case GLFW.GLFW_KEY_F14: return "F14";
            case GLFW.GLFW_KEY_F15: return "F15";
            case GLFW.GLFW_KEY_F16: return "F16";
            case GLFW.GLFW_KEY_F17: return "F17";
            case GLFW.GLFW_KEY_F18: return "F18";
            case GLFW.GLFW_KEY_F19: return "F19";
            case GLFW.GLFW_KEY_F20: return "F20";
            case GLFW.GLFW_KEY_F21: return "F21";
            case GLFW.GLFW_KEY_F22: return "F22";
            case GLFW.GLFW_KEY_F23: return "F23";
            case GLFW.GLFW_KEY_F24: return "F24";
            case GLFW.GLFW_KEY_F25: return "F25";
            default:
                String keyName = GLFW.glfwGetKeyName(key, 0);
                if (keyName == null) return "Unknown";
                return StringUtils.capitalize(keyName);
        }
    }
    public static int getKeyInt(String key) {
        switch (key.toLowerCase()) {
            case "a" : return GLFW.GLFW_KEY_A;
            case "b" : return GLFW.GLFW_KEY_B;
            case "c" : return GLFW.GLFW_KEY_C;
            case "d" : return GLFW.GLFW_KEY_D;
            case "e" : return GLFW.GLFW_KEY_E;
            case "f" : return GLFW.GLFW_KEY_F;
            case "g" : return GLFW.GLFW_KEY_G;
            case "h" : return GLFW.GLFW_KEY_H;
            case "i" : return GLFW.GLFW_KEY_I;
            case "j" : return GLFW.GLFW_KEY_J;
            case "k" : return GLFW.GLFW_KEY_K;
            case "l" : return GLFW.GLFW_KEY_L;
            case "m" : return GLFW.GLFW_KEY_M;
            case "n" : return GLFW.GLFW_KEY_N;
            case "o" : return GLFW.GLFW_KEY_O;
            case "p" : return GLFW.GLFW_KEY_P;
            case "q" : return GLFW.GLFW_KEY_Q;
            case "r" : return GLFW.GLFW_KEY_R;
            case "s" : return GLFW.GLFW_KEY_S;
            case "t" : return GLFW.GLFW_KEY_T;
            case "u" : return GLFW.GLFW_KEY_U;
            case "v" : return GLFW.GLFW_KEY_V;
            case "w" : return GLFW.GLFW_KEY_W;
            case "x" : return GLFW.GLFW_KEY_X;
            case "y" : return GLFW.GLFW_KEY_Y;
            case "z" : return GLFW.GLFW_KEY_Z;
            case "`" : return GLFW.GLFW_KEY_GRAVE_ACCENT;
            case "tab" : return GLFW.GLFW_KEY_TAB;
            case "capslock" : return GLFW.GLFW_KEY_CAPS_LOCK;
            case "lshift" : return GLFW.GLFW_KEY_LEFT_SHIFT;
            case "lctrl" : return GLFW.GLFW_KEY_LEFT_CONTROL;
            case "lalt" : return GLFW.GLFW_KEY_LEFT_ALT;
            case "1" : return GLFW.GLFW_KEY_1;
            case "2" : return GLFW.GLFW_KEY_2;
            case "3" : return GLFW.GLFW_KEY_3;
            case "4" : return GLFW.GLFW_KEY_4;
            case "5" : return GLFW.GLFW_KEY_5;
            case "6" : return GLFW.GLFW_KEY_6;
            case "7" : return GLFW.GLFW_KEY_7;
            case "8" : return GLFW.GLFW_KEY_8;
            case "9" : return GLFW.GLFW_KEY_9;
            case "0" : return GLFW.GLFW_KEY_0;
            case "f1" : return GLFW.GLFW_KEY_F1;
            case "f2" : return GLFW.GLFW_KEY_F2;
            case "f3" : return GLFW.GLFW_KEY_F3;
            case "f4" : return GLFW.GLFW_KEY_F4;
            case "f5" : return GLFW.GLFW_KEY_F5;
            case "f6" : return GLFW.GLFW_KEY_F6;
            case "f7" : return GLFW.GLFW_KEY_F7;
            case "f8" : return GLFW.GLFW_KEY_F8;
            case "f9" : return GLFW.GLFW_KEY_F9;
            case "f10" : return GLFW.GLFW_KEY_F10;
            case "f11" : return GLFW.GLFW_KEY_F11;
            case "f12" : return GLFW.GLFW_KEY_F12;
            case "[" : return GLFW.GLFW_KEY_LEFT_BRACKET;
            case "]" : return GLFW.GLFW_KEY_RIGHT_BRACKET;
            case "\"" : return GLFW.GLFW_KEY_BACKSLASH;
            case "-" : return GLFW.GLFW_KEY_MINUS;
            case "=" : return GLFW.GLFW_KEY_EQUAL;
            case ";" : return GLFW.GLFW_KEY_SEMICOLON;
            case "'" : return GLFW.GLFW_KEY_APOSTROPHE;
            case "," : return GLFW.GLFW_KEY_COMMA;
            case "." : return GLFW.GLFW_KEY_PERIOD;
            case "/" : return GLFW.GLFW_KEY_SLASH;
            case "rshift" : return GLFW.GLFW_KEY_RIGHT_SHIFT;
            case "rctrl" : return GLFW.GLFW_KEY_RIGHT_CONTROL;
            case "ralt" : return GLFW.GLFW_KEY_RIGHT_ALT;
            case "up" : return GLFW.GLFW_KEY_UP;
            case "down" : return GLFW.GLFW_KEY_DOWN;
            case "left" : return GLFW.GLFW_KEY_LEFT;
            case "right" : return GLFW.GLFW_KEY_RIGHT;
            case "printscreen" : return GLFW.GLFW_KEY_PRINT_SCREEN;
            case "scrolllock" : return GLFW.GLFW_KEY_SCROLL_LOCK;
            case "pausebreak" : return GLFW.GLFW_KEY_PAUSE;
            case "insert" : return GLFW.GLFW_KEY_INSERT;
            case "home" : return GLFW.GLFW_KEY_HOME;
            case "end" : return GLFW.GLFW_KEY_END;
            case "pagedown" : return GLFW.GLFW_KEY_PAGE_DOWN;
            case "numlock" : return GLFW.GLFW_KEY_NUM_LOCK;
            case "num*" : return GLFW.GLFW_KEY_KP_MULTIPLY;
            case "num7" : return GLFW.GLFW_KEY_KP_7;
            case "num8" : return GLFW.GLFW_KEY_KP_8;
            case "num9" : return GLFW.GLFW_KEY_KP_9;
            case "num+" : return GLFW.GLFW_KEY_KP_ADD;
            case "num/" : return GLFW.GLFW_KEY_KP_DIVIDE;
            case "num4" : return GLFW.GLFW_KEY_KP_4;
            case "num5" : return GLFW.GLFW_KEY_KP_5;
            case "num6" : return GLFW.GLFW_KEY_KP_6;
            case "num1" : return GLFW.GLFW_KEY_KP_1;
            case "num2" : return GLFW.GLFW_KEY_KP_2;
            case "num3" : return GLFW.GLFW_KEY_KP_3;
            case "num0" : return GLFW.GLFW_KEY_KP_0;
            case "num." : return GLFW.GLFW_KEY_KP_DECIMAL;

            case "none" : return -189321754;
            default: return -189321754;

        }
    }

    public static String getKeyString(int key) {
        switch (key) {
            case GLFW.GLFW_KEY_A : return "a";
            case GLFW.GLFW_KEY_B : return "b";
            case GLFW.GLFW_KEY_C : return "c";
            case GLFW.GLFW_KEY_D : return "d";
            case GLFW.GLFW_KEY_E : return "e";
            case GLFW.GLFW_KEY_F : return "f";
            case GLFW.GLFW_KEY_G : return "g";
            case GLFW.GLFW_KEY_H : return "h";
            case GLFW.GLFW_KEY_I : return "i";
            case GLFW.GLFW_KEY_J : return "j";
            case GLFW.GLFW_KEY_K : return "k";
            case GLFW.GLFW_KEY_L : return "l";
            case GLFW.GLFW_KEY_M : return "m";
            case GLFW.GLFW_KEY_N : return "n";
            case GLFW.GLFW_KEY_O : return "o";
            case GLFW.GLFW_KEY_P : return "p";
            case GLFW.GLFW_KEY_Q : return "q";
            case GLFW.GLFW_KEY_R : return "r";
            case GLFW.GLFW_KEY_S : return "s";
            case GLFW.GLFW_KEY_T : return "t";
            case GLFW.GLFW_KEY_U : return "u";
            case GLFW.GLFW_KEY_V : return "v";
            case GLFW.GLFW_KEY_W : return "w";
            case GLFW.GLFW_KEY_X : return "x";
            case GLFW.GLFW_KEY_Y : return "y";
            case GLFW.GLFW_KEY_Z : return "z";
            case GLFW.GLFW_KEY_GRAVE_ACCENT : return "`";
            case GLFW.GLFW_KEY_TAB : return "Tab";
            case GLFW.GLFW_KEY_CAPS_LOCK : return "CapsLock";
            case GLFW.GLFW_KEY_LEFT_SHIFT : return "LShift";
            case GLFW.GLFW_KEY_LEFT_CONTROL : return "LCtrl";
            case GLFW.GLFW_KEY_LEFT_ALT : return "LAlt";
            case GLFW.GLFW_KEY_1 : return "1";
            case GLFW.GLFW_KEY_2 : return "2";
            case GLFW.GLFW_KEY_3 : return "3";
            case GLFW.GLFW_KEY_4 : return "4";
            case GLFW.GLFW_KEY_5 : return "5";
            case GLFW.GLFW_KEY_6 : return "6";
            case GLFW.GLFW_KEY_7 : return "7";
            case GLFW.GLFW_KEY_8 : return "8";
            case GLFW.GLFW_KEY_9 : return "9";
            case GLFW.GLFW_KEY_0 : return "0";
            case GLFW.GLFW_KEY_F1 : return "F1";
            case GLFW.GLFW_KEY_F2 : return "F2";
            case GLFW.GLFW_KEY_F3 : return "F3";
            case GLFW.GLFW_KEY_F4 : return "F4";
            case GLFW.GLFW_KEY_F5 : return "F5";
            case GLFW.GLFW_KEY_F6 : return "F6";
            case GLFW.GLFW_KEY_F7 : return "F7";
            case GLFW.GLFW_KEY_F8 : return "F8";
            case GLFW.GLFW_KEY_F9 : return "F9";
            case GLFW.GLFW_KEY_F10 : return "F10";
            case GLFW.GLFW_KEY_F11 : return "F11";
            case GLFW.GLFW_KEY_F12 : return "F12";
            case GLFW.GLFW_KEY_LEFT_BRACKET : return "[";
            case GLFW.GLFW_KEY_RIGHT_BRACKET : return "]";
            case GLFW.GLFW_KEY_BACKSLASH : return "\"";
            case GLFW.GLFW_KEY_MINUS : return "-";
            case GLFW.GLFW_KEY_EQUAL : return "=";
            case GLFW.GLFW_KEY_SEMICOLON : return ";";
            case GLFW.GLFW_KEY_APOSTROPHE : return "'";
            case GLFW.GLFW_KEY_COMMA : return ",";
            case GLFW.GLFW_KEY_PERIOD : return ".";
            case GLFW.GLFW_KEY_SLASH : return "/";
            case GLFW.GLFW_KEY_RIGHT_SHIFT : return "RShift";
            case GLFW.GLFW_KEY_RIGHT_CONTROL : return "RCtrl";
            case GLFW.GLFW_KEY_RIGHT_ALT : return "RAlt";
            case GLFW.GLFW_KEY_UP : return "Up";
            case GLFW.GLFW_KEY_DOWN : return "Down";
            case GLFW.GLFW_KEY_LEFT : return "Left";
            case GLFW.GLFW_KEY_RIGHT : return "Right";
            case GLFW.GLFW_KEY_PRINT_SCREEN : return "PrntScrn";
            case GLFW.GLFW_KEY_SCROLL_LOCK : return "ScrlLock";
            case GLFW.GLFW_KEY_PAUSE : return "PauseBrk";
            case GLFW.GLFW_KEY_INSERT : return "Insrt";
            case GLFW.GLFW_KEY_HOME : return "Home";
            case GLFW.GLFW_KEY_END : return "End";
            case GLFW.GLFW_KEY_PAGE_DOWN : return "PgDown";
            case GLFW.GLFW_KEY_NUM_LOCK : return "NumLock";
            case GLFW.GLFW_KEY_KP_MULTIPLY : return "num*";
            case GLFW.GLFW_KEY_KP_7 : return "num7";
            case GLFW.GLFW_KEY_KP_8 : return "num8";
            case GLFW.GLFW_KEY_KP_9 : return "num9";
            case GLFW.GLFW_KEY_KP_ADD : return "num+";
            case GLFW.GLFW_KEY_KP_DIVIDE : return "num/";
            case GLFW.GLFW_KEY_KP_4 : return "num4";
            case GLFW.GLFW_KEY_KP_5 : return "num5";
            case GLFW.GLFW_KEY_KP_6 : return "num6";
            case GLFW.GLFW_KEY_KP_1 : return "num1";
            case GLFW.GLFW_KEY_KP_2 : return "num2";
            case GLFW.GLFW_KEY_KP_3 : return "num3";
            case GLFW.GLFW_KEY_KP_0 : return "num0";
            case GLFW.GLFW_KEY_KP_DECIMAL : return "num.";
            case GLFW.GLFW_MOUSE_BUTTON_3 : return "MB3";
            case GLFW.GLFW_MOUSE_BUTTON_4 : return "MB4";
            case GLFW.GLFW_MOUSE_BUTTON_5 : return "MB5";
            case GLFW.GLFW_MOUSE_BUTTON_6 : return "MB6";

            case GLFW.GLFW_KEY_DELETE : return "None";
            default: return "None";

        }
    }

    public static String getButtonName(int button) {
        switch (button) {
            case -1: return "Unknown";
            case 0:  return "Mouse Left";
            case 1:  return "Mouse Right";
            case 2:  return "Mouse Middle";
            default: return "Mouse " + button;
        }
    }
}