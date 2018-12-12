package vonnie.vonniestest.furnace;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import vonnie.vonniestest.vonniestest;

import java.util.Collections;

public class GuiFastFurnace extends GuiContainer {
    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;

    private static final ResourceLocation background = new ResourceLocation(vonniestest.MODID, "textures/gui/fast_furnace.png");
    private TileFastFurnace furnace;

    public GuiFastFurnace(TileFastFurnace tileEntity, ContainerFastFurnace container) {
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;

        furnace = tileEntity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int energy = furnace.getClientEnergy();
        drawEnergyBar(energy);

        if (furnace.getClientProgress() > 0) {
            int percentage = 100 - furnace.getClientProgress() * 100 / TileFastFurnace.MAX_PROGRESS;
            drawString(mc.fontRenderer, "Progress: " + percentage + "%", guiLeft + 10, guiTop + 50, 0xffffff);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX,mouseY);

        if (mouseX >= guiLeft + 7 && mouseX < guiLeft + 109 && mouseY >= guiTop + 7 && mouseY < guiTop + 17) {
            drawHoveringText(Collections.singletonList("Energy: " + furnace.getClientEnergy()), mouseX, mouseY, fontRenderer);
        }
    }

    private void drawEnergyBar(int energy) {
        drawRect(guiLeft + 7, guiTop + 7, guiLeft + 109, guiTop + 17, 0xff555555);
        int percentage = energy * 100 / TileFastFurnace.MAX_POWER;
        for (int i = 0 ; i < percentage ; i++) {
            drawVerticalLine(guiLeft + 7 + 1 + i, guiTop + 7, guiTop + 16, i % 2 == 0 ? 0xffff0000 : 0xff000000);
       }
    }
}
