package misat11.hybrid.network.java.p393.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityRotationPacket;

public class ServerEntityRotationPacket393 extends ServerEntityMovementPacket393 implements ServerEntityRotationPacket {
    protected ServerEntityRotationPacket393() {
        this.rot = true;
    }

    public ServerEntityRotationPacket393(int entityId, float yaw, float pitch, boolean onGround) {
        super(entityId, onGround);
        this.rot = true;
        this.yaw = yaw;
        this.pitch = pitch;
    }
}
