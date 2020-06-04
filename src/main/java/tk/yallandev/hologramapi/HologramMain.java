package tk.yallandev.hologramapi;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import tk.yallandev.hologramapi.controller.HologramController;
import tk.yallandev.hologramapi.hologram.Hologram;
import tk.yallandev.hologramapi.hologram.handler.TouchHandler;
import tk.yallandev.hologramapi.hologram.impl.SimpleHologram;

public class HologramMain extends JavaPlugin {
	
	private HologramController controller;
	
	@Override
	public void onEnable() {
		controller = HologramController.createInstance(this);
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				Hologram hologram = controller.createHologram("caralho", new Location(Bukkit.getWorlds().get(0), 0, 120, 0), SimpleHologram.class);

				hologram.setTouchHandler(new TouchHandler() {
					
					@Override
					public void onTouch(Hologram hologram, Player player, TouchType touchType) {
						player.sendMessage("eae");
					}
				});
				
				hologram.addLine("to so testando tlg");
				hologram.addLine(controller.createHologram("otroteste", new Location(Bukkit.getWorlds().get(0), 0, 120, 0), SimpleHologram.class));
			}
		}.runTask(this);
	}
	
	@Override
	public void onDisable() {
		controller.handleDisable();
	}
	
}
