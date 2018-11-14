package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerConfirmTransactionPacket;

public class TransactionConfirmTranslator implements IDownstreamTranslator<ServerConfirmTransactionPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerConfirmTransactionPacket packet) {

		return null;
	}

}
