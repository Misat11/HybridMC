package misat11.hybrid.network.java.p404.packet.ingame.client.player;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.Hand;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.InteractAction;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;

import java.io.IOException;

public class ClientPlayerInteractEntityPacket extends MinecraftPacket {
    private int entityId;
    private InteractAction action;

    private float targetX;
    private float targetY;
    private float targetZ;
    private Hand hand;

    @SuppressWarnings("unused")
    private ClientPlayerInteractEntityPacket() {
    }

    public ClientPlayerInteractEntityPacket(int entityId, InteractAction action) {
        this(entityId, action, Hand.MAIN_HAND);
    }

    public ClientPlayerInteractEntityPacket(int entityId, InteractAction action, Hand hand) {
        this(entityId, action, 0, 0, 0, hand);
    }

    public ClientPlayerInteractEntityPacket(int entityId, InteractAction action, float targetX, float targetY, float targetZ, Hand hand) {
        this.entityId = entityId;
        this.action = action;
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
        this.hand = hand;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public InteractAction getAction() {
        return this.action;
    }

    public float getTargetX() {
        return this.targetX;
    }

    public float getTargetY() {
        return this.targetY;
    }

    public float getTargetZ() {
        return this.targetZ;
    }

    public Hand getHand() {
        return this.hand;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.action = MagicValues404.key(InteractAction.class, in.readVarInt());
        if(this.action == InteractAction.INTERACT_AT) {
            this.targetX = in.readFloat();
            this.targetY = in.readFloat();
            this.targetZ = in.readFloat();
        }

        if(this.action == InteractAction.INTERACT || this.action == InteractAction.INTERACT_AT) {
            this.hand = MagicValues404.key(Hand.class, in.readVarInt());
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeVarInt(MagicValues404.value(Integer.class, this.action));
        if(this.action == InteractAction.INTERACT_AT) {
            out.writeFloat(this.targetX);
            out.writeFloat(this.targetY);
            out.writeFloat(this.targetZ);
        }

        if(this.action == InteractAction.INTERACT || this.action == InteractAction.INTERACT_AT) {
            out.writeVarInt(MagicValues404.value(Integer.class, this.hand));
        }
    }
}
