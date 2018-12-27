package vonnie.vonniestest;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import vonnie.vonniestest.blocks.BlockTurquoise;
import vonnie.vonniestest.furnace.BlockFastFurnace;
import vonnie.vonniestest.generator.BlockGenerator;
import vonnie.vonniestest.items.ItemTurquoiseGem;
import vonnie.vonniestest.worldgen.BlockTurquoiseOre;

public class ModItems {

    @GameRegistry.ObjectHolder("vonniestest:turquoise_gem")
    public static ItemTurquoiseGem itemTurquoiseGem;

    @SideOnly(Side.CLIENT)
    public static void initModels() { itemTurquoiseGem.initModel(); }

    public static void register(IForgeRegistry<Item> registry) {
        registry.register(new ItemBlock(ModBlocks.blockFastFurnace).setRegistryName(BlockFastFurnace.FAST_FURNACE));
        registry.register(new ItemBlock(ModBlocks.blockGenerator).setRegistryName(BlockGenerator.GENERATOR));
        registry.register(new ItemBlock(ModBlocks.blockTurquoise).setRegistryName(BlockTurquoise.TURQUOISE_BLOCK));
        registry.register(new ItemBlock(ModBlocks.blockTurquoiseOre) {
            @Override
            public int getMetadata(int damage) {
                return damage;
            }
        }
                .setHasSubtypes(true)
                .setRegistryName(BlockTurquoiseOre.TURQUOISE_ORE));
        registry.register(new ItemTurquoiseGem());
    }
}
