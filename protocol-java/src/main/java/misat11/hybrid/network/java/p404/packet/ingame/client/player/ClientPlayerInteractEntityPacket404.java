package misat11.hybrid.network.java.p404.packet.ingame.client.player;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.Hand;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.InteractAction;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.player.ClientPlayerInteractEntityPacket;

import java.io.IOException;

@Getter
public class ClientPlayerInteractEntityPacket404 extends MinecraftPacket implements ClientPlayerInteractEntityPacket{
    private int entityId;
    private InteractAction action;

    private float targetX;
    private float targetY;
    private float targetZ;
    private Hand hand;

    @SuppressWarnings("unused")
    private ClientPlayerInteractEntityPacket404() {
    }

    public ClientPlayerInteractEntityPacket404(int entityId, InteractAction action) {
        this(entityId, action, Hand.MAIN_HAND);
    }

    public ClientPlayerInteractEntityPacket404(int entityId, InteractAction action, Hand hand) {
        this(entityId, action, 0, 0, 0, hand);
    }

    public ClientPlayerInteractEntityPacket404(int entityId, InteractAction action, float targetX, float targetY, float targetZ, Hand hand) {
        this.entityId = entityId;
        this.action = action;
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
        this.hand = hand;
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
