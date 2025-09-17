package com.adccadc.rust;

import java.io.*;
import java.util.Properties;

public class RustConfig {
    private static final String CONFIG_FILE_PATH = "./config/rust.properties";

    private static final String USE_LEGACY_KEY = "use_legacy_oxidize_logic";
    private static final String AFFECT_REDSTONE = "affect_redstone";

    private static final String EXPOSED_WPPB = "exposed_weighted_pressure_plate";
    private static final String WEATHERED_WPPB = "weathered_weighted_pressure_plate";
    private static final String OXIDIZED_WPPB = "oxidized_weighted_pressure_plate";
    private static final String WAXED_WPPB = "waxed_weighted_pressure_plate";
    private static final String WAXED_EXPOSED_WPPB = "waxed_exposed_weighted_pressure_plate";
    private static final String WAXED_WEATHERED_WPPB = "waxed_weathered_weighted_pressure_plate";
    private static final String WAXED_OXIDIZED_WPPB = "waxed_oxidized_weighted_pressure_plate";

    private static boolean useLegacyLogic = false; // 是否采用旧版氧化逻辑
    private static boolean affectRedstone = true; // 是否影响红石
    private static Integer exposed_WPPB = 170; // 斑驳的测重压力板每增加一格信号所需实体量
    private static Integer weathered_WPPB = 190; // 锈蚀的测重压力板每增加一格信号所需实体量
    private static Integer oxidized_WPPB = 210; // 氧化的测重压力板每增加一格信号所需实体量
    private static Integer waxed_WPPB = 160; // 涂蜡的测重压力板每增加一格信号所需实体量
    private static Integer waxed_exposed_WPPB = 180; // 涂蜡的斑驳测重压力板每增加一格信号所需实体量
    private static Integer waxed_weathered_WPPB = 200; // 涂蜡的锈蚀测重压力板每增加一格信号所需实体量
    private static Integer waxed_oxidized_WPPB = 220; // 涂蜡的氧化测重压力板每增加一格信号所需实体量

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
            useLegacyLogic = Boolean.parseBoolean(props.getProperty(USE_LEGACY_KEY, "false").trim());
            affectRedstone = Boolean.parseBoolean(props.getProperty(AFFECT_REDSTONE, "false").trim());
            exposed_WPPB = Integer.parseUnsignedInt(props.getProperty(EXPOSED_WPPB, "150").trim());
            weathered_WPPB = Integer.parseUnsignedInt(props.getProperty(WEATHERED_WPPB, "150").trim());
            oxidized_WPPB = Integer.parseUnsignedInt(props.getProperty(OXIDIZED_WPPB, "150").trim());
            waxed_WPPB = Integer.parseUnsignedInt(props.getProperty(WAXED_WPPB, "150").trim());
            waxed_exposed_WPPB = Integer.parseUnsignedInt(props.getProperty(WAXED_EXPOSED_WPPB, "150").trim());
            waxed_weathered_WPPB = Integer.parseUnsignedInt(props.getProperty(WAXED_WEATHERED_WPPB, "150").trim());
            waxed_oxidized_WPPB = Integer.parseUnsignedInt(props.getProperty(WAXED_OXIDIZED_WPPB, "150").trim());
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
            writer.println();
            writer.println("#  - true: Use old mod version oxidation logic");
            writer.println("#  - false: Use the original oxidation logic");
            writer.println("#  if using the old logic, there is a probability of oxidizing the blocks around the player every 5 minutes");
            writer.println(USE_LEGACY_KEY + "= false");
            writer.println();
            writer.println("#  - true: Rust can affect redstone");
            writer.println("#  - false: Rust can't affect redstone");
            writer.println("#  if the value is false, rust will not use subsequent config, use original block state and logic");
            writer.println(AFFECT_REDSTONE + "= true");
            writer.println();
            writer.println("#  - number");
            writer.println("#  How much weight is required for each level of signal (original version is 150)");
            writer.println(EXPOSED_WPPB + "= 170");
            writer.println(WEATHERED_WPPB + "= 190");
            writer.println(OXIDIZED_WPPB + "= 210");
            writer.println(WAXED_WPPB + "= 160");
            writer.println(WAXED_EXPOSED_WPPB + "= 180");
            writer.println(WAXED_WEATHERED_WPPB + "= 200");
            writer.println(WAXED_OXIDIZED_WPPB + "= 220");

            System.out.println("Created default config file: " + configFile.getAbsolutePath());
            System.out.println("Please edit the file and restart Minecraft to apply changes.");
        } catch (IOException e) {
            System.err.println("Failed to create config file: " + e.getMessage());
        }
    }

    public static boolean useLegacyOxidizeLogic() {return useLegacyLogic;}
    public static boolean isAffectRedstone() {return affectRedstone;}

    public static Integer getExposed_WPPB() {return affectRedstone ? exposed_WPPB : 150;}
    public static Integer getWeathered_WPPB() {return affectRedstone ? weathered_WPPB : 150;}
    public static Integer getOxidized_WPPB() {return affectRedstone ? oxidized_WPPB : 150;}
    public static Integer getWaxed_WPPB() {return affectRedstone ? waxed_WPPB : 150;}
    public static Integer getWaxed_exposed_WPPB() {return affectRedstone ? waxed_exposed_WPPB : 150;}
    public static Integer getWaxed_weathered_WPPB() {return affectRedstone ? waxed_weathered_WPPB : 150;}
    public static Integer getWaxed_oxidized_WPPB() {return affectRedstone ? waxed_oxidized_WPPB : 150;}
}
