package com.perceus.endora;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import com.perceus.endora.spells.BaseSpell;
import com.perceus.endora.spells.destruction.SpellFireballPyroball;
import com.perceus.endora.utilities.EndoraUtilities;

public class CastingMechanic implements Listener
{
	@SuppressWarnings("serial")
	public static Map<String, BaseSpell> spell_registry = new HashMap<>()
	{{
		put("spellfireballpyroball", new SpellFireballPyroball()); 
	}};
	
	@EventHandler
	public void onCast(PlayerInteractEvent event) 
	{
		ItemStack held;
		held = event.getPlayer().getInventory().getItem(EquipmentSlot.HAND) ;

		if (event.getHand() == null) 
		{
			return;
		}
		
		if (event.getHand().equals(EquipmentSlot.OFF_HAND))
		{	
			return;
		}
		
		//if in offhand, return.
		if(held==null || held.getType().equals(Material.AIR))
		{
			return;
		}
		
		String spell = EndoraUtilities.readFromNamespacedKey(held, "internalName");
		
		if (!spell_registry.containsKey(spell)) 
		{
			return;
		}
	}
}
