package com.perceus.endora.spells;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.endora.utilities.EndoraUtilities;

public abstract class BaseSpell
{
	public abstract boolean cast(PlayerInteractEvent event);
	
	int manaCost;
	protected String displayName;
	protected String[] lore;
	protected Material material;
	//protected boolean glow = false;
	protected String internalName;
	//protected boolean bookspell;
	//protected double cooldown;
	
	public BaseSpell(Material material, String displayName, String internalName, int manaCost, String... lore) 
	{
		this.material = material;
		this.displayName = displayName;
		this.internalName = internalName;
		this.manaCost = manaCost;
		this.lore = lore;
		EndoraUtilities.saveToNamespacedKey(null, displayName, internalName);
	}
	
	public int getManaCost() 
	{	
		return manaCost;
	}
	
	public String getInternalName() 
	{
		return internalName;
	}
	
	
}
