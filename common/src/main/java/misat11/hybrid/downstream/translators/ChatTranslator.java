package misat11.hybrid.downstream.translators;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.message.RawMessage;
import misat11.hybrid.message.TipMessage;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.TextPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.pabstract.data.game.MessageType;
import misat11.hybrid.network.java.pabstract.data.message.ChatColor;
import misat11.hybrid.network.java.pabstract.data.message.ChatFormat;
import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.data.message.TranslationMessage;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerChatPacket;

public class ChatTranslator implements IDownstreamTranslator<ServerChatPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerChatPacket packet) {
		TextPacket tp = new TextPacket();
		String message = translate(packet.getMessage());
		if (packet.getType() == MessageType.NOTIFICATION) {
			tp.setMessage(new TipMessage(message, false));
		} else {
			if (packet.getMessage() instanceof TranslationMessage) {
				tp.setMessage(new misat11.hybrid.message.TranslationMessage(
						translationTranslateText((TranslationMessage) packet.getMessage()),
						translationTranslateParams(((TranslationMessage) packet.getMessage()).getTranslationParams())));
			} else {
				tp.setMessage(new RawMessage(message, false));
			}
		}
		tp.setPlatformChatId("");
		tp.setXuid("");
		return new BedrockPacket[] { tp };
	}

	public static String translate(Message message) {
		JsonParser parser = new JsonParser();
		if (isMessage(message.getText())) {
			JsonObject o = parser.parse(message.getText()).getAsJsonObject();
			editJson(o);
			message = Message.fromJson((JsonElement) o);
		}
		StringBuilder build = new StringBuilder(message.getText());
		for (Message msg : message.getExtra()) {
			build.append(toMinecraftColor(msg.getStyle().getColor()));
			build.append(toMinecraftFormat(msg.getStyle().getFormats()));
			if (!(msg.getText() == null))
				build.append(translate(msg));
		}
		return build.toString();
	}

	private static boolean isMessage(String text) {
		JsonParser parser = new JsonParser();
		try {
			JsonObject o = parser.parse(text).getAsJsonObject();
			editJson(o);
			try {
				Message.fromJson((JsonElement) o);
			} catch (Exception e) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private static void editJson(JsonObject o) {
		if (o.has("hoverEvent")) {
			JsonObject sub = (JsonObject) o.get("hoverEvent");
			JsonElement e = sub.get("value");
			if (e instanceof JsonArray) {
				JsonObject newobj = new JsonObject();
				newobj.add("extra", e);
				newobj.addProperty("text", "");
				sub.remove("value");
				sub.add("value", newobj);
			}
		}
		if (o.has("extra")) {
			JsonArray a = o.getAsJsonArray("extra");
			for (int i = 0; i < a.size(); i++)
				if (!(a.get(i) instanceof JsonPrimitive))
					editJson((JsonObject) a.get(i));
		}
	}

	private static String toMinecraftColor(ChatColor color) {
		String base = "\u00a7";
		switch (color) {
		case AQUA:
			base += "b";
			break;
		case BLACK:
			base += "0";
			break;
		case BLUE:
			base += "9";
			break;
		case DARK_AQUA:
			base += "3";
			break;
		case DARK_BLUE:
			base += "1";
			break;
		case DARK_GRAY:
			base += "8";
			break;
		case DARK_GREEN:
			base += "2";
			break;
		case DARK_PURPLE:
			base += "5";
			break;
		case DARK_RED:
			base += "4";
			break;
		case GOLD:
			base += "6";
			break;
		case GRAY:
			base += "7";
			break;
		case GREEN:
			base += "a";
			break;
		case LIGHT_PURPLE:
			base += "d";
			break;
		case RED:
			base += "c";
			break;
		case RESET:
			base += "r";
			break;
		case WHITE:
			base += "f";
			break;
		case YELLOW:
			base += "e";
			break;
		default:
			break;
		}
		return base;
	}

	private static String toMinecraftFormat(List<ChatFormat> formats) {
		String superBase = "";
		for (ChatFormat cf : formats) {
			String base = "\u00a7";
			switch (cf) {
			case BOLD:
				base += "l";
				break;
			case ITALIC:
				base += "o";
				break;
			case OBFUSCATED:
				base += "k";
				break;
			case STRIKETHROUGH:
				base += "m";
				break;
			case UNDERLINED:
				base += "n";
				break;
			default:
				break;
			}
			superBase += base;
		}
		return superBase;
	}

	public static String translationTranslateText(TranslationMessage message) {
		StringBuilder build = new StringBuilder("");
		build.append(toMinecraftColor(message.getStyle().getColor()));
		build.append(toMinecraftFormat(message.getStyle().getFormats()));
		build.append("%");
		build.append(message.getTranslationKey());
		return build.toString();
	}

	public static String[] translationTranslateParams(Message[] messages) {
		ArrayList<String> strings = new ArrayList<>();
		for (int i = 0; i < messages.length; i++) {
			if (messages[i] instanceof TranslationMessage) {
				TranslationMessage tmsg = (TranslationMessage) messages[i];
				StringBuilder build = new StringBuilder("");
				build.append("%");
				build.append(tmsg.getTranslationKey());
				strings.add(build.toString());
				if (tmsg.getTranslationKey().equals("commands.gamemode.success.other"))
					strings.add("");
				for (int j = 0; j < translationTranslateParams(tmsg.getTranslationParams()).length; j++)
					strings.add(translationTranslateParams(tmsg.getTranslationParams())[j]);
			} else {
				StringBuilder build = new StringBuilder("");
				build.append(toMinecraftColor(messages[i].getStyle().getColor()));
				build.append(toMinecraftFormat(messages[i].getStyle().getFormats()));
				build.append(translate(messages[i]));
				strings.add(build.toString());
			}
		}
		String[] stringArray = new String[strings.size()];
		return strings.toArray(stringArray);
	}

}
