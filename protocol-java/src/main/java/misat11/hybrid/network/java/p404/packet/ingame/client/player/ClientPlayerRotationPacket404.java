package misat11.hybrid.network.java.p404.packet.ingame.client.player;

import misat11.hybrid.network.java.pabstract.packet.ingame.client.player.ClientPlayerRotationPacket;

public class ClientPlayerRotationPacket404 extends ClientPlayerMovementPacket404 implements ClientPlayerRotationPacket {
    protected ClientPlayerRotationPacket404() {
        this.rot = true;
    }

    public ClientPlayerRotationPacket404(boolean onGround, float yaw, float pitch) {
        super(onGround);
        this.rot = true;
        this.yaw = yaw;
        this.pitch = pitch;
    }
}
