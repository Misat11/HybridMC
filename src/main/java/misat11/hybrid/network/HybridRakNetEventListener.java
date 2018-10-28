package misat11.hybrid.network;

import java.net.InetSocketAddress;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import com.nukkitx.network.raknet.RakNetServerEventListener;
import com.nukkitx.server.NukkitServer;
import com.nukkitx.server.network.bedrock.BedrockPacketCodec;

import misat11.hybrid.HybridPlugin;

public class HybridRakNetEventListener implements RakNetServerEventListener {

	private static final int RAKNET_PROTOCOL_VERSION = 9;

	@Override
	public Action onConnectionRequest(InetSocketAddress address, int protocolVersion) {
		return protocolVersion == RAKNET_PROTOCOL_VERSION ? Action.CONTINUE : Action.INCOMPATIBLE_PROTOCOL_VERISON;
	}

	@Override
	public Advertisement onQuery(InetSocketAddress address) {
		FileConfiguration config = HybridPlugin.getInstance().getConfigurator().config;
		return new Advertisement("MCPE", config.getString("motd"), BedrockPacketCodec.BROADCAST_PROTOCOL_VERSION,
				NukkitServer.MINECRAFT_VERSION.toString(), Bukkit.getOnlinePlayers().size(), Bukkit.getMaxPlayers(),
				config.getString("submotd"), "Survival");
	}

}
