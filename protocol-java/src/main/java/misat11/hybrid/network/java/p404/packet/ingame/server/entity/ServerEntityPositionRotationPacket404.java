package misat11.hybrid.network.java.p404.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityPositionRotationPacket;

public class ServerEntityPositionRotationPacket404 extends ServerEntityMovementPacket404 implements ServerEntityPositionRotationPacket {
    protected ServerEntityPositionRotationPacket404() {
        this.pos = true;
        this.rot = true;
    }

    public ServerEntityPositionRotationPacket404(int entityId, double moveX, double moveY, double moveZ, float yaw, float pitch, boolean onGround) {
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
