package misat11.hybrid.network.java.p404.packet.ingame.server.entity;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.attribute.Attribute;
import misat11.hybrid.network.java.pabstract.data.game.entity.attribute.AttributeModifier;
import misat11.hybrid.network.java.pabstract.data.game.entity.attribute.AttributeType;
import misat11.hybrid.network.java.pabstract.data.game.entity.attribute.ModifierOperation;
import misat11.hybrid.network.java.pabstract.data.game.entity.attribute.ModifierType;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityPropertiesPacket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class ServerEntityPropertiesPacket404 extends MinecraftPacket implements ServerEntityPropertiesPacket {
    private int entityId;
    private List<Attribute> attributes;

    @SuppressWarnings("unused")
    private ServerEntityPropertiesPacket404() {
    }

    public ServerEntityPropertiesPacket404(int entityId, List<Attribute> attributes) {
        this.entityId = entityId;
        this.attributes = attributes;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.attributes = new ArrayList<Attribute>();
        int length = in.readInt();
        for(int index = 0; index < length; index++) {
            String key = in.readString();
            double value = in.readDouble();
            List<AttributeModifier> modifiers = new ArrayList<AttributeModifier>();
            int len = in.readVarInt();
            for(int ind = 0; ind < len; ind++) {
                modifiers.add(new AttributeModifier(getMagic().key(ModifierType.class, in.readUUID()), in.readDouble(), getMagic().key(ModifierOperation.class, in.readByte())));
            }

            this.attributes.add(new Attribute(getMagic().key(AttributeType.class, key), value, modifiers));
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeInt(this.attributes.size());
        for(Attribute attribute : this.attributes) {
            out.writeString(getMagic().value(String.class, attribute.getType()));
            out.writeDouble(attribute.getValue());
            out.writeVarInt(attribute.getModifiers().size());
            for(AttributeModifier modifier : attribute.getModifiers()) {
                out.writeUUID(getMagic().value(UUID.class, modifier.getType()));
                out.writeDouble(modifier.getAmount());
                out.writeByte(getMagic().value(Integer.class, modifier.getOperation()));
            }
        }
    }
}
