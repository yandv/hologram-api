package tk.yallandev.hologramapi.controller;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import tk.yallandev.hologramapi.hologram.Hologram;
import tk.yallandev.hologramapi.listener.HologramListener;

public class HologramController {
	
	private JavaPlugin javaPlugin;
	private List<Hologram> hologramList;
	
	public HologramController(JavaPlugin javaPlugin) {
		this.javaPlugin = javaPlugin;
		Bukkit.getPluginManager().registerEvents(new HologramListener(this), javaPlugin);
	}
	
}
