package tk.yallandev.hologramapi.hologram.impl;

import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.injector.PacketConstructor;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;

import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.World;
import tk.yallandev.hologramapi.hologram.Hologram;
import tk.yallandev.hologramapi.packet.PacketController;

public class SimpleHologram implements Hologram {

	public static final int MINECRAFT_VERSION = ProtocolLibrary.getProtocolManager().getMinecraftVersion().getMinor();

	private String displayName;
	private Location location;
	
	private EntityArmorStand armorStand;

	public SimpleHologram(String displayName, Location location) {
		
		this.displayName = displayName;
		this.location = location;
		
		armorStand = new EntityArmorStand(((CraftWorld) location.getWorld()).getHandle());
		
		armorStand.setInvisible(true);
		armorStand.setGravity(false);
		armorStand.setCustomName(displayName);
		armorStand.setCustomNameVisible(isCustomNameVisible());
		
		
		
//		PacketPlayOutNamedEntitySpawn asdasd = new PacketPlayOutSpawnEntityLiving(arg0)
		
		PacketContainer packetContainer = ProtocolLibrary.getProtocolManager()
				.createPacketConstructor(PacketType.Play.Server.SPAWN_ENTITY_LIVING, armorStand).createPacket(armorStand);
		
	    double x = location.getX();
	    double y = location.getY();
	    double z = location.getZ();
	    
	    if (MINECRAFT_VERSION < 9) {
	      packetContainer.getIntegers()
	        .write(1, Integer.valueOf((int)Math.floor(x * 32.0D)))
	        .write(2, Integer.valueOf((int)Math.floor(y * 32.0D)))
	        .write(3, Integer.valueOf((int)Math.floor(z * 32.0D)));
	    } else {
	      packetContainer.getDoubles()
	        .write(0, Double.valueOf(x))
	        .write(1, Double.valueOf(y))
	        .write(2, Double.valueOf(z));
	    } 
	    
	    packetContainer.getBytes()
	      .write(0, Byte.valueOf((byte)(int)(location.getYaw() * 256.0F / 360.0F)))
	      .write(1, Byte.valueOf((byte)(int)(location.getPitch() * 256.0F / 360.0F)));
	    if (MINECRAFT_VERSION < 15)
	      packetContainer.getDataWatcherModifier().write(0, new WrappedDataWatcher()); 
		
//		packetContainer.get
	}

	@Override
	public void spawn() {

	}

	@Override
	public boolean isSpawned() {
		return false;
	}

	@Override
	public void setDisplayName(String displayName) {

	}

	@Override
	public boolean hasDisplayName() {
		return false;
	}

	@Override
	public boolean isCustomNameVisible() {
		return displayName.isEmpty() || displayName == null ? false : true;
	}

	@Override
	public String getDisplayName() {
		return null;
	}

	@Override
	public void addLine(String line) {

	}

	@Override
	public void addLine(Hologram hologram) {

	}

	@Override
	public Collection<Hologram> getLines() {
		return null;
	}

	@Override
	public void teleport(Location location) {

	}

	@Override
	public Location getLocation() {
		return location;
	}

}
