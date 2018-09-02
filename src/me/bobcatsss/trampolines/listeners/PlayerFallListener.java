package me.bobcatsss.trampolines.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

import me.bobcatsss.trampolines.Core;
import me.bobcatsss.trampolines.events.PlayerFallEvent;

public class PlayerFallListener implements Listener {
	
	private Core plugin;
	public PlayerFallListener(Core pl) {
		this.plugin = pl;
	}
	
	@EventHandler
	public void onFall(PlayerFallEvent e) { 
		Player player = e.getPlayer();
		Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
		if(block.getType() == Material.AIR || block.getType() != Material.SLIME_BLOCK) return;
		
		if(plugin.isTrampoline(block)) {
		player.setVelocity(new Vector(0, 1.0, 0));
		player.playSound(block.getLocation(), Sound.ENTITY_SLIME_SQUISH, 1, 1);
		}
	}
}
