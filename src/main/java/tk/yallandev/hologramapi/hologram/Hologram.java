package tk.yallandev.hologramapi.hologram;

import org.bukkit.Location;
import org.bukkit.World;

public interface Hologram {
	
	void spawn();
	
	boolean isSpawned();
	
	/**
	 * 
	 * Change the hologram displayName
	 * 
	 * @param displayName
	 */
	
	void setDisplayName(String displayName);
	
	/**
	 * 
	 * Return if the displayName have been registered
	 * 
	 * @return boolean
	 */
	
	boolean hasDisplayName();
	
	/**
	 * 
	 * Return true if the displayName isn't empty or null
	 * 
	 * @return boolean
	 */
	
	boolean isCustomNameVisible();
	
	/**
	 * 
	 * Return the current hologram displayName
	 * 
	 * @return string
	 */
	
	String getDisplayName();
	
	/**
	 * 
	 * Teleport the hologram to specified location
	 * 
	 * @param location
	 */
	
	void teleport(Location location);
	
	default void teleport(World world, int x, int y, int z) {
		teleport(new Location(world, x, y, z));
	}
	
	default void teleport(int x, int y, int z) {
		teleport(new Location(getLocation().getWorld(), x, y, z));
	}
	
	/**
	 * 
	 * Get the Hologram location
	 * 
	 * @return location
	 */
	
	Location getLocation();
}
