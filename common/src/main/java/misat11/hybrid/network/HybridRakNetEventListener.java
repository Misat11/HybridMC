package misat11.hybrid.network;

import java.net.InetSocketAddress;

import com.nukkitx.network.raknet.RakNetServerEventListener;
import com.nukkitx.server.NukkitServer;
import com.nukkitx.server.network.bedrock.BedrockPacketCodec;

import misat11.hybrid.Platform;

public class HybridRakNetEventListener implements RakNetServerEventListener {

	private static final int RAKNET_PROTOCOL_VERSION = 9;

	@Override
	public Action onConnectionRequest(InetSocketAddress address, int protocolVersion) {
		return protocolVersion == RAKNET_PROTOCOL_VERSION ? Action.CONTINUE : Action.INCOMPATIBLE_PROTOCOL_VERISON;
	}

	@Override
	public Advertisement onQuery(InetSocketAddress address) {
		return new Advertisement("MCPE", // GAME
				Platform.getMotd(), // Motd
				BedrockPacketCodec.BROADCAST_PROTOCOL_VERSION, // Minecraft Bedrock Edition protocol
				NukkitServer.MINECRAFT_VERSION.toString(), // Minecraft Bedrock Edition version
				Platform.getOnlinePlayers(), // Online Players
				Platform.getMaxPlayers(), // Max Players
				Platform.getSubmotd(), // Submotd
				"Survival" // Default gamemode
		);
	}

}
