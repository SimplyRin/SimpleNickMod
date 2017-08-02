package net.simplyrin.vanillanick;

import org.apache.commons.lang3.StringUtils;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

public class NickManager {

	public static boolean enableNick;
	private static String nickname;
	private static String prefix = "&6[YT]";

	public static void enableNick(boolean b) {
		enableNick = b;
	}

	public static void setNick(String nick) {
		nickname = nick;
	}

	public static String getNick() {
		return nickname;
	}

	public static void setPrefix(String nickprefix) {
		prefix = nickprefix;
	}

	public static String getPrefix() {
		return prefix;
	}

	public static void onEvent(ClientChatReceivedEvent event) {
		if(NickManager.enableNick) {
			String message = event.message.getFormattedText();

			message = message.replaceAll("\u00a7", "&");

			if(message.contains(VanillaNickMod.getPlayer().getName())) {

				EntityPlayerSP p = VanillaNickMod.getPlayer();
				String prefix = NickManager.getPrefix();
				String nick = NickManager.getNick();

				event.setCanceled(true);

				message = message.replaceAll("&r", "");
				message = message.replace("&r", "");

				if(message.equals("&e" + VanillaNickMod.getPlayer().getName() + " joined.")) {
					ChatHandler.send("&e" + message.replaceAll(p.getName(), nick));
					return;
				}

				if(message.contains("&a[VIP] " + VanillaNickMod.getPlayer().getName())) {
					message = message.replace("&a[VIP] " + p.getName(), prefix + " " + nick);
					ChatHandler.send(message);
					return;
				}

				if(message.contains("&a[VIP&6+&a] " + VanillaNickMod.getPlayer().getName())) {
					message = message.replace("&a[VIP&6+&a] " + p.getName(), prefix + " " + nick);
					ChatHandler.send(message);
					return;
				}

				if(message.contains("&b[MVP] " + VanillaNickMod.getPlayer().getName())) {
					message = message.replace("&b[MVP] " + p.getName(), prefix + " " + nick);
					ChatHandler.send(message);
					return;
				}

				// MVP+ RED
				if(message.contains("&b[MVP&c+&b] " + VanillaNickMod.getPlayer().getName())) {
					message = message.replace("&b[MVP&c+&b] " + p.getName(), prefix + " " + nick);
					ChatHandler.send(message);
					return;
				}

				// MVP+ GOLD
				if(message.contains("&b[MVP&6+&b] " + VanillaNickMod.getPlayer().getName())) {
					message = message.replace("&b[MVP&6+&b] " + p.getName(), prefix + " " + nick);
					ChatHandler.send(message);
					return;
				}

				// MVP+ GREEN
				if(message.contains("&b[MVP&a+&b] " + VanillaNickMod.getPlayer().getName())) {
					message = message.replace("&b[MVP&a+&b] " + p.getName(), prefix + " " + nick);
					ChatHandler.send(message);
					return;
				}

				// MVP+ YELLOW
				if(message.contains("&b[MVP&e+&b] " + VanillaNickMod.getPlayer().getName())) {
					message = message.replace("&b[MVP&e+&b] " + p.getName(), prefix + " " + nick);
					ChatHandler.send(message);
					return;
				}

				// MVP+ PINK
				if(message.contains("&b[MVP&d+&b] " + VanillaNickMod.getPlayer().getName())) {
					message = message.replace("&b[MVP&d+&b] " + p.getName(), prefix + " " + nick);
					ChatHandler.send(message);
					return;
				}

				// MVP+ WHITE
				if(message.contains("&b[MVP&f+&b] " + VanillaNickMod.getPlayer().getName())) {
					message = message.replace("&b[MVP&f+&b] " + p.getName(), prefix + " " + nick);
					ChatHandler.send(message);
					return;
				}

				// MVP+ BLUE
				if(message.contains("&b[MVP&9+&b] " + VanillaNickMod.getPlayer().getName())) {
					message = message.replace("&b[MVP&9+&b] " + p.getName(), prefix + " " + nick);
					ChatHandler.send(message);
					return;
				}

				// MVP+ DARK GREEN
				if(message.contains("&b[MVP&2+&b] " + VanillaNickMod.getPlayer().getName())) {
					message = message.replace("&b[MVP&2+&b] " + p.getName(), prefix + " " + nick);
					ChatHandler.send(message);
					return;
				}

				// MVP+ DARK RED
				if(message.contains("&b[MVP&4+&b] " + VanillaNickMod.getPlayer().getName())) {
					message = message.replace("&b[MVP&4+&b] " + p.getName(), prefix + " " + nick);
					ChatHandler.send(message);
					return;
				}

				// MVP+ DARK AQUA
				if(message.contains("&b[MVP&3+&b] " + VanillaNickMod.getPlayer().getName())) {
					message = message.replace("&b[MVP&3+&b] " + p.getName(), prefix + " " + nick);
					ChatHandler.send(message);
					return;
				}

				// MVP+ DARK PURPLE
				if(message.contains("&b[MVP&5+&b] " + VanillaNickMod.getPlayer().getName())) {
					message = message.replace("&b[MVP&5+&b] " + p.getName(), prefix + " " + nick);
					ChatHandler.send(message);
					return;
				}

				// MVP+ GRAY
				if(message.contains("&b[MVP&7+&b] " + VanillaNickMod.getPlayer().getName())) {
					message = message.replace("&b[MVP&7+&b] " + p.getName(), prefix + " " + nick);
					ChatHandler.send(message);
					return;
				}

				// MVP+ BLACK
				if(message.contains("&b[MVP&0+&b] " + VanillaNickMod.getPlayer().getName())) {
					message = message.replace("&b[MVP&0+&b] " + p.getName(), prefix + " " + nick);
					ChatHandler.send(message);
					return;
				}

				// Default
				if(message.contains(":")) {
					message = message.replace(VanillaNickMod.getPlayer().getName(), prefix + " " + nick);
					ChatHandler.send(message.replaceAll("&7", "&f"));
					return;
				}

				message = message.replaceAll(p.getName(), StringUtils.left(prefix, 2) + "" + nick);
				ChatHandler.send(message);
			}
		}
	}
}
