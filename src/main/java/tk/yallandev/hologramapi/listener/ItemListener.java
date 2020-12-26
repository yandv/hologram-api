package tk.yallandev.hologramapi.listener;

import java.util.stream.Collectors;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Giant;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;

import tk.yallandev.hologramapi.controller.ItemController;
import tk.yallandev.hologramapi.item.Item;
import tk.yallandev.hologramapi.item.impl.GiantItem;

public class ItemListener implements Listener {

	private ItemController controller;

	public ItemListener(ItemController controller) {
		this.controller = controller;
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Giant))
			return;

		for (Item item : controller.getItemList().stream().filter(item -> item instanceof GiantItem)
				.collect(Collectors.toList()))
			if (item.compareEntity(event.getEntity())) {
				event.setCancelled(true);
			}
	}

	@EventHandler
	public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
//		if (event.getRightClicked().getType() == EntityType.ARMOR_STAND) {
//			for (Hologram hologram : controller.getHologramList().stream()
//					.filter(hologram -> hologram.hasTouchHandler()).collect(Collectors.toList())) {
//				if (hologram.compareEntity(event.getRightClicked())) {
//					if (hologram.hasTouchHandler())
//						hologram.getTouchHandler().onTouch(hologram, event.getPlayer(), TouchType.RIGHT);
//				}
//			}
//			
//			event.setCancelled(true);
//		}
	}

	@EventHandler
	public void onWorldLoad(WorldLoadEvent event) {
		for (Entity giant : event.getWorld().getEntities().stream()
				.filter(entity -> entity.getType() == EntityType.GIANT).collect(Collectors.toList())) {
			giant.remove();
		}
	}

	@EventHandler
	public void onWorldLoad(WorldUnloadEvent event) {
		for (Item item : controller.getItemList())
			if (item.getLocation().getWorld().equals(event.getWorld()))
				if (!item.isSpawned()) {
					item.spawn();
				}
	}

	@EventHandler
	public void onChunkLoad(ChunkLoadEvent event) {
		for (Item item : controller.getItemList())
			if (item.getLocation().getChunk().equals(event.getChunk()))
				if (!item.isSpawned()) {
					item.spawn();
				}
	}

	@EventHandler
	public void onChunkUnload(ChunkUnloadEvent event) {
		for (Item item : controller.getItemList())
			if (item.isSpawned())
				if (item.getLocation().getChunk().equals(event.getChunk()))
					event.setCancelled(true);
	}

	@EventHandler
	public void onPluginDisable(PluginDisableEvent event) {
		if (event.getPlugin() == controller.getJavaPlugin())
			for (Item item : controller.getItemList())
				item.remove();
	}

}
