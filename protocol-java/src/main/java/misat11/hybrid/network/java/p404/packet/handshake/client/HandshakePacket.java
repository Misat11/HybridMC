package misat11.hybrid.network.java.p404.packet.handshake.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.pabstract.data.handshake.HandshakeIntent;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;

import java.io.IOException;

public class HandshakePacket extends MinecraftPacket {
    private int protocolVersion;
    private String hostname;
    private int port;
    private HandshakeIntent intent;

    @SuppressWarnings("unused")
    private HandshakePacket() {
    }

    public HandshakePacket(int protocolVersion, String hostname, int port, HandshakeIntent intent) {
        this.protocolVersion = protocolVersion;
        this.hostname = hostname;
        this.port = port;
        this.intent = intent;
    }

    public int getProtocolVersion() {
        return this.protocolVersion;
    }

    public String getHostName() {
        return this.hostname;
    }

    public int getPort() {
        return this.port;
    }

    public HandshakeIntent getIntent() {
        return this.intent;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.protocolVersion = in.readVarInt();
        this.hostname = in.readString();
        this.port = in.readUnsignedShort();
        this.intent = MagicValues404.key(HandshakeIntent.class, in.readVarInt());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.protocolVersion);
        out.writeString(this.hostname);
        out.writeShort(this.port);
        out.writeVarInt(MagicValues404.value(Integer.class, this.intent));
    }

    @Override
    public boolean isPriority() {
        return true;
    }
}
