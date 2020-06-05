package tk.yallandev.hologramapi.hologram.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import tk.yallandev.hologramapi.hologram.Hologram;
import tk.yallandev.hologramapi.hologram.handler.TouchHandler;

public class SimpleHologram implements Hologram {

	private String displayName;
	private Location location;

	private ArmorStand armorStand;
	private List<Hologram> hologramList;
	
	private TouchHandler touchHandler;
	
	private boolean spawned;

	public SimpleHologram(String displayName, Location location) {
		this.displayName = displayName;
		this.location = location;
		this.hologramList = new ArrayList<>();
	}

	@Override
	public void spawn() {
		spawned = true;
		
		try {
			armorStand = createArmorStand();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		hologramList.forEach(Hologram::spawn);
	}
	
	@Override
	public void remove() {
		spawned = false;
		
		if (armorStand != null)
			try {
				armorStand = createArmorStand();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		hologramList.forEach(Hologram::remove);
	}

	@Override
	public boolean isSpawned() {
		return spawned;
	}

	@Override
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
		
		if (armorStand != null) {
			armorStand.setCustomName(displayName);
			armorStand.setCustomNameVisible(isCustomNameVisible());
		}
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
		return displayName;
	}

	@Override
	public void addLine(String line) {
		SimpleHologram hologram = new SimpleHologram(line, getLocation().clone().subtract(0, (getLines().size() + 1) * 0.25, 0));
		
		hologram.setTouchHandler(getTouchHandler());
		
		if (isSpawned())
			hologram.spawn();
		
		hologramList.add(hologram);
	}

	@Override
	public void addLine(Hologram hologram) {
		if (isSpawned()) {
			hologram.teleport(hologram.getLocation().subtract(0, (getLines().size() + 1) * 0.25, 0));
			hologram.spawn();
		}
		
		if (!hologram.hasTouchHandler())
			hologram.setTouchHandler(getTouchHandler());
		
		hologramList.add(hologram);
	}

	@Override
	public Collection<Hologram> getLines() {
		return hologramList;
	}

	@Override
	public void teleport(Location location) {
		this.location = location;
		
		if (armorStand != null) {
			armorStand.remove();
			
			try {
				armorStand = createArmorStand();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		hologramList.forEach(hologram -> hologram.teleport(location));
	}

	@Override
	public Location getLocation() {
		return location;
	}

	/**
	 * @deprecated The SimpleHologram doesnt support show
	 */
	
	@Override
	public void show(Player player) {
		
	}
	
	/**
	 * @deprecated The SimpleHologram doesnt support hide
	 */

	@Override
	public void hide(Player player) {
		
	}
	
	/**
	 * @deprecated The SimpleHologram doesnt support viewers
	 */

	@Override
	public Collection<Player> getViewers() {
		return new ArrayList<>();
	}
	
	private ArmorStand createArmorStand() throws Exception {
		
		if (!location.getChunk().isLoaded()) {
			if (!location.getChunk().load(true)) {
				throw new Exception("Could not load the chunk " + location.getX() + ", " + location.getY() + ", " + location.getZ());
			}
		}
		
		ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

		armorStand.setVisible(false);
		armorStand.setGravity(false);
		armorStand.setCustomName(displayName);
		armorStand.setCustomNameVisible(true);
		
		return armorStand;
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
		return rightClicked == armorStand;
	}

}
