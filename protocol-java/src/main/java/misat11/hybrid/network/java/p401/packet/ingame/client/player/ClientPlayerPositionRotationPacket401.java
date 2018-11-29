package misat11.hybrid.network.java.p401.packet.ingame.client.player;

import misat11.hybrid.network.java.pabstract.packet.ingame.client.player.ClientPlayerPositionRotationPacket;

public class ClientPlayerPositionRotationPacket401 extends ClientPlayerMovementPacket401 implements ClientPlayerPositionRotationPacket {
    protected ClientPlayerPositionRotationPacket401() {
        this.pos = true;
        this.rot = true;
    }

    public ClientPlayerPositionRotationPacket401(boolean onGround, double x, double y, double z, float yaw, float pitch) {
        super(onGround);
        this.pos = true;
        this.rot = true;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }
}
