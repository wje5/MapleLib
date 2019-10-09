package com.maple.maplelib;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class GuiScreenCG extends GuiScreen {
	private static final ResourceLocation texture = new ResourceLocation("maplelib:textures/gui/cg.png");
	private float totalTicks = 0.0F;

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.totalTicks += partialTicks;
		this.mc.getTextureManager().bindTexture(texture);
		if (this.totalTicks < 10) {
			this.drawMask(1.0F, 0x000000);
			this.drawMask(1.0F - this.totalTicks * 0.1F, 0xFFFFFF);
		} else if (this.totalTicks < 60) {
			this.drawStretchableImageRect(0.0D, 0.0D, this.width, this.height, 0.0D, 0.0D, 256 * (1919.0D / 2048.0D),
					256 * (1080.0D / 2048.0D), this.zLevel);
			this.drawMask(1.0F - (this.totalTicks - 10) * 0.02F, 0x000000);
		} else if (this.totalTicks < 120) {
			this.drawStretchableImageRect(0.0D, 0.0D, this.width, this.height, 0.0D, 0.0D, 256 * (1919.0D / 2048.0D),
					256 * (1080.0D / 2048.0D), this.zLevel);
		} else if (this.totalTicks < 140) {
			this.drawStretchableImageRect(0.0D, 0.0D, this.width, this.height, 0.0D, 0.0D, 256 * (1919.0D / 2048.0D),
					256 * (1080.0D / 2048.0D), this.zLevel);
			this.drawMask((this.totalTicks - 120) * 0.05F, 0x000000);
		}
		if (this.totalTicks >= 150) {
			this.mc.displayGuiScreen(new GuiMainMenu());
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	private void drawStretchableImageRect(double x, double y, double width, double height, double u, double v,
			double uvWidth, double uvHeight, float zLevel) {
		float f = 0.00390625F;
		GL11.glPushMatrix();
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x, y + height, zLevel, u * f, (v + uvHeight) * f);
		tessellator.addVertexWithUV(x + width, y + height, zLevel, (u + uvWidth) * f, (v + uvHeight) * f);
		tessellator.addVertexWithUV(x + width, y, zLevel, (u + uvWidth) * f, v * f);
		tessellator.addVertexWithUV(x, y, zLevel, u * f, v * f);
		tessellator.draw();
		GL11.glPopMatrix();
	}

	private void drawMask(float alpha, int baseColor) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glColorMask(true, true, true, false);
		int color = (((int) (alpha * 0xFF)) << 24) + baseColor;
		this.drawGradientRect(0, 0, this.width, this.height, color, color);
		GL11.glColorMask(true, true, true, true);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glPopMatrix();
	}

	@Override
	protected void mouseClicked(int x, int y, int button) {
		super.mouseClicked(x, y, button);
		this.mc.displayGuiScreen(new GuiMainMenu());
	}
}
