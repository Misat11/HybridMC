package misat11.hybrid.network.java.p393.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerDeclareTagsPacket;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public class ServerDeclareTagsPacket393 extends MinecraftPacket implements ServerDeclareTagsPacket {
    private Map<String, int[]> blockTags;
    private Map<String, int[]> itemTags;
    private Map<String, int[]> fluidTags;

    @SuppressWarnings("unused")
    private ServerDeclareTagsPacket393() {
    }

    public ServerDeclareTagsPacket393(Map<String, int[]> blockTags, Map<String, int[]> itemTags, Map<String, int[]> fluidTags) {
        this.blockTags = blockTags;
        this.itemTags = itemTags;
        this.fluidTags = fluidTags;
    }

    @Override
    public void read(NetInput in) throws IOException {
        blockTags = new HashMap<>();
        itemTags = new HashMap<>();
        fluidTags = new HashMap<>();
        for(Map<String, int[]> tags : Arrays.asList(blockTags, itemTags, fluidTags)) {
            int tagsCount = in.readVarInt();
            for(int i = 0; i < tagsCount; i++) {
                String name = in.readString();
                int entriesCount = in.readVarInt();
                int[] entries = new int[entriesCount];
                for(int index = 0; index < entriesCount; index++) {
                    entries[index] = in.readVarInt();
                }
                tags.put(name, entries);
            }
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        for(Map<String, int[]> tags : Arrays.asList(blockTags, itemTags, fluidTags)) {
            out.writeVarInt(tags.size());
            for (Map.Entry<String, int[]> tag : tags.entrySet()) {
                out.writeString(tag.getKey());
                out.writeVarInt(tag.getValue().length);
                for (int id : tag.getValue()) {
                    out.writeVarInt(id);
                }
            }
        }
    }
}
