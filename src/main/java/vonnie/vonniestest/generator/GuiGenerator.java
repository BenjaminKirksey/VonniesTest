package vonnie.vonniestest.generator;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import vonnie.vonniestest.config.GeneratorConfig;
import vonnie.vonniestest.vonniestest;

import java.util.Collections;

public class GuiGenerator extends GuiContainer {

    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;

    private static final ResourceLocation background = new ResourceLocation(vonniestest.MODID, "textures/gui/generator.png");
    private TileGenerator generator;

    public GuiGenerator(TileGenerator tileEntity, ContainerGenerator container) {
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;

        generator = tileEntity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int energy = generator.getClientEnergy();
        drawEnergyBar(energy);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);

        if (mouseX > guiLeft + 10 && mouseX < guiLeft + 112 && mouseY > guiTop + 5 && mouseY < guiTop + 15) {
            drawHoveringText(Collections.singletonList("Energy: " + generator.getClientEnergy()), mouseX, mouseY, fontRenderer);
        }
    }

    private void drawEnergyBar(int energy) {
        drawRect(guiLeft + 7, guiTop + 7, guiLeft + 109, guiTop + 17, 0xff555555);
        int percentage = energy * 100 / GeneratorConfig.MAX_POWER;
        for (int i = 0 ; i < percentage ; i++) {
            drawVerticalLine(guiLeft + 7 + 1 + i, guiTop + 7, guiTop + 16, i % 2 == 0 ? 0xffff0000 : 0xff000000);
        }
    }
}
