package tk.yallandev.hologramapi.hologram.impl.packet;

import tk.yallandev.hologramapi.hologram.Hologram;

/**
 * 
 * Support only for PacketHologram
 * PacketHolograms supports hide, show and viewerList
 * 
 * OBS: The PacketHologram doesnt works for version < 47 because the packet is only for
 * 1.8+, we doesnt use EntityHorse to make the Hologram to lower versions
 * 
 * @author yandv
 *
 */

public abstract class PacketHologram implements Hologram {
	
	/**
	 * 
	 * A PacketHologram registered
	 * 
	 * @param registered
	 */
	
	public abstract void setRegistered(boolean registered);
	
	/**
	 * 
	 * Check if the PacketHologram is registered
	 * 
	 * @param registered
	 */
	
	public abstract boolean isRegistered();

}
