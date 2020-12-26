package tk.yallandev.hologramapi;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketEvent;

import tk.yallandev.hologramapi.controller.HologramController;
import tk.yallandev.hologramapi.controller.ItemController;
import tk.yallandev.hologramapi.handler.TouchHandler;
import tk.yallandev.hologramapi.hologram.Hologram;
import tk.yallandev.hologramapi.hologram.impl.SimpleHologram;
import tk.yallandev.hologramapi.item.Item;
import tk.yallandev.hologramapi.item.impl.GiantItem;
import tk.yallandev.nms.packet.PacketController;
import tk.yallandev.nms.packet.PacketHandler;
import tk.yallandev.nms.packet.wrapper.AbstractPacket;

public class HologramMain extends JavaPlugin {

	private PacketController packetController;
	private HologramController hologramController;
	private ItemController itemController;

	@Override
	public void onEnable() {
		hologramController = HologramController.createInstance(this);
		itemController = ItemController.createInstance(this);

		packetController = new PacketController(this);
		packetController.registerHandler(new PacketHandler() {

			@Override
			public boolean onPacketReceive(AbstractPacket abstractPacket, PacketType packetType,
					PacketEvent packetEvent) {

				return false;
			}

			@Override
			public boolean onPacketSend(AbstractPacket abstractPacket, PacketType packetType, PacketEvent packetEvent) {

				return false;
			}

		});

		new BukkitRunnable() {

			@Override
			public void run() {
				Hologram hologram = hologramController.createHologram("caralho",
						new Location(Bukkit.getWorlds().get(0), 0, 120, 0), SimpleHologram.class);
				
				hologram.setTouchHandler(new TouchHandler() {
					
					@Override
					public void onTouch(Hologram hologram, Player player, TouchType touchType) {
						player.sendMessage("eae");
					}
				});
				
				hologram.addLine("to so testando tlg");
				hologram.addLine(hologramController.createHologram("otroteste",
						new Location(Bukkit.getWorlds().get(0), 0, 120, 0), SimpleHologram.class));
				
				Item item = itemController.createItem(new Location(Bukkit.getWorlds().get(0), 0, 120, 0),
						new ItemStack(Material.MUSHROOM_SOUP), GiantItem.class);
				
				item.spawn();
			}
		}.runTask(this);
	}

}
