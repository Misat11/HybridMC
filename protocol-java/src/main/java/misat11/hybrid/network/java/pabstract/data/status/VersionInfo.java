package misat11.hybrid.network.java.pabstract.data.status;

import java.util.Objects;

import misat11.hybrid.network.java.pabstract.util.ObjectUtil;

public class VersionInfo {

    private String name;
    private int protocol;

    public VersionInfo(String name, int protocol) {
        this.name = name;
        this.protocol = protocol;
    }

    public String getVersionName() {
        return this.name;
    }

    public int getProtocolVersion() {
        return this.protocol;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof VersionInfo)) return false;

        VersionInfo that = (VersionInfo) o;
        return Objects.equals(this.name, that.name) &&
                this.protocol == that.protocol;
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(this.name, this.protocol);
    }

    @Override
    public String toString() {
        return ObjectUtil.toString(this);
    }
}
