package misat11.hybrid.network.java.p404.packet.ingame.client.player;

import misat11.hybrid.network.java.pabstract.packet.ingame.client.player.ClientPlayerPositionPacket;

public class ClientPlayerPositionPacket404 extends ClientPlayerMovementPacket404 implements ClientPlayerPositionPacket {
    protected ClientPlayerPositionPacket404() {
        this.pos = true;
    }

    public ClientPlayerPositionPacket404(boolean onGround, double x, double y, double z) {
        super(onGround);
        this.pos = true;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
