package me.bobcatsss.trampolines;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.bobcatsss.trampolines.commands.CreateTrampolineCommand;
import me.bobcatsss.trampolines.listeners.PlayerFallListener;
import me.bobcatsss.trampolines.listeners.PlayerMoveListener;
import me.bobcatsss.trampolines.listeners.TrampolineSetListener;
import me.bobcatsss.trampolines.utils.FileManager;
import me.bobcatsss.trampolines.utils.Trampoline;

public class Core extends JavaPlugin {
	
	private Map<Player, Trampoline> creating = new HashMap<>();
	private Map<String, Trampoline> trampolines = new HashMap<>();
	private FileManager trampolinesFile = null;
	
	@Override
	public void onEnable() {
		trampolinesFile = new FileManager(this, "trampolines");
		loadTrampolines();
		Bukkit.getPluginManager().registerEvents(new PlayerFallListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
		Bukkit.getPluginManager().registerEvents(new TrampolineSetListener(this), this);
		getCommand("trampoline").setExecutor(new CreateTrampolineCommand(this));
	}
	
	@Override
	public void onDisable() {
		saveTrampolines();
		trampolines.clear();
		trampolines = null;
		trampolinesFile = null;
		creating.clear();
		creating = null;
	}
	
	private void saveTrampolines() {
		if(getTrampolines().isEmpty()) return;
		for(Trampoline t : getTrampolines().values()) {
			getTrampolineFile().getConfig().set(t.getID() + ".Pos-1", t.getStart());
			getTrampolineFile().getConfig().set(t.getID() + ".Pos-2", t.getEnd());
		}
		getTrampolineFile().save();
	}
	
	private void loadTrampolines() {
		new BukkitRunnable() {

			@Override
			public void run() {
				for(String key : getTrampolineFile().getConfig().getKeys(false)) {
					Trampoline t = new Trampoline(key);
					t.setStart((Location) getTrampolineFile().getConfig().get(key + ".Pos-1"));
					t.setEnd((Location) getTrampolineFile().getConfig().get(key + ".Pos-2"));
					t.setUp();
					getTrampolines().put(key, t);
				}
			}
		}.runTaskLater(this, 20);
	}
	
	public Map<Player, Trampoline> getCreating() {
		return creating;
	}
	
	public void removeTrampoline(String id) {
		getTrampolineFile().getConfig().set(id, null);
		getTrampolineFile().save();
		getTrampolines().remove(id);
	}
	
	public boolean isTrampoline(Block block) {
		for(Trampoline t : getTrampolines().values()) {
			if(t.getBlocks().contains(block)) {
				return true;
			}
		}
		return false;
	}
	
	public Trampoline getTrampoline(String id) {
		if(trampolines.containsKey(id)) {
			return trampolines.get(id);
		}
		return null;
	}
	
	public FileManager getTrampolineFile() {
		return trampolinesFile;
	}
	
	public Map<String, Trampoline> getTrampolines() {
		return trampolines;
	}
}
