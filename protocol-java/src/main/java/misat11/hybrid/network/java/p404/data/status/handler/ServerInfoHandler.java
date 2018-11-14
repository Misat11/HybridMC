package misat11.hybrid.network.java.p404.data.status.handler;

import com.github.steveice10.packetlib.Session;

import misat11.hybrid.network.java.p404.data.status.ServerStatusInfo;

public interface ServerInfoHandler {
    public void handle(Session session, ServerStatusInfo info);
}
