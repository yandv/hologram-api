package tk.yallandev.hologramapi;

import org.bukkit.plugin.java.JavaPlugin;

import tk.yallandev.hologramapi.controller.HologramController;

public class HologramMain extends JavaPlugin {
	
	private HologramController controller;
	
	@Override
	public void onEnable() {
		controller = new HologramController(this);
	}
	
	@Override
	public void onDisable() {
	}
	
}
