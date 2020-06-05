package tk.yallandev.hologramapi.hologram.impl.packet.v1_8_R3;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

public class ArmorStand extends net.minecraft.server.v1_8_R3.EntityArmorStand {

	public ArmorStand(Location location) {
		super(((CraftWorld)location.getWorld()).getHandle());
		
		setInvisible(true);
		setBasePlate(false);
		setGravity(false);
		setCustomName("Hologram");
		setCustomNameVisible(true);
		setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
	}
	
}
