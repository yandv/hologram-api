package tk.yallandev.hologramapi.listener;

import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.world.WorldLoadEvent;

import tk.yallandev.hologramapi.controller.HologramController;
import tk.yallandev.hologramapi.handler.TouchHandler.TouchType;
import tk.yallandev.hologramapi.hologram.Hologram;

public class HologramListener implements Listener {

	private HologramController controller;

	public HologramListener(HologramController controller) {
		this.controller = controller;

		for (World world : Bukkit.getWorlds())
			for (Entity armorStand : world.getEntities().stream()
					.filter(entity -> entity.getType() == EntityType.ARMOR_STAND).collect(Collectors.toList()))
				armorStand.remove();
	}

	@EventHandler
	public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
		if (event.getRightClicked().getType() == EntityType.ARMOR_STAND) {
			for (Hologram hologram : controller.getHologramList().stream()
					.filter(hologram -> hologram.hasTouchHandler()).collect(Collectors.toList())) {
				if (hologram.compareEntity(event.getRightClicked())) {
					if (hologram.hasTouchHandler())
						hologram.getTouchHandler().onTouch(hologram, event.getPlayer(), TouchType.RIGHT);
				}
			}

			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent e) {
		ArmorStand armorStand = e.getRightClicked();

		if (!armorStand.isVisible()) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onWorldLoad(WorldLoadEvent event) {
		for (Entity armorStand : event.getWorld().getEntities().stream()
				.filter(entity -> entity.getType() == EntityType.ARMOR_STAND).collect(Collectors.toList()))
			armorStand.remove();
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

	@EventHandler
	public void onPluginDisable(PluginDisableEvent event) {
		if (event.getPlugin() == controller.getJavaPlugin())
			for (Hologram hologram : controller.getHologramList())
				hologram.remove();
	}

}
