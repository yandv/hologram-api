package tk.yallandev.hologramapi.hologram.impl.packet.v1_8_R3;

import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import tk.yallandev.hologramapi.hologram.Hologram;
import tk.yallandev.hologramapi.hologram.handler.TouchHandler;

public class PacketHologram extends tk.yallandev.hologramapi.hologram.impl.packet.PacketHologram {
	
	private String displayName;
	private Location location;
	
	private List<Hologram> hologramList;
	
	private TouchHandler touchHandler;
	
	private boolean spawned;
	
	private ArmorStand armorStand;
	
	private PacketPlayOutSpawnEntityLiving spawnPacket;
	private PacketPlayOutSpawnEntityLiving destroyPacket;
	
	private boolean registered;

	public PacketHologram(String displayName, Location location) {
		this.displayName = displayName;
		this.location = location;
		
		this.armorStand = new ArmorStand(location);
		
		this.spawnPacket = new PacketPlayOutSpawnEntityLiving(armorStand);
		this.destroyPacket = new PacketPlayOutSpawnEntityLiving(armorStand);
	}

	@Override
	public void spawn() {
		if (isSpawned()) {
			throw new IllegalStateException("Hologram already spawned!");
		}
		
		spawned = true;
		
		broadcast(spawnPacket);
	}
	
	@Override
	public void remove() {
		if (!isSpawned()) {
			throw new IllegalStateException("Hologram isnt spawned!");
		}
		
		spawned = false;
		
		broadcast(destroyPacket);
	}

	@Override
	public boolean isSpawned() {
		return spawned;
	}

	@Override
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public boolean hasDisplayName() {
		return isCustomNameVisible();
	}

	@Override
	public boolean isCustomNameVisible() {
		return displayName.isEmpty() || displayName == null ? false : true;
	}

	@Override
	public String getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addLine(String line) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLine(Hologram hologram) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Hologram> getLines() {
		return hologramList;
	}

	@Override
	public void teleport(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public void show(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Player> getViewers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTouchHandler(TouchHandler touchHandler) {
		this.touchHandler = touchHandler;
	}
	
	@Override
	public boolean hasTouchHandler() {
		return touchHandler != null;
	}
	
	@Override
	public TouchHandler getTouchHandler() {
		return touchHandler;
	}

	@Override
	public boolean compareEntity(Entity rightClicked) {
		System.out.println("TA fazendo MERDA");
		return false;
	}
	
	@Override
	public void setRegistered(boolean registered) {
		this.registered = registered;
	}
	
	@Override
	public boolean isRegistered() {
		return registered;
	}
	
	private void sendPacket(Player player, net.minecraft.server.v1_8_R3.Packet<net.minecraft.server.v1_8_R3.PacketListenerPlayOut> packet) {
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
	}

	private void broadcast(net.minecraft.server.v1_8_R3.Packet<net.minecraft.server.v1_8_R3.PacketListenerPlayOut> packet) {
		Bukkit.getOnlinePlayers().forEach(player -> sendPacket(player, packet));
	}
	
}
