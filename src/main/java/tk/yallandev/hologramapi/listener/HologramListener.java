package tk.yallandev.hologramapi.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tk.yallandev.hologramapi.controller.HologramController;
import tk.yallandev.hologramapi.hologram.Hologram;
import tk.yallandev.hologramapi.hologram.impl.SimpleHologram;

public class HologramListener implements Listener {
	
	private HologramController controller;
	
	public HologramListener(HologramController controller) {
		this.controller = controller;
	}
	
	@EventHandler
	public void asokda(PlayerJoinEvent event) {
		Hologram hologram = new SimpleHologram("caralho", event.getPlayer().getLocation().add(0, 10, 0));
		
		event.getPlayer().sendMessage("eae");
	}

}
