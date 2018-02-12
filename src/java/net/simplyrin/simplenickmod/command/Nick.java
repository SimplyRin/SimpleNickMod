package net.simplyrin.simplenickmod.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.simplyrin.simplenickmod.Main;

public class Nick extends CommandBase {

	@Override
	public String getCommandName() {
		return "nick";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "&cUsage: /nick <nickname>";
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
		if(!Main.getMod().isInfo()) {
			Main.getMod().sendMessage(Main.getMod().getPrefix() + Main.getMod().getInfoMessage());
			return;
		}

		if(args.length == 0) {
			Main.getMod().sendMessage(Main.getMod().getPrefix() + "&cUsage: /nick <nickname>");
			return;
		}

		for(String name : Main.getMod().getDisabledList()) {
			if(args[0].contains(name.toLowerCase())) {
				Main.getMod().sendMessage(Main.getMod().getPrefix() + "&cThis name is not allowed!");
				return;
			}
		}

		if(args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("clear")) {
			Main.getMod().getNickManager().setNick(false);
			Main.getMod().sendMessage("&aYour nick has been reset!");
			return;
		}

		Main.getMod().getNickManager().setNick(true);
		Main.getMod().getNickManager().setNickname(args[0]);
		Main.getMod().sendMessage("&aYou are now nicked as " + args[0] + "&a!");
	}

}
