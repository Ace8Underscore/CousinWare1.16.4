package io.ace.nordclient.utilz.configz;

import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.managers.FriendManager;
import io.ace.nordclient.managers.HackManager;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingMode;
import io.ace.nordclient.settings.SettingSlider;
import io.ace.nordclient.settings.SettingToggle;
import io.ace.nordclient.utilz.FriendUtil;
import net.minecraft.client.Minecraft;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FileMang {
    private static Path dir;

    public static void init() {
        dir = Paths.get(Minecraft.getInstance().gameDir.getPath(), "CousinWare", "1.16/");

        if (!dir.toFile().exists()) {
            dir.toFile().mkdirs();
        }
    }

    public static Path getDir() {
        return dir;
    }

    public static List<String> readFileLines(String... file) {
        try {
            return Files.readAllLines(stringsToPath(file));
        } catch (IOException e) {
            System.out.println("Error Reading File: " + stringsToPath(file));
            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    public static void createFile(String... file) {
        try {
            if (fileExists(file)) {
                return;
            }

            dir.toFile().mkdirs();
            Files.createFile(stringsToPath(file));
        } catch (IOException e) {
            System.out.println("Error Creating File: " + Arrays.toString(file));
            e.printStackTrace();
        }
    }

    public static void createEmptyFile(String... file) {
        try {
            dir.toFile().mkdirs();
            if (!fileExists(file)) {
                Files.createFile(stringsToPath(file));
            }

            FileWriter writer = new FileWriter(stringsToPath(file).toFile());
            writer.write("");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error Clearing/Creating File: " + Arrays.toString(file));
            e.printStackTrace();
        }

    }

    public static void appendFile(String content, String... file) {
        try {
            FileWriter writer = new FileWriter(stringsToPath(file).toFile(), true);
            writer.write(content + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error Appending File: " + Arrays.toString(file));
            e.printStackTrace();
        }
    }

    public static boolean fileExists(String... file) {
        try {
            return stringsToPath(file).toFile().exists();
        } catch (Exception e) {
            return false;
        }
    }

    public static void deleteFile(String... file) {
        try {
            Files.deleteIfExists(stringsToPath(file));
        } catch (Exception e) {
            System.out.println("Error Deleting File: " + Arrays.toString(file));

            e.printStackTrace();
        }
    }

    public static Path stringsToPath(String... strings) {
        Path path = dir;

        for (String s : strings) {
            path = path.resolve(s);
        }

        return path;
    }

    public static void saveSettings() {
        createEmptyFile("Settings.txt");
        String lines = "";

        StringBuilder line;

        for (Iterator iter = HackManager.getHacks().iterator(); iter.hasNext(); lines = lines + line + "\n") {
            Hack h = (Hack) iter.next();
            line = new StringBuilder(h.getName());
            int count = 0;

            for (Iterator settingsIter = h.getSettings().iterator(); settingsIter.hasNext(); ++count) {
                SettingBase set = (SettingBase) settingsIter.next();
                if (set instanceof SettingSlider) {
                    line.append(":").append(h.getSettings().get(count).toSlider().getValue());
                }

                if (set instanceof SettingMode) {
                    line.append(":").append(h.getSettings().get(count).toMode().mode);
                }

                if (set instanceof SettingToggle) {
                    line.append(":").append(h.getSettings().get(count).toToggle().state);
                }
            }
        }

        appendFile(lines, "Settings.txt");
    }

    public static void readSettings() {
        List<String> lines = readFileLines("Settings.txt");
        Iterator hacksIter = HackManager.getHacks().iterator();

        fern:
        while (hacksIter.hasNext()) {
            Hack h = (Hack) hacksIter.next();
            Iterator linesIter = lines.iterator();

            while (true) {
                String[] line;

                do {
                    if (!linesIter.hasNext()) {
                        continue fern;
                    }

                    String s = (String) linesIter.next();
                    line = s.split(":");
                } while (!line[0].startsWith(h.getName()));

                int count = 0;

                for (Iterator anotherIter = h.getSettings().iterator(); anotherIter.hasNext(); ++count) {
                    SettingBase set = (SettingBase) anotherIter.next();

                    try {
                        if (set instanceof SettingSlider) {
                            h.getSettings().get(count).toSlider().value = Double.parseDouble(line[count + 1]);
                        }

                        if (set instanceof SettingMode) {
                            h.getSettings().get(count).toMode().mode = Integer.parseInt(line[count + 1]);
                        }

                        if (set instanceof SettingToggle) {
                            h.getSettings().get(count).toToggle().state = Boolean.parseBoolean(line[count + 1]);
                        }
                    } catch (Exception ignored) {
                    }
                }
            }
        }
    }

    public static void saveModules() {
        createEmptyFile("Hacks.txt");
        StringBuilder lines = new StringBuilder();

        for (Hack h : HackManager.getHacks()) {
            if (!h.getName().equalsIgnoreCase("ClickGui") && !h.getName().equalsIgnoreCase("fakeplayer")) {
                lines.append(h.getName()).append(":").append(h.isEnabled()).append("\n");
            }
        }

        appendFile(lines.toString(), "Hacks.txt");
    }

    public static void readModules() {
        List<String> lines = readFileLines("Hacks.txt");

        for (Hack h : HackManager.getHacks()) {
            for (String s : lines) {
                String[] line = s.split(":");

                try {
                    if (line[0].contains(h.getName()) && line[1].contains("true")) {
                        h.toggle();
                        break;
                    }
                } catch (Exception ignored) {
                }
            }
        }
    }

    public static void saveBinds() {
        createEmptyFile("Binds.txt");
        String lines = "";

        Hack m;

        for (Iterator iter = HackManager.getHacks().iterator(); iter.hasNext(); lines = lines + m.getName() + ":" + m.getBind() + "\n") {
            m = (Hack) iter.next();
        }

        appendFile(lines, "Binds.txt");
    }

    public static void readBinds() {
        List<String> lines = readFileLines("Binds.txt");

        for (Hack m : HackManager.getHacks()) {
            for (String s : lines) {
                String[] line = s.split(":");
                if (line[0].startsWith(m.getName())) {
                    try {
                        m.setBind(Integer.parseInt(line[line.length - 1]));
                    } catch (Exception ignored) {
                    }
                }
            }
        }
    }

    public static void saveFriends() {
        createEmptyFile("Friends.txt");
        StringBuilder lines = new StringBuilder();

        for (FriendUtil f : FriendManager.getFriends()) {

            lines.append(f.getName()).append("\n");
        }
        appendFile(lines.toString(), "Friends.txt");
    }

    public static void readFriends() {
        List<String> lines = readFileLines("Friends.txt");
            for (String s : lines) {
                try {
                    FriendManager.addFriend(s);
                } catch (Exception ignored) {

            }
        }
    }

    public static void saveDrawn() {
        createEmptyFile("Drawn.txt");
        StringBuilder lines = new StringBuilder();

        for (Hack h : HackManager.getHacks()) {

            lines.append(h.getName()).append(":").append(h.isDrawn()).append("\n");
        }
        appendFile(lines.toString(), "Drawn.txt");
    }

    public static void readDrawn() {
        List<String> lines = readFileLines("Drawn.txt");

        for (Hack h : HackManager.getHacks()) {
            for (String s : lines) {
                String[] line = s.split(":");

                try {
                    if (line[0].contains(h.getName()) && line[1].contains("false")) {
                        h.drawn = false;
                        break;
                    }
                } catch (Exception ignored) {
                }
            }
        }
    }

}