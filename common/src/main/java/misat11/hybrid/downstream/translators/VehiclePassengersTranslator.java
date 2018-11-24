package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.SetEntityLinkPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntitySetPassengersPacket404;

public class VehiclePassengersTranslator implements IDownstreamTranslator<ServerEntitySetPassengersPacket404>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntitySetPassengersPacket404 packet) {
		// Not implemented yet in NukkitX
		return null;
	}
	
	public static SetEntityLinkPacket create(long eid, long linkEid) {
		SetEntityLinkPacket selp = new SetEntityLinkPacket();
		
		// Not implemented yet in NukkitX
		return selp;
	}

}
