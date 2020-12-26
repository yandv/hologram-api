package tk.yallandev.hologramapi.item;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import tk.yallandev.hologramapi.handler.TouchHandler;

public interface Item {
	
	void spawn();
	
	void remove();
	
	boolean isSpawned();
	
	ItemStack getItem();
	
	void setItem(ItemStack itemStack);
	
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
	 * Return the TouchHandler, or null
	 * 
	 * @return
	 */
	
	Location getLocation();
	
	/**
	 * 
	 * Compare the ArmorStand entity of the Hologram with
	 * 
	 * @param rightClicked
	 * @return
	 */

	boolean compareEntity(Entity rightClicked);

}
