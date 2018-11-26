package misat11.hybrid.network.java.pabstract.packet;

import com.github.steveice10.packetlib.packet.Packet;

import lombok.Data;
import misat11.hybrid.network.java.pabstract.data.MagicValues;
import misat11.hybrid.network.java.pabstract.util.ObjectUtil;

@Data
public abstract class MinecraftPacket implements Packet {
	
	private MagicValues magic;
	
    @Override
	public boolean isPriority() {
		return false;
	}

    @Override
    public String toString() {
        return ObjectUtil.toString(this);
    }
}
