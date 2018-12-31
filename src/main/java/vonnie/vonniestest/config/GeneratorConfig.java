package vonnie.vonniestest.config;

import net.minecraftforge.common.config.Config;
import vonnie.vonniestest.vonniestest;

@Config(modid = vonniestest.MODID, category = "generator")
public class GeneratorConfig {

    @Config.Comment(value = "Maximum amount of power for the generator")
    public static int MAX_POWER = 100000;

    @Config.Comment(value = "Multiplier for Damage Generation")
    public static float POWER_DAMAGE_FACTOR = 5.0f;

    @Config.Comment(value = "Multiplier for Fishing Generation")
    public static float POWER_FISHING_FACTOR = 7.0f;

    @Config.Comment(value = "Event Tracking Box Length (Centered on Generator)[Odd]")
    @Config.RangeInt(min = 3)
    public static int TRACKING_BOX_LENGTH = 11;

    @Config.Comment(value = "Event Tracking Box Width (Centered on Generator)[Odd]")
    @Config.RangeInt(min = 3)
    public static int TRACKING_BOX_WIDTH = 11;

    @Config.Comment(value = "Event Tracking Box Height (Centered on Generator)[Odd]")
    @Config.RangeInt(min = 3)
    public static int TRACKING_BOX_HEIGHT = 7;
}