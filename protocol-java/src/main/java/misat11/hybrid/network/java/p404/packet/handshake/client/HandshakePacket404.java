package misat11.hybrid.network.java.p404.packet.handshake.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.handshake.HandshakeIntent;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.handshake.client.HandshakePacket;

import java.io.IOException;

@Getter
public class HandshakePacket404 extends MinecraftPacket implements HandshakePacket {
    private int protocolVersion;
    private String hostName;
    private int port;
    private HandshakeIntent intent;

    @SuppressWarnings("unused")
    private HandshakePacket404() {
    }

    public HandshakePacket404(int protocolVersion, String hostName, int port, HandshakeIntent intent) {
        this.protocolVersion = protocolVersion;
        this.hostName = hostName;
        this.port = port;
        this.intent = intent;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.protocolVersion = in.readVarInt();
        this.hostName = in.readString();
        this.port = in.readUnsignedShort();
        this.intent = getMagic().key(HandshakeIntent.class, in.readVarInt());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.protocolVersion);
        out.writeString(this.hostName);
        out.writeShort(this.port);
        out.writeVarInt(getMagic().value(Integer.class, this.intent));
    }

    @Override
    public boolean isPriority() {
        return true;
    }
}
