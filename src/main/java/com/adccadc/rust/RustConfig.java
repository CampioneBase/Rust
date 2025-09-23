package com.adccadc.rust;

import java.io.*;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RustConfig {
    private static final String CONFIG_FILE_PATH = "./config/rust.properties";

    private static final String MOD_VERSION = "mod_version";
    private static final String modversion = "1.3.0";

    private static final String USE_LEGACY_KEY = "use_legacy_oxidize_logic";
    private static final String AFFECT_REDSTONE = "affect_redstone";
    private static final String AFFECT_ENTITY = "effect_entity";

    private static final String EXPOSED_WPPB = "exposed_weighted_pressure_plate";
    private static final String WEATHERED_WPPB = "weathered_weighted_pressure_plate";
    private static final String OXIDIZED_WPPB = "oxidized_weighted_pressure_plate";
    private static final String WAXED_WPPB = "waxed_weighted_pressure_plate";
    private static final String WAXED_EXPOSED_WPPB = "waxed_exposed_weighted_pressure_plate";
    private static final String WAXED_WEATHERED_WPPB = "waxed_weathered_weighted_pressure_plate";
    private static final String WAXED_OXIDIZED_WPPB = "waxed_oxidized_weighted_pressure_plate";

    private static final String EXPOSED_IG = "exposed_iron_golem";
    private static final String WEATHERED_IG = "weathered_iron_golem";
    private static final String OXIDIZED_IG = "oxidized_iron_golem";
    private static final String WAXED_IG = "waxed_iron_golem";
    private static final String WAXED_EXPOSED_IG = "waxed_exposed_iron_golem";
    private static final String WAXED_WEATHERED_IG = "waxed_weathered_iron_golem";
    private static final String WAXED_OXIDIZED_IG = "waxed_oxidized_iron_golem";

    private static boolean useLegacyLogic = false; // 是否采用旧版氧化逻辑

    private static boolean affectRedstone = true; // 是否影响红石
    // 各种测重压力板每一级信号所需实体重量
    private static Integer exposed_WPPB = 170;
    private static Integer weathered_WPPB = 190;
    private static Integer oxidized_WPPB = 210;
    private static Integer waxed_WPPB = 160;
    private static Integer waxed_exposed_WPPB = 180;
    private static Integer waxed_weathered_WPPB = 200;
    private static Integer waxed_oxidized_WPPB = 220;

    private static boolean affectEntity = true; // 是否影响实体
    // 各种铁傀儡的生物属性
    private static final List<Float> default_IG = new ArrayList<Float>(Arrays.asList(0.25F, 15.0F)); //原版铁傀儡属性(移动速度，攻击伤害)
    private static List<Float> exposed_IG = new ArrayList<Float>(Arrays.asList(0.22F, 14.0F));
    private static List<Float> weathered_IG = new ArrayList<Float>(Arrays.asList(0.19F, 13.0F));
    private static List<Float> oxidized_IG = new ArrayList<Float>(Arrays.asList(0.16F, 12.0F));
    private static List<Float> waxed_IG = new ArrayList<Float>(Arrays.asList(0.23F, 14.5F));
    private static List<Float> waxed_exposed_IG = new ArrayList<Float>(Arrays.asList(0.20F, 13.5F));
    private static List<Float> waxed_weathered_IG = new ArrayList<Float>(Arrays.asList(0.17F, 12.5F));
    private static List<Float> waxed_oxidized_IG = new ArrayList<Float>(Arrays.asList(0.14F, 11.5F));

    private RustConfig() {}

    protected static List<Float> convertWithRegex(String input) {
        List<Float> result = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\d+(?:\\.\\d+)?(?:F|f)?");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String match = matcher.group();

            if (match.toUpperCase().endsWith("F")) {
                match = match.substring(0, match.length() - 1);
            }
            result.add(Float.parseFloat(match));
        }
        return result;
    }

    protected static String formatWithF(List<Float> list) {
        return list.stream()
                .map(obj -> {
                    if (obj instanceof Number) {
                        return obj.toString() + "F";
                    }
                    return obj.toString();
                })
                .collect(Collectors.joining(",", "[", "]"));
    }

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
            affectRedstone = Boolean.parseBoolean(props.getProperty(AFFECT_REDSTONE, "true").trim());
            affectEntity = Boolean.parseBoolean(props.getProperty(AFFECT_ENTITY, "true"));
            exposed_WPPB = Integer.parseUnsignedInt(props.getProperty(EXPOSED_WPPB, "150").trim());
            weathered_WPPB = Integer.parseUnsignedInt(props.getProperty(WEATHERED_WPPB, "150").trim());
            oxidized_WPPB = Integer.parseUnsignedInt(props.getProperty(OXIDIZED_WPPB, "150").trim());
            waxed_WPPB = Integer.parseUnsignedInt(props.getProperty(WAXED_WPPB, "150").trim());
            waxed_exposed_WPPB = Integer.parseUnsignedInt(props.getProperty(WAXED_EXPOSED_WPPB, "150").trim());
            waxed_weathered_WPPB = Integer.parseUnsignedInt(props.getProperty(WAXED_WEATHERED_WPPB, "150").trim());
            waxed_oxidized_WPPB = Integer.parseUnsignedInt(props.getProperty(WAXED_OXIDIZED_WPPB, "150").trim());
            exposed_IG = convertWithRegex(props.getProperty(EXPOSED_IG, "[0.22F,14.0F]").trim());
            weathered_IG = convertWithRegex(props.getProperty(WEATHERED_IG, "[0.19F,13.0F]").trim());
            oxidized_IG = convertWithRegex(props.getProperty(OXIDIZED_IG, "[0.16F,12.0F]").trim());
            waxed_IG = convertWithRegex(props.getProperty(WAXED_IG, "[0.23F,14.5F]"));
            waxed_exposed_IG = convertWithRegex(props.getProperty(WAXED_EXPOSED_IG, "[0.20F,13.5F]").trim());
            waxed_weathered_IG = convertWithRegex(props.getProperty(WAXED_WEATHERED_IG, "[0.17F,12.5F]").trim());
            waxed_oxidized_IG = convertWithRegex(props.getProperty(WAXED_OXIDIZED_IG, "[0.14F,11.5F]").trim());
            Rust.LOGGER.info("Loaded rust config: use_legacy_logic = " + useLegacyLogic);
            Rust.LOGGER.info("Loaded rust config: affect_redstone = " + affectRedstone);
            Rust.LOGGER.info("Loaded rust config: affect_entity = " + affectEntity);
            // 若读取的为非本版本配置文件 复制已改配置并生成新配置
            if (!Objects.equals(props.getProperty(MOD_VERSION), modversion)) {
                Rust.LOGGER.warn("Already loaded into the old version config file, updating to the new version config");
                createDefaultConfig(configFile);
            }
        } catch (IOException e) {
            Rust.LOGGER.error("Failed to load config file, using default settings: " + e.getMessage());
            createDefaultConfig(configFile);
        }
    }

    private static void createDefaultConfig(File configFile) {
        File configDir = configFile.getParentFile();
        if (!configDir.exists() && !configDir.mkdirs()) {
            Rust.LOGGER.error("Failed to create config directory: " + configDir.getAbsolutePath());
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(configFile))) {

            writer.println("#  Rust Mod Oxidation Configuration");
            writer.println("#  Please do not modify this item, otherwise the config file will be regenerated");
            writer.println(MOD_VERSION + "= 1.3.0");
            writer.println();
            writer.println("#  - true: Use old mod version oxidation logic");
            writer.println("#  - false: Use the original oxidation logic");
            writer.println("#  if using the old logic, there is a probability of oxidizing the blocks around the player every 5 minutes");
            writer.println(USE_LEGACY_KEY + "= " + useLegacyLogic);
            writer.println();
            writer.println("#  - true: Rust can affect redstone");
            writer.println("#  - false: Rust can't affect redstone");
            writer.println("#  if the value is false, rust will not use subsequent config, use original block state and logic");
            writer.println(AFFECT_REDSTONE + "= " + affectRedstone);
            writer.println();
            writer.println("#  - number(int)");
            writer.println("#  How much weight is required for each level of signal (original version is 150)");
            writer.println(EXPOSED_WPPB + "= " + exposed_WPPB);
            writer.println(WEATHERED_WPPB + "= " + weathered_WPPB);
            writer.println(OXIDIZED_WPPB + "= " + oxidized_WPPB);
            writer.println(WAXED_WPPB + "= " + waxed_WPPB);
            writer.println(WAXED_EXPOSED_WPPB + "= " + waxed_exposed_WPPB);
            writer.println(WAXED_WEATHERED_WPPB + "= " + waxed_weathered_WPPB);
            writer.println(WAXED_OXIDIZED_WPPB + "= " + waxed_oxidized_WPPB);
            writer.println();
            writer.println("#  - true: Rust can affect entity");
            writer.println("#  - false: Rust can't affect entity");
            writer.println("#  if the value is false, rust will not use subsequent config, use original entity attributes");
            writer.println(AFFECT_ENTITY + "= " + affectEntity);
            writer.println();
            writer.println("#  - [decimal(float),decimal(float)]");
            writer.println("#  - [movement speed,attack damage] *Please add F at the end of each float value");
            writer.println("#  This will change the attributes of the golem, some of which may not be effective for existing golem");
            writer.println(EXPOSED_IG + "= " + formatWithF(exposed_IG));
            writer.println(WEATHERED_IG + "= " + formatWithF(weathered_IG));
            writer.println(OXIDIZED_IG + "= " + formatWithF(oxidized_IG));
            writer.println(WAXED_IG + "= " + formatWithF(waxed_IG));
            writer.println(WAXED_EXPOSED_IG + "= " + formatWithF(waxed_exposed_IG));
            writer.println(WAXED_WEATHERED_IG + "= " + formatWithF(waxed_weathered_IG));
            writer.println(WAXED_OXIDIZED_IG + "= " + formatWithF(waxed_oxidized_IG));

            Rust.LOGGER.info("Created new config file: " + configFile.getAbsolutePath());
            Rust.LOGGER.info("Please edit the file and restart Minecraft to apply changes.");
        } catch (IOException e) {
            Rust.LOGGER.error("Failed to create config file: " + e.getMessage());
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

    public static List<Float> getExposed_IG() {return affectEntity ? exposed_IG : default_IG;}
    public static List<Float> getWeathered_IG() {return affectEntity ? weathered_IG : default_IG;}
    public static List<Float> getOxidized_IG() {return affectEntity ? oxidized_IG : default_IG;}
    public static List<Float> getWaxed_IG() {return affectEntity ? waxed_IG : default_IG;}
    public static List<Float> getWaxed_exposed_IG() {return affectEntity ? waxed_exposed_IG : default_IG;}
    public static List<Float> getWaxed_weathered_IG() {return affectEntity ? waxed_weathered_IG : default_IG;}
    public static List<Float> getWaxed_oxidized_IG() {return affectEntity ? waxed_oxidized_IG : default_IG;}
}
