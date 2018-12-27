package vonnie.vonniestest.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vonnie.vonniestest.tools.GenericBlock;
import vonnie.vonniestest.vonniestest;

public class BlockTurquoise extends GenericBlock {
    public static final ResourceLocation TURQUOISE_BLOCK = new ResourceLocation(vonniestest.MODID, "turquoise_block");

    public BlockTurquoise(){
        super(Material.IRON);
        setRegistryName(TURQUOISE_BLOCK);
        setTranslationKey(vonniestest.MODID + ".turquoise_block");
        setHarvestLevel("pickaxe", 1);
        setHardness(4.0f);
        setResistance(7.0f);
        setSoundType(SoundType.METAL);
    }

    @SideOnly(Side.CLIENT)
    public void initModel(){
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
