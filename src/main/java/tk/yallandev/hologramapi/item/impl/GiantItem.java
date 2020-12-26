package tk.yallandev.hologramapi.item.impl;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import lombok.Getter;
import tk.yallandev.hologramapi.handler.TouchHandler;
import tk.yallandev.hologramapi.item.Item;

@Getter
public class GiantItem implements Item {

	private Location location;
	private ItemStack itemStack;
	
	private boolean spawned;

	private ArmorStand armorStand;
	private Giant giant;

	public GiantItem(Location location, ItemStack itemStack) {
		this.location = location.subtract(-1.5, 0, 3.5);
		this.itemStack = itemStack;
	}

	@Override
	public void spawn() {
		if (isSpawned())
			return;

		spawned = true;
		
		if (!location.getChunk().isLoaded())
			location.getChunk().load();

		try {
			giant = createGiant();
			armorStand = createArmorStand(giant);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove() {
		if (!isSpawned())
			return;

		spawned = false;

		giant.remove();
		armorStand.remove();
	}
	
	@Override
	public ItemStack getItem() {
		return itemStack;
	}

	@Override
	public void setItem(ItemStack itemStack) {
		if (isSpawned()) {
			giant.getEquipment().setItemInHand(itemStack);
		}
		
		this.itemStack = itemStack;
	}

	@Override
	public void setTouchHandler(TouchHandler touchHandler) {

	}

	@Override
	public boolean hasTouchHandler() {
		return false;
	}

	@Override
	public TouchHandler getTouchHandler() {
		return null;
	}
	
	@Override
	public boolean compareEntity(Entity rightClicked) {
		return rightClicked == armorStand || rightClicked == giant;
	}

	private Giant createGiant() {
		Giant giant = (Giant) location.getWorld().spawn(location, Giant.class);
		giant.getEquipment().setItemInHand(itemStack);
		giant.setCustomName("§7-/-");
		giant.setCustomNameVisible(false);
		giant.getEquipment().setItemInHandDropChance(0.0F);
		giant.setCanPickupItems(false);
		giant.setRemoveWhenFarAway(false);
		giant.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 999));
		return giant;
	}

	private ArmorStand createArmorStand(Giant giant) {
		ArmorStand armorStand = (ArmorStand) location.getWorld().spawn(location.clone().subtract(0.0D, 8.0D, 0.0D),
				ArmorStand.class);
		armorStand.setCustomName("§7-/-");
		armorStand.setCustomNameVisible(false);
		armorStand.setGravity(false);
		armorStand.setVisible(false);
		armorStand.setRemoveWhenFarAway(false);
		armorStand.setPassenger(giant);
		return armorStand;
	}

}
