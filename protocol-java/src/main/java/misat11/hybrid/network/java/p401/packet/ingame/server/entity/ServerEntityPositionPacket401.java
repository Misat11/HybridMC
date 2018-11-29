package misat11.hybrid.network.java.p401.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityPositionPacket;

public class ServerEntityPositionPacket401 extends ServerEntityMovementPacket401 implements ServerEntityPositionPacket {
    protected ServerEntityPositionPacket401() {
        this.pos = true;
    }

    public ServerEntityPositionPacket401(int entityId, double moveX, double moveY, double moveZ, boolean onGround) {
        super(entityId, onGround);
        this.pos = true;
        this.movementX = moveX;
        this.movementY = moveY;
        this.movementZ = moveZ;
    }
}
