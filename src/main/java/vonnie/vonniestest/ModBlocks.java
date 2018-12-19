package vonnie.vonniestest;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vonnie.vonniestest.furnace.BlockFastFurnace;
import vonnie.vonniestest.worldgen.BlockTurquoiseOre;

public class ModBlocks {

    @GameRegistry.ObjectHolder("vonniestest:fast_furnace")
    public static BlockFastFurnace blockFastFurnace;

    @GameRegistry.ObjectHolder("vonniestest:turquoise_ore")
    public static BlockTurquoiseOre blockTurquoiseOre;


    @SideOnly(Side.CLIENT)
    public static void initModels() {
        blockFastFurnace.initModel();
        blockTurquoiseOre.initModel();
    }
}