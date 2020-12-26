package tk.yallandev.hologramapi.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import tk.yallandev.hologramapi.item.Item;
import tk.yallandev.hologramapi.listener.ItemListener;

@Getter
public class ItemController {
	
	private JavaPlugin javaPlugin;
	private List<Item> itemList;

	public ItemController(JavaPlugin javaPlugin) {
		this.javaPlugin = javaPlugin;
		this.itemList = new ArrayList<>();
		Bukkit.getPluginManager().registerEvents(new ItemListener(this), javaPlugin);
	}

	public void registerHologram(Item item) {
		if (!itemList.contains(item))
			itemList.add(item);
	}

	public void unregisterHologram(Item item) {
		if (itemList.contains(item))
			itemList.remove(item);
	}
	
	public Item createItem(Location location, ItemStack itemStack, Class<? extends Item> clazz) {
		Item item = null;

		try {
			item = clazz.getConstructor(Location.class, ItemStack.class).newInstance(location, itemStack);
			item.spawn();
			registerHologram(item);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		return item;
	}
	
	public static ItemController createInstance(JavaPlugin javaPlugin) {
		return new ItemController(javaPlugin);
	}

}
