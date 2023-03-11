package com.perceus.endora;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class EndoraOnJoin
{
	@EventHandler
	public void onJoin(PlayerJoinEvent event) 
	{
		Player player = event.getPlayer();
		
		if (!(player.hasPlayedBefore())) 
		{
			if (!player.isOp()) 
			{
				player.setGameMode(GameMode.SURVIVAL);
			}
			player.sendMessage("§r§fWelcome to the server, " + player.getDisplayName() + "§r§f!");
			player.sendMessage("§r§fWe're currently running on §r§eEndora§r§f (§cv§41.0.0§f)");
			return;
		}
		
		if (!player.isOp()) 
		{
			player.setGameMode(GameMode.SURVIVAL);
		}
		player.sendMessage("§r§fWelcome to the server, " + player.getDisplayName() + "§r§f!");
		player.sendMessage("§r§fWe're currently running on §r§eEndora§r§f (§cv§41.0.0§f)");
	}
}
