package misat11.hybrid.network.java.pabstract.packet;

import com.github.steveice10.packetlib.packet.Packet;

import misat11.hybrid.network.java.pabstract.util.ObjectUtil;

public abstract class MinecraftPacket implements Packet {
    @Override
    public boolean isPriority() {
        return false;
    }

    @Override
    public String toString() {
        return ObjectUtil.toString(this);
    }
}
