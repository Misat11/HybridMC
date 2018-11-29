package misat11.hybrid.network.java.p401.packet.login.server;

import com.github.steveice10.mc.auth.data.GameProfile;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.login.server.LoginSuccessPacket;

import java.io.IOException;

@Getter
public class LoginSuccessPacket401 extends MinecraftPacket implements LoginSuccessPacket {
    private GameProfile profile;

    @SuppressWarnings("unused")
    private LoginSuccessPacket401() {
    }

    public LoginSuccessPacket401(GameProfile profile) {
        this.profile = profile;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.profile = new GameProfile(in.readString(), in.readString());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(this.profile.getIdAsString());
        out.writeString(this.profile.getName());
    }

    @Override
    public boolean isPriority() {
        return true;
    }
}
