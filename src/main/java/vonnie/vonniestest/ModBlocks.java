package vonnie.vonniestest;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import vonnie.vonniestest.blocks.BlockTurquoise;
import vonnie.vonniestest.furnace.BlockFastFurnace;
import vonnie.vonniestest.furnace.TileFastFurnace;
import vonnie.vonniestest.generator.BlockGenerator;
import vonnie.vonniestest.generator.TileGenerator;
import vonnie.vonniestest.puzzle.BlockPuzzle;
import vonnie.vonniestest.puzzle.TilePuzzle;
import vonnie.vonniestest.worldgen.BlockTurquoiseOre;

public class ModBlocks {

    @GameRegistry.ObjectHolder("vonniestest:fast_furnace")
    public static BlockFastFurnace blockFastFurnace;

    @GameRegistry.ObjectHolder("vonniestest:generator")
    public static BlockGenerator blockGenerator;

    @GameRegistry.ObjectHolder("vonniestest:puzzle")
    public static BlockPuzzle blockPuzzle;

    @GameRegistry.ObjectHolder("vonniestest:turquoise_ore")
    public static BlockTurquoiseOre blockTurquoiseOre;

    @GameRegistry.ObjectHolder("vonniestest:turquoise_block")
    public static BlockTurquoise blockTurquoise;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        blockFastFurnace.initModel();
        blockGenerator.initModel();
        blockTurquoiseOre.initModel();
        blockTurquoise.initModel();
        blockPuzzle.initModel();
    }

    public static void register(IForgeRegistry<Block> registry) {
        registry.register(new BlockFastFurnace());
        GameRegistry.registerTileEntity(TileFastFurnace.class, vonniestest.MODID + "_fast_furnace");

        registry.register(new BlockGenerator());
        GameRegistry.registerTileEntity(TileGenerator.class, vonniestest.MODID + "_generator");

        registry.register(new BlockPuzzle());
        GameRegistry.registerTileEntity(TilePuzzle.class, vonniestest.MODID + "_puzzle");

        registry.register(new BlockTurquoiseOre());

        registry.register(new BlockTurquoise());
    }
}