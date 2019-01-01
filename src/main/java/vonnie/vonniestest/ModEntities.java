package vonnie.vonniestest;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vonnie.vonniestest.guard.EntityGuard;
import vonnie.vonniestest.guard.RenderGuard;

public class ModEntities {
    public static void init() {
        int id = 1;

        EntityRegistry.registerModEntity(new ResourceLocation(vonniestest.MODID, "vonniestest_guard"), EntityGuard.class, "vonniestest_guard", id++,
                vonniestest.instance, 64, 3, true, 0x222222, 0x555555);
    }
    @SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityGuard.class, RenderGuard.FACTORY);
    }
}
