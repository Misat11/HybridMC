package misat11.hybrid.network.java.p393.packet.ingame.server.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.pabstract.data.game.window.property.WindowProperty;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.window.ServerWindowPropertyPacket;

import java.io.IOException;

public class ServerWindowPropertyPacket393 extends MinecraftPacket implements ServerWindowPropertyPacket {
    private int windowId;
    private int property;
    private int value;

    @SuppressWarnings("unused")
    private ServerWindowPropertyPacket393() {
    }

    public ServerWindowPropertyPacket393(int windowId, int property, int value) {
        this.windowId = windowId;
        this.property = property;
        this.value = value;
    }

    public <T extends Enum<T> & WindowProperty> ServerWindowPropertyPacket393(int windowId, T property, int value) {
        this.windowId = windowId;
        this.property = getMagic().value(Integer.class, property);
        this.value = value;
    }

    public int getWindowId() {
        return this.windowId;
    }

    public int getRawProperty() {
        return this.property;
    }

    public <T extends Enum<T> & WindowProperty> T getProperty(Class<T> type) {
        return getMagic().key(type, this.value);
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.windowId = in.readUnsignedByte();
        this.property = in.readShort();
        this.value = in.readShort();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.windowId);
        out.writeShort(this.property);
        out.writeShort(this.value);
    }
}
