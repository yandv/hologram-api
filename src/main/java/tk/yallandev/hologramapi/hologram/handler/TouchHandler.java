package tk.yallandev.hologramapi.hologram.handler;

import org.bukkit.entity.Player;

import lombok.NonNull;
import tk.yallandev.hologramapi.hologram.Hologram;

/**
 * 
 * Class to watch a Hologram Touch
 * 
 * @author yandv
 *
 */

public interface TouchHandler {

	public void onTouch(@NonNull Hologram hologram, @NonNull Player player, TouchType touchType);
	
	public enum TouchType {
		
		LEFT, RIGHT;

	}

}
