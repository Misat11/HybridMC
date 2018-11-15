package misat11.hybrid.network.java.pabstract.data.game.entity.type.object;

import misat11.hybrid.network.java.pabstract.util.ObjectUtil;

public class GenericObjectData implements ObjectData {
    private int value;

    public GenericObjectData(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof GenericObjectData)) return false;

        GenericObjectData that = (GenericObjectData) o;
        return this.value == that.value;
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(this.value);
    }

    @Override
    public String toString() {
        return ObjectUtil.toString(this);
    }
}
