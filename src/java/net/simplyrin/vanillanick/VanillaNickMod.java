package net.simplyrin.vanillanick;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.simplyrin.vanillanick.command.Nick;
import net.simplyrin.vanillanick.command.NickRank;

@Mod(modid = VanillaNickMod.MODID, version = VanillaNickMod.VERSION, clientSideOnly = true)
public class VanillaNickMod {

    public static final String MODID = "SimpleNickMod";
    public static final String VERSION = "1.1";

	@EventHandler
    public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);

    	ClientCommandHandler.instance.registerCommand(new Nick());
    	ClientCommandHandler.instance.registerCommand(new NickRank());
    }

	@SubscribeEvent
	public void onChat(ClientChatReceivedEvent event) {
		NickManager.onEvent(event);
	}

	public static String getPrefix() {
		return "\u00a77[\u00a7cSimpleNick\u00a77] \u00a7r";
	}

	public static Minecraft getMinecraft() {
		return Minecraft.getMinecraft();
	}

	public static EntityPlayerSP getPlayer() {
		return VanillaNickMod.getMinecraft().thePlayer;
	}
}
