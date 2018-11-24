package misat11.hybrid.network.java.p404.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityRotationPacket;

public class ServerEntityRotationPacket404 extends ServerEntityMovementPacket404 implements ServerEntityRotationPacket {
    protected ServerEntityRotationPacket404() {
        this.rot = true;
    }

    public ServerEntityRotationPacket404(int entityId, float yaw, float pitch, boolean onGround) {
        super(entityId, onGround);
        this.rot = true;
        this.yaw = yaw;
        this.pitch = pitch;
    }
}
