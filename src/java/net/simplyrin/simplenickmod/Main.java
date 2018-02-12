package net.simplyrin.simplenickmod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import club.sk1er.mods.publicmod.JsonHolder;
import club.sk1er.mods.publicmod.Multithreading;
import club.sk1er.mods.publicmod.Sk1erMod;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.simplyrin.simplenickmod.command.Nick;
import net.simplyrin.simplenickmod.command.NickRank;

@Mod(modid = Main.MODID, version = Main.VERSION, clientSideOnly = true)
public class Main {

	public static final String MODID = "SimpleNickMod";
	public static final String VERSION = "1.3";

	private static Main mod;
	private NickManager nickManager;
	private List<String> disabledList;

	private boolean isInfo = true;
	private String infoMessage = "";

	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);

		ClientCommandHandler.instance.registerCommand(new Nick());
		ClientCommandHandler.instance.registerCommand(new NickRank());

		mod = this;
		this.nickManager = new NickManager();
		this.disabledList = new ArrayList<String>();

		this.disabledList.add("SimplyRin");
		this.disabledList.add("KawaiiRin");

		Multithreading.runAsync(() -> {
			try {
				JsonHolder result = new JsonHolder(Sk1erMod.rawWithAgent("https://api.simplyrin.net/Forge-Mods/" + MODID + "/Players/" + Minecraft.getMinecraft().getSession().getProfile().getId().toString() + ".json"));
				if(result.has("result")) {
					this.isInfo = result.optBoolean("result");
				}
			} catch (Exception e) {
			}

			try {
				JsonHolder result = new JsonHolder(Sk1erMod.rawWithAgent("https://api.simplyrin.net/Forge-Mods/" + MODID + "/info.json"));
				if(result.has("enabled")) {
					this.isInfo = result.optBoolean("enabled");
					if(!this.isInfo) {
						this.infoMessage = ChatColor.translateAlternateColorCodes('&', result.has("message") ? result.getString("message") : "&c&lThis is temporarily disabled.");
					}
				}
			} catch (Exception e) {
			}

			try {
				String[] result = Sk1erMod.rawWithAgent("https://api.simplyrin.net/Forge-Mods/SimpleNickMod/disabledNames.txt").split(",");
				for(String name : result) {
					this.disabledList.add(name);
				}
			} catch(Exception e) {
			}
		});
	}

	@SubscribeEvent
	public void onChat(ClientChatReceivedEvent event) {
		if(!this.isInfo) {
			return;
		}

		if(!this.nickManager.isNick()) {
			return;
		}

		if(event.isCanceled()) {
			return;
		}

		String message = event.message.getFormattedText();
		EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;

		message = message.replaceAll("\u00a7", "&");

		if(message.contains(player.getName())) {
			String nickPrefix = this.nickManager.getPrefix();
			String nick = this.nickManager.getNickname();

			event.setCanceled(true);

			message = message.replaceAll("&r", "");
			message = message.replace("&r", "");

			List<String> list = Arrays.asList("&a[VIP]", "&a[VIP&6+&a]", "&b[MVP]", "&b[MVP&c+&b]", "&b[MVP&6+&b]", "&b[MVP&a+&b]", "&b[MVP&e+&b]", "&b[MVP&d+&b]", "&b[MVP&f+&b]", "&b[MVP&9+&b]", "\"&b[MVP&2+&b]", "&b[MVP&4+&b]", "&b[MVP&3+&b]", "&b[MVP&5+&b]", "&b[MVP&7+&b]", "&b[MVP&0+&b]");

			if(message.equals("&e" + player.getName() + " joined.")) {
				this.sendMessage("&e" + message.replace(player.getName(), nick));
				return;
			}

			for(String prefix : list) {
				if(message.contains(prefix + " " + player.getName())) {
					message = message.replace(prefix + " " + player.getName(), nickPrefix + " " + nick);
					this.sendMessage(message);
					return;
				}
			}

			if(message.contains(":")) {
				message = message.replace(player.getName(), nickPrefix + " " + nick);
				this.sendMessage(message.replaceAll("&7", "&f"));
				return;
			}

			message = message.replaceAll(player.getName(), StringUtils.left(nickPrefix, 2) + "" + nick);
			this.sendMessage(message);
		}
	}

	public static Main getMod() {
		return mod;
	}

	public String getPrefix() {
		return "\u00a77[\u00a7cSimpleNick\u00a77] \u00a7r";
	}

	public void sendMessage(String message) {
		message = message.replaceAll("&", "\u00a7");
		message = message.replaceAll("§", "\u00a7");

		Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(message));
	}

	public NickManager getNickManager() {
		return this.nickManager;
	}

	public List<String> getDisabledList() {
		return this.disabledList;
	}

	public boolean isInfo() {
		return this.isInfo;
	}

	public String getInfoMessage() {
		return this.infoMessage;
	}

	public class NickManager {

		private boolean nick;
		private String nickname;
		private String prefix = "&a[VIP]";

		public void setNick(boolean b) {
			this.nick = b;
		}

		public void setNickname(String nick) {
			this.nickname = nick;
		}

		public String getNickname() {
			return this.nickname;
		}

		public void setPrefix(String nickprefix) {
			this.prefix = nickprefix;
		}

		public String getPrefix() {
			return this.prefix;
		}

		public boolean isNick() {
			return this.nick;
		}

	}

}
