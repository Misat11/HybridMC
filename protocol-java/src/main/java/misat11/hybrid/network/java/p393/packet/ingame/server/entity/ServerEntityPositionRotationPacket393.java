package misat11.hybrid.network.java.p393.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityPositionRotationPacket;

public class ServerEntityPositionRotationPacket393 extends ServerEntityMovementPacket393 implements ServerEntityPositionRotationPacket {
    protected ServerEntityPositionRotationPacket393() {
        this.pos = true;
        this.rot = true;
    }

    public ServerEntityPositionRotationPacket393(int entityId, double moveX, double moveY, double moveZ, float yaw, float pitch, boolean onGround) {
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
