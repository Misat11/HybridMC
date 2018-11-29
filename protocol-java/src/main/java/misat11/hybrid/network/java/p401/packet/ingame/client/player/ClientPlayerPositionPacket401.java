package misat11.hybrid.network.java.p401.packet.ingame.client.player;

import misat11.hybrid.network.java.pabstract.packet.ingame.client.player.ClientPlayerPositionPacket;

public class ClientPlayerPositionPacket401 extends ClientPlayerMovementPacket401 implements ClientPlayerPositionPacket {
    protected ClientPlayerPositionPacket401() {
        this.pos = true;
    }

    public ClientPlayerPositionPacket401(boolean onGround, double x, double y, double z) {
        super(onGround);
        this.pos = true;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
