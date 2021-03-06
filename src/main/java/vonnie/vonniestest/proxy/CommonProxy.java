package vonnie.vonniestest.proxy;

import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.ListenableFuture;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.animation.ITimeValue;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import vonnie.vonniestest.ModBlocks;
import vonnie.vonniestest.ModEntities;
import vonnie.vonniestest.ModItems;
import vonnie.vonniestest.generator.DamageTracker;
//import vonnie.vonniestest.generator.FishingTracker;
import vonnie.vonniestest.network.Messages;
import vonnie.vonniestest.vonniestest;
import vonnie.vonniestest.worldgen.OreGenerator;
import vonnie.vonniestest.worldgen.WorldTickHandler;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        Messages.registerMessages("vonniestest");
        GameRegistry.registerWorldGenerator(OreGenerator.instance, 5);
        MinecraftForge.EVENT_BUS.register(OreGenerator.instance);

        ModEntities.init();
    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(vonniestest.instance, new GuiHandler());
        MinecraftForge.EVENT_BUS.register(WorldTickHandler.instance);
        //MinecraftForge.EVENT_BUS.register(FishingTracker.instance);
        MinecraftForge.EVENT_BUS.register(DamageTracker.instance);
    }

    public void postInit(FMLPostInitializationEvent e) {
        GameRegistry.addSmelting(ModBlocks.blockTurquoiseOre, new ItemStack(ModItems.itemTurquoiseGem, 1), 0.5f);
        OreDictionary.registerOre("oreTurquoise", ModBlocks.blockTurquoiseOre);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        ModBlocks.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        ModItems.register(event.getRegistry());
    }

    @Nullable
    public IAnimationStateMachine load(ResourceLocation location, ImmutableMap<String, ITimeValue> parameters) {
        return null;
    }

    public ListenableFuture<Object> addScheduledTaskClient (Runnable runnableToSchedule) {
        throw new IllegalStateException("This should only be called from client side");
    }

    public EntityPlayer getClientPlayer() {
        throw new IllegalStateException("This should only be called from client side");
    }
}