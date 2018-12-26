package vonnie.vonniestest.jei;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import vonnie.vonniestest.ModBlocks;
import vonnie.vonniestest.furnace.BlockFastFurnace;
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
        IIngredientBlacklist ingredientBlacklist = registry.getJeiHelpers().getIngredientBlacklist();
        // Game freezes when loading player skulls, see https://bugs.mojang.com/browse/MC-65587
        ingredientBlacklist.addIngredientToBlacklist(new ItemStack(Items.SKULL, 1, 3));
        // hide enchanted books, we display them as special ingredients because vanilla does not properly store the enchantment data by its registry name
        ingredientBlacklist.addIngredientToBlacklist(new ItemStack(Items.ENCHANTED_BOOK, 1, OreDictionary.WILDCARD_VALUE));
    }

}
