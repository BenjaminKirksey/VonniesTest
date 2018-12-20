package vonnie.vonniestest.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vonnie.vonniestest.vonniestest;

public class ItemTurquoiseGem extends Item {

    public ItemTurquoiseGem() {
        setTranslationKey(vonniestest.MODID + ".turquoise_gem");
        setRegistryName(new ResourceLocation(vonniestest.MODID, "turquoise_gem"));
        setCreativeTab(vonniestest.creativeTab);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
