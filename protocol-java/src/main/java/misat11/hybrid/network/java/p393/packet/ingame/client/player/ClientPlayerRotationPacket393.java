package misat11.hybrid.network.java.p393.packet.ingame.client.player;

import misat11.hybrid.network.java.pabstract.packet.ingame.client.player.ClientPlayerRotationPacket;

public class ClientPlayerRotationPacket393 extends ClientPlayerMovementPacket393 implements ClientPlayerRotationPacket {
    protected ClientPlayerRotationPacket393() {
        this.rot = true;
    }

    public ClientPlayerRotationPacket393(boolean onGround, float yaw, float pitch) {
        super(onGround);
        this.rot = true;
        this.yaw = yaw;
        this.pitch = pitch;
    }
}
