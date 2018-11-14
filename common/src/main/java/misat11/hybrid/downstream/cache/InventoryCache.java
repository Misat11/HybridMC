package misat11.hybrid.downstream.cache;

import lombok.Data;
import misat11.hybrid.network.bedrock.packet.ContainerClosePacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientCloseWindowPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerOpenWindowPacket;

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
				session.getDownstream().send(new ClientCloseWindowPacket(openedWindowID));
				
			}
			openedWindowID = -1;
				
		}
	}
	
	public void open(HybridSession session, ServerOpenWindowPacket packet) {
		// TODO
	}
	
}
