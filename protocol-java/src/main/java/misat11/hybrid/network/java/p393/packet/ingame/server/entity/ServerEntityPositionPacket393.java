package misat11.hybrid.network.java.p393.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityPositionPacket;

public class ServerEntityPositionPacket393 extends ServerEntityMovementPacket393 implements ServerEntityPositionPacket {
    protected ServerEntityPositionPacket393() {
        this.pos = true;
    }

    public ServerEntityPositionPacket393(int entityId, double moveX, double moveY, double moveZ, boolean onGround) {
        super(entityId, onGround);
        this.pos = true;
        this.movementX = moveX;
        this.movementY = moveY;
        this.movementZ = moveZ;
    }
}
