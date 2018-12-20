package vonnie.vonniestest;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vonnie.vonniestest.items.ItemTurquoiseGem;

public class ModItems {

    @GameRegistry.ObjectHolder("vonniestest:turquoise_gem")
    public static ItemTurquoiseGem itemTurquoiseGem;

    @SideOnly(Side.CLIENT)
    public static void initModels() { itemTurquoiseGem.initModel(); }
}
