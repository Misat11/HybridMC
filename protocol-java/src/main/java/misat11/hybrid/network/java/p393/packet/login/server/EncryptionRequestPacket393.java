package misat11.hybrid.network.java.p393.packet.login.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.login.server.EncryptionRequestPacket;
import misat11.hybrid.network.java.pabstract.util.CryptUtil;

import java.io.IOException;
import java.security.PublicKey;

@Getter
public class EncryptionRequestPacket393 extends MinecraftPacket implements EncryptionRequestPacket {
    private String serverId;
    private PublicKey publicKey;
    private byte verifyToken[];

    @SuppressWarnings("unused")
    private EncryptionRequestPacket393() {
    }

    public EncryptionRequestPacket393(String serverId, PublicKey publicKey, byte verifyToken[]) {
        this.serverId = serverId;
        this.publicKey = publicKey;
        this.verifyToken = verifyToken;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.serverId = in.readString();
        this.publicKey = CryptUtil.decodePublicKey(in.readBytes(in.readVarInt()));
        this.verifyToken = in.readBytes(in.readVarInt());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(this.serverId);
        byte encoded[] = this.publicKey.getEncoded();
        out.writeVarInt(encoded.length);
        out.writeBytes(encoded);
        out.writeVarInt(this.verifyToken.length);
        out.writeBytes(this.verifyToken);
    }

    @Override
    public boolean isPriority() {
        return true;
    }
}
