package com.perceus.endora.spells.destruction;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.endora.spells.BaseSpell;
import com.perceus.endora.utilities.SpellParticles;

public class SpellFireballPyroball extends BaseSpell
{

	public SpellFireballPyroball()
	{
		super(Material.FIRE_CHARGE, "§r§fSpell: Fireball", "spellfireballpyroball", 0, "");
		// TODO Auto-generated constructor stub
	}

	private static Map<UUID,Integer> superCastMechanic = new HashMap<>();
	
	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) 
		{
			player.sendMessage("Invalid Cast Method.");
			return false;
		}
		
		if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) 
		{
			player.sendMessage("Invalid Cast Method.");
			return false;
		}
		
		if (event.getAction().equals(Action.LEFT_CLICK_AIR)) 
		{
			if (!superCastMechanic.containsKey(event.getPlayer().getUniqueId()))
			{
				player.sendMessage("§r§cFireball§r§f selected.");
				superCastMechanic.put(player.getUniqueId(), 1);
				return true;
			}
			
			if (superCastMechanic.get(player.getUniqueId()) == 1)
			{
				player.sendMessage("§r§4§lPyroball§r§f selected.");
				superCastMechanic.put(player.getUniqueId(), 2);
				return true;
			}
			
			if (superCastMechanic.get(player.getUniqueId()) == 2)
			{
				player.sendMessage("§r§cFireball§r§f selected.");
				superCastMechanic.put(player.getUniqueId(), 1);
				return true;
			}
		}
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			
			if (superCastMechanic.get(player.getUniqueId()) == 1) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
				player.playSound(event.getPlayer().getLocation(), Sound.ENTITY_BLAZE_SHOOT, SoundCategory.MASTER, 1, 1);
				player.launchProjectile(SmallFireball.class);
				return true;
			}
			
			if (superCastMechanic.get(player.getUniqueId()) == 2) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.SMOKE_NORMAL, null);
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 25, Particle.FLAME, null);
				player.playSound(event.getPlayer().getLocation(), Sound.ENTITY_BLAZE_SHOOT, SoundCategory.MASTER, 1, 1);
				SmallFireball fb = event.getPlayer().launchProjectile(SmallFireball.class);
				fb.setIsIncendiary(true);
				fb.setYield(5);
				fb.setFireTicks(600);
				fb.setGlowing(true);
				fb.setVisualFire(false);
				return true;
			}	
		}
		return false;
	}
}
