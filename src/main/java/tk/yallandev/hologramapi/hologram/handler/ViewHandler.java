package tk.yallandev.hologramapi.hologram.handler;

import org.bukkit.entity.Player;

import lombok.NonNull;
import tk.yallandev.hologramapi.hologram.Hologram;


/**
 * 
 * Class to watch a Hologram View
 * 
 * Por enquanto não da para editar o holograma quando está sendo visto
 * 
 * @author yandv
 *
 */

public interface ViewHandler {
	
	public void onView(@NonNull Hologram hologram, @NonNull Player player);

}
