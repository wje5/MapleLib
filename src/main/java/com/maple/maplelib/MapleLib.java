package com.maple.maplelib;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "maplelib", name = "Maple Lib", version = "@version@", acceptedMinecraftVersions = "1.7.10")
public class MapleLib {
	public static final String MODID = "maplelib";
	public static final String NAME = "Maple Lib";
	public static final String VERSION = "1.0.0";

	public static boolean cgplayed = false;

	@Instance("maplelib")
	public static MapleLib instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			MinecraftForge.EVENT_BUS.register(this);
			FMLCommonHandler.instance().bus().register(this);
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onGuiOpen(GuiOpenEvent event) {
		if (event.gui instanceof GuiMainMenu && !this.cgplayed) {
			this.cgplayed = true;
			event.gui = new GuiScreenCG();
		}
	}

	@SubscribeEvent
	public void onPlayerLoggin(PlayerLoggedInEvent event) {
		IChatComponent msg = new ChatComponentTranslation("chat.playerJoinWorld", new Object[0]);
		ChatStyle style = new ChatStyle().setBold(Boolean.valueOf(true)).setUnderlined(Boolean.valueOf(true))
				.setColor(EnumChatFormatting.BLUE)
				.setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://jq.qq.com/?_wv=1027&k=5Ssxfbn"));
		msg.appendSibling(new ChatComponentTranslation("529418824", new Object[0]).setChatStyle(style));
		event.player.addChatComponentMessage(msg);
	}
}