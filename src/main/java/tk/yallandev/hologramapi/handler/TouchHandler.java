package tk.yallandev.hologramapi.handler;

import org.bukkit.entity.Player;

import tk.yallandev.hologramapi.hologram.Hologram;

/**
 * 
 * Class to watch a Hologram Touch
 * 
 * @author yandv
 *
 */

public interface TouchHandler {

	public void onTouch(Hologram hologram, Player player, TouchType touchType);
	
	public enum TouchType {
		
		LEFT, RIGHT;

	}

}
