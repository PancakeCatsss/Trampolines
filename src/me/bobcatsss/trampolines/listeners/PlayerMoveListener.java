package me.bobcatsss.trampolines.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.bobcatsss.trampolines.events.PlayerFallEvent;

public class PlayerMoveListener implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if(e.getTo().getY() == e.getFrom().getY()) return;
		if(e.getTo().getY() > e.getFrom().getY()) return;
		PlayerFallEvent event = new PlayerFallEvent(e.getPlayer());
		Bukkit.getPluginManager().callEvent(event);
	}

}
