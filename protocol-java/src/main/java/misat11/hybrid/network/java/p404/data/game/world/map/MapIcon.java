package misat11.hybrid.network.java.p404.data.game.world.map;

import java.util.Objects;

import misat11.hybrid.network.java.p404.data.message.Message;
import misat11.hybrid.network.java.p404.util.ObjectUtil;

public class MapIcon {
    private int centerX;
    private int centerZ;
    private MapIconType iconType;
    private int iconRotation;
    private Message displayName;

    public MapIcon(int centerX, int centerZ, MapIconType iconType, int iconRotation, Message displayName) {
        this.centerX = centerX;
        this.centerZ = centerZ;
        this.iconType = iconType;
        this.iconRotation = iconRotation;
        this.displayName = displayName;
    }

    public int getCenterX() {
        return this.centerX;
    }

    public int getCenterZ() {
        return this.centerZ;
    }

    public MapIconType getIconType() {
        return this.iconType;
    }

    public int getIconRotation() {
        return this.iconRotation;
    }

    public Message getDisplayName() {
        return this.displayName;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof MapIcon)) return false;

        MapIcon that = (MapIcon) o;
        return this.centerX == that.centerX &&
                this.centerZ == that.centerZ &&
                this.iconType == that.iconType &&
                this.iconRotation == that.iconRotation &&
                Objects.equals(this.displayName, that.displayName);
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(this.centerX, this.centerZ, this.iconType, this.iconRotation, this.displayName);
    }

    @Override
    public String toString() {
        return ObjectUtil.toString(this);
    }
}
