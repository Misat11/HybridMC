package misat11.hybrid.network.java.p401.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityRotationPacket;

public class ServerEntityRotationPacket401 extends ServerEntityMovementPacket401 implements ServerEntityRotationPacket {
    protected ServerEntityRotationPacket401() {
        this.rot = true;
    }

    public ServerEntityRotationPacket401(int entityId, float yaw, float pitch, boolean onGround) {
        super(entityId, onGround);
        this.rot = true;
        this.yaw = yaw;
        this.pitch = pitch;
    }
}
