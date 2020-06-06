package tk.yallandev.hologramapi;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketEvent;

import tk.yallandev.hologramapi.controller.HologramController;
import tk.yallandev.hologramapi.hologram.Hologram;
import tk.yallandev.hologramapi.hologram.handler.TouchHandler;
import tk.yallandev.hologramapi.hologram.impl.SimpleHologram;
import tk.yallandev.nms.packet.PacketController;
import tk.yallandev.nms.packet.PacketHandler;
import tk.yallandev.nms.packet.wrapper.AbstractPacket;

public class HologramMain extends JavaPlugin {

	private PacketController packetController;
	private HologramController hologramController;

	@Override
	public void onEnable() {
		hologramController = HologramController.createInstance(this);

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
			}
		}.runTask(this);
	}

}
