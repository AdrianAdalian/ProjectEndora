package com.perceus.endora;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Endora extends JavaPlugin
{
	@Override
	public void onEnable() 
	{
		Bukkit.getConsoleSender().sendMessage("§aEndora Enabled");
	}
	
	@Override
	public void onDisable() 
	{
		
	}
}
