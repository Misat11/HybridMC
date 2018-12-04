package misat11.hybrid.network.java.p393.packet.ingame.client.player;

import misat11.hybrid.network.java.pabstract.packet.ingame.client.player.ClientPlayerPositionPacket;

public class ClientPlayerPositionPacket393 extends ClientPlayerMovementPacket393 implements ClientPlayerPositionPacket {
    protected ClientPlayerPositionPacket393() {
        this.pos = true;
    }

    public ClientPlayerPositionPacket393(boolean onGround, double x, double y, double z) {
        super(onGround);
        this.pos = true;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
