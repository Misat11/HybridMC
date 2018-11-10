package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.window.ServerConfirmTransactionPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class TransactionConfirmTranslator implements IDownstreamTranslator<ServerConfirmTransactionPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerConfirmTransactionPacket packet) {

		return null;
	}

}
