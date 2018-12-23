package vonnie.vonniestest.config;

import net.minecraftforge.common.config.Config;
import vonnie.vonniestest.vonniestest;

@Config(modid = vonniestest.MODID, category = "generator")
public class GeneratorConfig {

    @Config.Comment(value = "Maximum amount of power for the generator")
    public static int MAX_POWER = 100000;
}