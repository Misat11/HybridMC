package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import misat11.hybrid.network.java.pabstract.data.game.TitleAction;
import misat11.hybrid.network.java.pabstract.data.message.Message;

public interface ServerTitlePacket {
	public TitleAction getAction();

	public Message getTitle();

	public Message getSubtitle();

	public Message getActionBar();

	public int getFadeIn();

	public int getStay();

	public int getFadeOut();
}
