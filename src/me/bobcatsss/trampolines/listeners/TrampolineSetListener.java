package me.bobcatsss.trampolines.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import me.bobcatsss.trampolines.Core;
import me.bobcatsss.trampolines.utils.Messages;
import me.bobcatsss.trampolines.utils.Trampoline;

public class TrampolineSetListener implements Listener {
	
	private Core plugin;
	public TrampolineSetListener(Core pl) {
		this.plugin = pl;
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Action action = e.getAction();
		Player player = e.getPlayer();
		if(e.getClickedBlock() == null || e.getClickedBlock().getType() == Material.AIR) return;
		if(e.getHand() == EquipmentSlot.OFF_HAND) return;
		if(!plugin.getCreating().containsKey(player)) return;
		Trampoline t = plugin.getCreating().get(player);
		e.setCancelled(true);
		if(action == Action.LEFT_CLICK_BLOCK) {
			t.setStart(e.getClickedBlock().getLocation());
			player.sendMessage(Messages.POS_ONE_SET.getValue());
			return;
		}
		if(action == Action.RIGHT_CLICK_BLOCK) {
			if(t.getStart() == null) {
				player.sendMessage(Messages.POS_ONE_REQUIRED.getValue());
				return;
			}
			if(!t.getStart().getWorld().getUID().equals(e.getClickedBlock().getWorld().getUID())) {
				player.sendMessage(Messages.BETWEEN_WORLDS.getValue("{trampoline_id}", t.getID()));
				return;
			}
			if(t.getStart().distance(e.getClickedBlock().getLocation()) > 250) {
				player.sendMessage(Messages.TRAMPOLINE_TOO_BIG.getValue("{trampoline_id}", t.getID()));
				return;
			}
			t.setEnd(e.getClickedBlock().getLocation());
			t.set(true);
			t.setUp();
			e.getPlayer().sendMessage(Messages.TRAMPOLINE_CREATED.getValue("{trampoline_id}", t.getID()));
			plugin.getCreating().remove(player);
			plugin.getTrampolines().put(t.getID(), t);
			return;
		}
	}

}
