package com.adccadc.rust;

import java.io.*;
import java.util.Properties;

public class RustConfig {
    private static final String CONFIG_FILE_PATH = "./config/rust.properties";
    private static final String USE_LEGACY_KEY = "use_legacy_oxidize_logic";
    private static boolean useLegacyLogic = false;

    private RustConfig() {}

    public static void loadConfig() {
        File configFile = new File(CONFIG_FILE_PATH);

        if (!configFile.exists()) {
            createDefaultConfig(configFile);
            return;
        }

        Properties props = new Properties();
        try (InputStream input = new FileInputStream(configFile)) {
            props.load(input);
            useLegacyLogic = Boolean.parseBoolean(props.getProperty(USE_LEGACY_KEY, "false"));
            System.out.println("Loaded oxidize config: use_legacy_logic = " + useLegacyLogic);
        } catch (IOException e) {
            System.err.println("Failed to load config file, using default settings: " + e.getMessage());
            createDefaultConfig(configFile);
        }
    }

    private static void createDefaultConfig(File configFile) {
        File configDir = configFile.getParentFile();
        if (!configDir.exists() && !configDir.mkdirs()) {
            System.err.println("Failed to create config directory: " + configDir.getAbsolutePath());
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(configFile))) {

            writer.println("# Rust Mod Oxidation Configuration");
            writer.println("#");
            writer.println("#  - true: Use old mod version oxidation logic");
            writer.println("#  - false: Use the original oxidation logic");
            writer.println("#  If using the old logic, there is a probability of oxidizing the blocks around the player every 5 minutes");
            writer.println(USE_LEGACY_KEY + "=false");

            System.out.println("Created default config file: " + configFile.getAbsolutePath());
            System.out.println("Please edit the file and restart Minecraft to apply changes.");
        } catch (IOException e) {
            System.err.println("Failed to create config file: " + e.getMessage());
        }
    }

    public static boolean useLegacyOxidizeLogic() {
        return useLegacyLogic;
    }
}
