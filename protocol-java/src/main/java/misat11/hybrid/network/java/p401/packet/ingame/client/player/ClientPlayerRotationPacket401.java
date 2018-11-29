package misat11.hybrid.network.java.p401.packet.ingame.client.player;

import misat11.hybrid.network.java.pabstract.packet.ingame.client.player.ClientPlayerRotationPacket;

public class ClientPlayerRotationPacket401 extends ClientPlayerMovementPacket401 implements ClientPlayerRotationPacket {
    protected ClientPlayerRotationPacket401() {
        this.rot = true;
    }

    public ClientPlayerRotationPacket401(boolean onGround, float yaw, float pitch) {
        super(onGround);
        this.rot = true;
        this.yaw = yaw;
        this.pitch = pitch;
    }
}
