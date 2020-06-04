package tk.yallandev.hologramapi.hologram;

import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import tk.yallandev.hologramapi.hologram.handler.TouchHandler;

public interface Hologram {
	
	void spawn();
	
	void remove();
	
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
	 * Add a line in this Hologram
	 * 
	 * @param hologram
	 */
	
	public void addLine(String line);
	
	/**
	 * 
	 * Add a line in this Hologram
	 * 
	 * @param hologram
	 */
	
	public void addLine(Hologram hologram);
	
	/**
	 * 
	 * Return all lines the hologram
	 * 
	 * @return collection
	 */
	
	Collection<Hologram> getLines();
	
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
	
	/**
	 * 
	 * Show Hologram for player
	 * 
	 * @param player
	 */
	
	void show(Player player);
	
	/**
	 * 
	 * Hide Hologram for player
	 * 
	 * @param player
	 */
	
	void hide(Player player);
	
	/**
	 * 
	 * Return all viewers
	 * 
	 * @return
	 */
	
	Collection<Player> getViewers();
	
	/**
	 * 
	 * Set the TouchHandler of the Hologram
	 * 
	 * @param touchHandler
	 */
	
	void setTouchHandler(TouchHandler touchHandler);
	
	/**
	 * Return if have a TouchHandler
	 * 
	 * @return
	 */
	
	boolean hasTouchHandler();
	
	/**
	 * 
	 * Return the TouchHandler, or null
	 * 
	 * @return
	 */
	
	TouchHandler getTouchHandler();
	
	/**
	 * 
	 * Compare the ArmorStand entity of the Hologram with
	 * 
	 * @param rightClicked
	 * @return
	 */

	boolean compareEntity(Entity rightClicked);
}
