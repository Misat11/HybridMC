package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.SetEntityLinkPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntitySetPassengersPacket;

public class VehiclePassengersTranslator implements IDownstreamTranslator<ServerEntitySetPassengersPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntitySetPassengersPacket packet) {
		// Not implemented yet in NukkitX
		return null;
	}
	
	public static SetEntityLinkPacket create(long eid, long linkEid) {
		SetEntityLinkPacket selp = new SetEntityLinkPacket();
		
		// Not implemented yet in NukkitX
		return selp;
	}

}
