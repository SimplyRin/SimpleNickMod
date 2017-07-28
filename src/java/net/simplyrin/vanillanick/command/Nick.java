package net.simplyrin.vanillanick.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.simplyrin.vanillanick.ChatHandler;
import net.simplyrin.vanillanick.NickManager;
import net.simplyrin.vanillanick.VanillaNickMod;

public class Nick extends CommandBase {

	@Override
	public String getCommandName() {
		return "nick";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "command.vanillaplusmod.usage";
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if(args.length == 0) {
			ChatHandler.send(VanillaNickMod.getPrefix() + "&cUsage: /nick <nickname>");
			return;
		}

		String nick = args[0];

		if(nick.equalsIgnoreCase("reset") || nick.equalsIgnoreCase("clear")) {
			NickManager.enableNick(false);
			ChatHandler.send(VanillaNickMod.getPrefix() + "&aYour nick has been reset!");
			return;
		}

		NickManager.enableNick(true);
		NickManager.setNick(nick);
		ChatHandler.send(VanillaNickMod.getPrefix() + "&aYou are now nicked as " + nick + "&a!");
	}

}
