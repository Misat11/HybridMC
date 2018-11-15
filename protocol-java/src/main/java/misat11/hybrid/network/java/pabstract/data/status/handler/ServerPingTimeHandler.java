package misat11.hybrid.network.java.pabstract.data.status.handler;

import com.github.steveice10.packetlib.Session;

public interface ServerPingTimeHandler {
    public void handle(Session session, long pingTime);
}
