package net.simplyrin.vanillanick;

import net.minecraft.util.ChatComponentText;

public class ChatHandler {

	public static void send(String message) {
		message = message.replaceAll("&", "\u00a7");
		message = message.replaceAll("§", "\u00a7");

		VanillaNickMod.getPlayer().addChatComponentMessage(new ChatComponentText(message));
	}

	public static String toNoColor(String message) {
		message = message.replace("&r", "");

		message = message.replace("&1", "");
		message = message.replace("&2", "");
		message = message.replace("&3", "");
		message = message.replace("&4", "");
		message = message.replace("&5", "");
		message = message.replace("&6", "");
		message = message.replace("&7", "");
		message = message.replace("&8", "");
		message = message.replace("&9", "");
		message = message.replace("&0", "");

		message = message.replace("&a", "");
		message = message.replace("&b", "");
		message = message.replace("&c", "");
		message = message.replace("&d", "");
		message = message.replace("&e", "");
		message = message.replace("&f", "");

		message = message.replace("&n", "");
		message = message.replace("&m", "");
		message = message.replace("&k", "");
		message = message.replace("&l", "");

		return message;
	}

}
