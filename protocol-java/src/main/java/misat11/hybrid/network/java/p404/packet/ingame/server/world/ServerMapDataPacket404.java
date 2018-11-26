package misat11.hybrid.network.java.p404.packet.ingame.server.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.world.map.MapData;
import misat11.hybrid.network.java.pabstract.data.game.world.map.MapIcon;
import misat11.hybrid.network.java.pabstract.data.game.world.map.MapIconType;
import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.world.ServerMapDataPacket;

import java.io.IOException;

@Getter
public class ServerMapDataPacket404 extends MinecraftPacket implements ServerMapDataPacket {
    private int mapId;
    private byte scale;
    private boolean trackingPosition;
    private MapIcon icons[];

    private MapData data;

    @SuppressWarnings("unused")
    private ServerMapDataPacket404() {
    }

    public ServerMapDataPacket404(int mapId, byte scale, boolean trackingPosition, MapIcon icons[]) {
        this(mapId, scale, trackingPosition, icons, null);
    }

    public ServerMapDataPacket404(int mapId, byte scale, boolean trackingPosition, MapIcon icons[], MapData data) {
        this.mapId = mapId;
        this.scale = scale;
        this.trackingPosition = trackingPosition;
        this.icons = icons;
        this.data = data;
    }
    
    public boolean getTrackingPosition() {
    	return this.trackingPosition;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.mapId = in.readVarInt();
        this.scale = in.readByte();
        this.trackingPosition = in.readBoolean();
        this.icons = new MapIcon[in.readVarInt()];
        for(int index = 0; index < this.icons.length; index++) {
            int type = in.readVarInt();
            int x = in.readUnsignedByte();
            int z = in.readUnsignedByte();
            int rotation = in.readUnsignedByte();
            Message displayName = null;
            if (in.readBoolean()) {
                displayName = Message.fromString(in.readString());
            }
            this.icons[index] = new MapIcon(x, z, getMagic().key(MapIconType.class, type), rotation, displayName);
        }

        int columns = in.readUnsignedByte();
        if(columns > 0) {
            int rows = in.readUnsignedByte();
            int x = in.readUnsignedByte();
            int y = in.readUnsignedByte();
            byte data[] = in.readBytes(in.readVarInt());
            this.data = new MapData(columns, rows, x, y, data);
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.mapId);
        out.writeByte(this.scale);
        out.writeBoolean(this.trackingPosition);
        out.writeVarInt(this.icons.length);
        for(int index = 0; index < this.icons.length; index++) {
            MapIcon icon = this.icons[index];
            int type = getMagic().value(Integer.class, icon.getIconType());
            out.writeVarInt(type);
            out.writeByte(icon.getCenterX());
            out.writeByte(icon.getCenterZ());
            out.writeByte(icon.getIconRotation());
            if (icon.getDisplayName() != null) {
                out.writeBoolean(false);
                out.writeString(icon.getDisplayName().toJsonString());
            } else {
                out.writeBoolean(true);
            }
        }

        if(this.data != null && this.data.getColumns() != 0) {
            out.writeByte(this.data.getColumns());
            out.writeByte(this.data.getRows());
            out.writeByte(this.data.getX());
            out.writeByte(this.data.getY());
            out.writeVarInt(this.data.getData().length);
            out.writeBytes(this.data.getData());
        } else {
            out.writeByte(0);
        }
    }
}
