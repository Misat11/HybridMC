package misat11.hybrid.network.java.pabstract.packet;

import com.github.steveice10.packetlib.packet.Packet;

public interface IMinecraftPacket extends Packet {
    @Override
	default boolean isPriority() {
		return false;
	}
}
