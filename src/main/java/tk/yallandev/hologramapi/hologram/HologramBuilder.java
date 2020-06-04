package tk.yallandev.hologramapi.hologram;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tk.yallandev.hologramapi.hologram.handler.TouchHandler;
import tk.yallandev.hologramapi.hologram.handler.ViewHandler;
import tk.yallandev.hologramapi.hologram.impl.SimpleHologram;

/**
 * 
 * Class to help creating hologram
 * 
 * @author yandv
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HologramBuilder {
	
	private String displayName;
	private Location location;
	
	private TouchHandler touchHandler;
	private ViewHandler viewHandler;
	
	private Class<? extends Hologram> clazz;
	
	public HologramBuilder(String displayName) {
		this.displayName = displayName;
	}
	
	public HologramBuilder(String displayName, Location location) {
		this.displayName = displayName;
		this.location = location;
	}
	
	public HologramBuilder setDisplayName(String displayName) {
		this.displayName = displayName;
		return this;
	}
	
	public HologramBuilder setLocation(Location location) {
		this.location = location;
		return this;
	}
	
	public HologramBuilder setTouchHandler(TouchHandler touchHandler) {
		this.touchHandler = touchHandler;
		return this;
	}
	
	public HologramBuilder setTouchHandler(ViewHandler viewHandler) {
		this.viewHandler = viewHandler;
		return this;
	}
	
	public HologramBuilder setHologramClass(Class<? extends Hologram> clazz) {
		this.clazz = clazz;
		return this;
	}
	
	public Hologram build() {
		Hologram hologram = null;
		
		if (location == null)
			throw new NullPointerException("Location cannot be null!");
		
		if (displayName == null)
			throw new NullPointerException("DisplayName cannot be null!");
		
		if (clazz == null)
			this.clazz = SimpleHologram.class;
		
		try {
			hologram = clazz.getConstructor(String.class, Location.class).newInstance(displayName, location);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		if (touchHandler != null)
			hologram.setTouchHandler(touchHandler);
		
//TODO make VIEW		if (viewHandler != null)
		
		return hologram;
	}

}
