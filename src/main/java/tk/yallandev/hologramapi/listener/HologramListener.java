package tk.yallandev.hologramapi.listener;

import java.util.stream.Collectors;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

import tk.yallandev.hologramapi.controller.HologramController;
import tk.yallandev.hologramapi.hologram.Hologram;
import tk.yallandev.hologramapi.hologram.handler.TouchHandler.TouchType;

public class HologramListener implements Listener {

	private HologramController controller;

	public HologramListener(HologramController controller) {
		this.controller = controller;
	}

	@EventHandler
	public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
		if (event.getRightClicked().getType() == EntityType.ARMOR_STAND) {

			event.setCancelled(true);

			for (Hologram hologram : controller.getHologramList().stream()
					.filter(hologram -> hologram.hasTouchHandler()).collect(Collectors.toList())) {

				if (hologram.compareEntity(event.getRightClicked())) {
					if (hologram.hasTouchHandler())
						hologram.getTouchHandler().onTouch(hologram, event.getPlayer(), TouchType.RIGHT);
				}
			}
		}
	}

	@EventHandler
	public void onChunkLoad(ChunkLoadEvent event) {
		for (Hologram hologram : controller.getHologramList())
			if (hologram.getLocation().getChunk().equals(event.getChunk()))
				if (!hologram.isSpawned()) {
					hologram.spawn();
				}
	}

	@EventHandler
	public void onChunkUnload(ChunkUnloadEvent event) {
		for (Hologram hologram : controller.getHologramList())
			if (hologram.isSpawned())
				if (hologram.getLocation().getChunk().equals(event.getChunk()))
					event.setCancelled(true);
	}

}
