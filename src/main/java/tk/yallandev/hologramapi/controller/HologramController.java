package tk.yallandev.hologramapi.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import tk.yallandev.hologramapi.hologram.Hologram;
import tk.yallandev.hologramapi.hologram.HologramBuilder;
import tk.yallandev.hologramapi.listener.HologramListener;

@Getter
public class HologramController {

	private JavaPlugin javaPlugin;
	private List<Hologram> hologramList;

	public HologramController(JavaPlugin javaPlugin) {
		this.javaPlugin = javaPlugin;
		this.hologramList = new ArrayList<>();
		Bukkit.getPluginManager().registerEvents(new HologramListener(this), javaPlugin);
	}

	public void registerHologram(Hologram hologram) {
		if (!hologramList.contains(hologram))
			hologramList.add(hologram);
	}

	public void unregisterHologram(Hologram hologram) {
		if (hologramList.contains(hologram))
			hologramList.remove(hologram);
	}

	public Hologram createHologram(String displayName, Location location, Class<? extends Hologram> clazz) {
		Hologram hologram = null;

		try {
			hologram = clazz.getConstructor(String.class, Location.class).newInstance(displayName, location);
			hologram.spawn();
			registerHologram(hologram);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		return hologram;
	}

	public Hologram createHologram(HologramBuilder hologramBuilder) {
		Hologram hologram = hologramBuilder.build();

		hologram.spawn();
		registerHologram(hologram);

		return hologram;
	}

	public static HologramController createInstance(JavaPlugin javaPlugin) {
		return new HologramController(javaPlugin);
	}

}
