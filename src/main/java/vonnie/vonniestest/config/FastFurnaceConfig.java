package vonnie.vonniestest.config;

import net.minecraftforge.common.config.Config;
import vonnie.vonniestest.vonniestest;

@Config(modid = vonniestest.MODID, category = "fastfurnace")
public class FastFurnaceConfig {

    @Config.Comment(value = "Number of ticks for one smelt in the fast furnace")
    @Config.RangeInt(min = 1)
    public static int MAX_PROGRESS = 40;

    @Config.Comment(value = "How much rf the furnace can store")

    public static int MAX_POWER = 100000;

    @Config.Comment(value = "Fast furnace rf usage per tick")
    @Config.RangeInt(min = 1)
    public static int RF_PER_TICK = 20;

    @Config.Comment(value = "How fast the fast furnace can charge in rf/t")
    @Config.RangeInt(min = 1)
    public static int RF_PER_TICK_INPUT = 100;
}