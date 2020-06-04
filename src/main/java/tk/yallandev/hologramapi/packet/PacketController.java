package tk.yallandev.hologramapi.packet;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;

public class PacketController {

	public static PacketContainer newContainer(PacketType packetType) {
		PacketContainer packetContainer = new PacketContainer(packetType);
		
//		if (withEntityId)
//			packetContainer.getIntegers().write(0, Integer.valueOf(this.npc.getEntityId()));
		
		return packetContainer;
	}

}
