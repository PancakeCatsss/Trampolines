package me.bobcatsss.trampolines.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.util.Vector;

public class Trampoline {
	
	private Location start, end;
	private boolean set = false;
	private String id;
	private List<Block> blocks = new ArrayList<>();
	private List<Block> replacedBlocks = new ArrayList<>();
	
	public Trampoline(String id) {
		this.id = id;
	}
	
	public void setUp() {
		this.set = true;
		this.blocks = getBlocks(start, end);
		for(Block b : blocks) {
			BlockState state = b.getState();
			state.setType(Material.SLIME_BLOCK);
			state.update(true);
		}
	}
	
	private List<Block> getBlocks(Location start, Location end) {
		double x1 = Math.min(start.getX(), end.getX());
		double x2 = Math.max(start.getX(), end.getX());
		double z1 = Math.min(start.getZ(), end.getZ());
		double z2 = Math.max(start.getZ(), end.getZ());
		Vector vec1 = new Vector(x1, start.getY(), z1);
		Vector vec2 = new Vector(x2, start.getY(), z2);
		List<Block> b = new ArrayList<>();
		for(int x = vec1.getBlockX(); x <= vec2.getBlockX(); x++) {
			for(int z = vec1.getBlockZ(); z <= vec2.getBlockZ(); z++) {
				replacedBlocks.add(start.getWorld().getBlockAt(x, start.getBlockY(), z));
				b.add(start.getWorld().getBlockAt(x, start.getBlockY(), z));
			}
		}
		return b;
	}
	
	public String getID() {
		return id;
	}
	
	public boolean isSet() {
		return set;
	}
	
	public void set(boolean value) {
		this.set = value;
	}
	
	public List<Block> getBlocks() {
		return blocks;
	}
	
	public void setStart(Location start) {
		this.start = start;
	}
	
	public Location getStart() {
		return start;
	}
	
	public void setEnd(Location end) {
		this.end = end;
	}
	
	public Location getEnd() {
		return end;
	}
}
