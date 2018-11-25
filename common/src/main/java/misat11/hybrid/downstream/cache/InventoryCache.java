package misat11.hybrid.downstream.cache;

import lombok.Data;
import misat11.hybrid.network.bedrock.packet.ContainerClosePacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientCloseWindowPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerOpenWindowPacket404;

@Data
public class InventoryCache {
	private int openedWindowID = -1;
	
	public boolean isWindowOpened() {
		return openedWindowID != -1;
	}
	
	public void closeOpened(HybridSession session, boolean byServer) {
		if (isWindowOpened()) {
			if(byServer) {
				ContainerClosePacket ccp = new ContainerClosePacket();
				ccp.setWindowId((byte) (openedWindowID & 0xFF));
				session.sendImmediatePackage(ccp);
			} else {
				session.getDownstream().send(new ClientCloseWindowPacket404(openedWindowID));
				
			}
			openedWindowID = -1;
				
		}
	}
	
	public void open(HybridSession session, ServerOpenWindowPacket404 packet) {
		// TODO
	}
	
}
