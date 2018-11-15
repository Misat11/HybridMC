package misat11.hybrid.network.java.p404.packet.ingame.server.scoreboard;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.pabstract.data.game.scoreboard.ObjectiveAction;
import misat11.hybrid.network.java.pabstract.data.game.scoreboard.ScoreType;
import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;

import java.io.IOException;

public class ServerScoreboardObjectivePacket extends MinecraftPacket {
    private String name;
    private ObjectiveAction action;
    private Message displayName;
    private ScoreType type;

    @SuppressWarnings("unused")
    private ServerScoreboardObjectivePacket() {
    }

    public ServerScoreboardObjectivePacket(String name) {
        this.name = name;
        this.action = ObjectiveAction.REMOVE;
    }

    public ServerScoreboardObjectivePacket(String name, ObjectiveAction action, Message displayName, ScoreType type) {
        if(action != ObjectiveAction.ADD && action != ObjectiveAction.UPDATE) {
            throw new IllegalArgumentException("(name, action, displayName) constructor only valid for adding and updating objectives.");
        }

        this.name = name;
        this.action = action;
        this.displayName = displayName;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public ObjectiveAction getAction() {
        return this.action;
    }

    public Message getDisplayName() {
        return this.displayName;
    }

    public ScoreType getType() {
        return this.type;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.name = in.readString();
        this.action = MagicValues404.key(ObjectiveAction.class, in.readByte());
        if(this.action == ObjectiveAction.ADD || this.action == ObjectiveAction.UPDATE) {
            this.displayName = Message.fromString(in.readString());
            this.type = MagicValues404.key(ScoreType.class, in.readVarInt());
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(this.name);
        out.writeByte(MagicValues404.value(Integer.class, this.action));
        if(this.action == ObjectiveAction.ADD || this.action == ObjectiveAction.UPDATE) {
            out.writeString(this.displayName.toJsonString());
            out.writeVarInt(MagicValues404.value(Integer.class, this.type));
        }
    }
}
