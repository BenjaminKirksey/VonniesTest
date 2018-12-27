package vonnie.vonniestest.jei;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;
import vonnie.vonniestest.ModBlocks;
import vonnie.vonniestest.furnace.ContainerFastFurnace;
import vonnie.vonniestest.furnace.GuiFastFurnace;

@JEIPlugin
public class JeiPlugin implements IModPlugin {

    @Override
    public void register(IModRegistry registry) {
        IJeiHelpers helpers = registry.getJeiHelpers();
        IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();
        registry.addRecipeClickArea(GuiFastFurnace.class, 75,25,25,25,VanillaRecipeCategoryUid.SMELTING);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.blockFastFurnace), VanillaRecipeCategoryUid.SMELTING);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerFastFurnace.class, VanillaRecipeCategoryUid.SMELTING, 0,3,9,36);
    }

}
