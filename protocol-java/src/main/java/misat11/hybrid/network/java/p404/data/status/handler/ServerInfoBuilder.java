package misat11.hybrid.network.java.p404.data.status.handler;

import com.github.steveice10.packetlib.Session;

import misat11.hybrid.network.java.p404.data.status.ServerStatusInfo;

public interface ServerInfoBuilder {
    public ServerStatusInfo buildInfo(Session session);
}
