package vonnie.vonniestest;



import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import vonnie.vonniestest.proxy.CommonProxy;

@Mod(modid = vonniestest.MODID, name = vonniestest.MODNAME, version = vonniestest.MODVERSION, dependencies = "required-after:forge@[11.16.0.1865,)", useMetadata = true)
public class vonniestest {

    public static final String MODID = "vonniestest";
    public static final String MODNAME = "My Test Mod";
    public static final String MODVERSION= "0.0.2";

    @SidedProxy(clientSide = "vonnie.vonniestest.proxy.ClientProxy", serverSide = "vonnie.vonniestest.proxy.ServerProxy")
    public static CommonProxy proxy;

    public static CreativeTabs creativeTab = new CreativeTabs("vonniestest") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModBlocks.blockFastFurnace);
        }
    };

    @Mod.Instance
    public static vonniestest instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}