package misat11.hybrid.network.java.p401.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityPositionRotationPacket;

public class ServerEntityPositionRotationPacket401 extends ServerEntityMovementPacket401 implements ServerEntityPositionRotationPacket {
    protected ServerEntityPositionRotationPacket401() {
        this.pos = true;
        this.rot = true;
    }

    public ServerEntityPositionRotationPacket401(int entityId, double moveX, double moveY, double moveZ, float yaw, float pitch, boolean onGround) {
        super(entityId, onGround);
        this.pos = true;
        this.rot = true;
        this.movementX = moveX;
        this.movementY = moveY;
        this.movementZ = moveZ;
        this.yaw = yaw;
        this.pitch = pitch;
    }
}
