package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerPlayerListEntryPacket;
import misat11.hybrid.network.java.pabstract.data.game.PlayerListEntry;

public class PlayerListSetEntryTranslator implements IDownstreamTranslator<ServerPlayerListEntryPacket> {

	// This translator needs fixes
	@Override
	public BedrockPacket[] translate(HybridSession session, ServerPlayerListEntryPacket packet) {
		/*PlayerListPacket plp = new PlayerListPacket();*/
		switch (packet.getAction()) {
		case ADD_PLAYER:
			/*plp.setType(Type.ADD);*/
			for (PlayerListEntry entry : packet.getEntries()) {
				session.getDownstream().getPlayerListEntryCache().put(entry.getProfile().getId(), entry);
				/*try {
					PlayerListPacket.Entry peEntry = new PlayerListPacket.Entry(entry.getProfile().getId());
					peEntry.setEntityId(0); // ?
					peEntry.setName(entry.getProfile().getName());
					peEntry.setPlatformChatId("");
					peEntry.setXuid("");
					peEntry.setSkin(Skin
							.create(ImageIO.read(PlayerListSetEntryTranslator.class.getClassLoader().getResourceAsStream("pe/steve_skin.png"))));
					plp.getEntries().add(peEntry);
				} catch (Exception e) {
					e.printStackTrace();
				}*/
			}
			/*return new BedrockPacket[] {plp};*/
		case REMOVE_PLAYER:
			/*plp.setType(Type.REMOVE);
			for (PlayerListEntry entry : packet.getEntries()) {
				PlayerListPacket.Entry peEntry = new PlayerListPacket.Entry(entry.getProfile().getId());
				plp.getEntries().add(peEntry);
			}
			return new BedrockPacket[] {plp};*/
		case UPDATE_DISPLAY_NAME:
		case UPDATE_GAMEMODE:
		case UPDATE_LATENCY:
			break;
		} 
		return null;
	}

}
