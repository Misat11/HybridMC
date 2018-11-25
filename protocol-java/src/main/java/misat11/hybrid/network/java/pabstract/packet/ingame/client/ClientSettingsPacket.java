package misat11.hybrid.network.java.pabstract.packet.ingame.client;

import java.util.List;

import misat11.hybrid.network.java.pabstract.data.game.entity.player.Hand;
import misat11.hybrid.network.java.pabstract.data.game.setting.ChatVisibility;
import misat11.hybrid.network.java.pabstract.data.game.setting.SkinPart;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientSettingsPacket extends IMinecraftPacket {

	public String getLocale();

	public int getRenderDistance();

	public ChatVisibility getChatVisibility();

	public boolean isUsedChatColors();

	public List<SkinPart> getVisibleParts();

	public Hand getMainHand();
}
