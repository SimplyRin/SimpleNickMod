package net.simplyrin.vanillanick.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.simplyrin.vanillanick.ChatHandler;
import net.simplyrin.vanillanick.NickManager;
import net.simplyrin.vanillanick.VanillaNickMod;

public class NickRank extends CommandBase {

	@Override
	public String getCommandName() {
		return "nickrank";
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
			ChatHandler.send(VanillaNickMod.getPrefix() + "&cUsage: /nickrank <prefix>");
			return;
		}

		String prefix = args[0];

		NickManager.setPrefix(prefix);
		ChatHandler.send(VanillaNickMod.getPrefix() + "&aYou are nicked rank is " + prefix + "!");
	}

}

