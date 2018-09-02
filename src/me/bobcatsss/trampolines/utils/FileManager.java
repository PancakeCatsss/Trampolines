package me.bobcatsss.trampolines.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.bobcatsss.trampolines.Core;

public class FileManager {

	private File file;
	private FileConfiguration config;
	private Core plugin;

	public FileManager(Core pl, String fileName) {
		this.plugin = pl;
		loadFile(fileName + ".yml");
	}

	private void loadFile(String name) {
		file = new File(plugin.getDataFolder(), name);
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			plugin.saveResource(name, false);
		}

		config = new YamlConfiguration();
		try {
			config.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			config.save(file);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void reload() {
		config = new YamlConfiguration();
		try {
			config.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public FileConfiguration getConfig() {
		return this.config;
	}

}
