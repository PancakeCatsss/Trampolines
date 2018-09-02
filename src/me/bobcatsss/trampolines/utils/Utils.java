package me.bobcatsss.trampolines.utils;

import org.bukkit.ChatColor;

public class Utils {
	
	public static String c(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static String prefix = c("&f[&3Trampolines&f] ");

}
