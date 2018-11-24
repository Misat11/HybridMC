package misat11.hybrid.network.java.p404.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityPositionPacket;

public class ServerEntityPositionPacket404 extends ServerEntityMovementPacket404 implements ServerEntityPositionPacket {
    protected ServerEntityPositionPacket404() {
        this.pos = true;
    }

    public ServerEntityPositionPacket404(int entityId, double moveX, double moveY, double moveZ, boolean onGround) {
        super(entityId, onGround);
        this.pos = true;
        this.movementX = moveX;
        this.movementY = moveY;
        this.movementZ = moveZ;
    }
}
