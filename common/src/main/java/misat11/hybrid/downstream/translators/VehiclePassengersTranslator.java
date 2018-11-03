package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntitySetPassengersPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.packet.SetEntityLinkPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

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
