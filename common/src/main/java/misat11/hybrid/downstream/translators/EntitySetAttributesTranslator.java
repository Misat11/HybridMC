package misat11.hybrid.downstream.translators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.entity.Attribute;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.UpdateAttributesPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.pabstract.data.game.entity.attribute.AttributeModifier;
import misat11.hybrid.network.java.pabstract.data.game.entity.attribute.AttributeType;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityPropertiesPacket;

public class EntitySetAttributesTranslator implements IDownstreamTranslator<ServerEntityPropertiesPacket> {

	private static HashMap<AttributeType, AttributeInfo> typeToInfo = new HashMap<>();

	private static class AttributeInfo {
		public final String peName;
		public final float minValue;
		public final float maxValue;
		public final float defaultValue;

		public AttributeInfo(String peName, float minValue, float defaultValue, float maxValue) {
			this.peName = peName;
			this.minValue = minValue;
			this.maxValue = maxValue;
			this.defaultValue = defaultValue;
		}
	}

	static {
		// typeToString.put(AttributeType.GENERIC_MAX_HEALTH, "");
		// typeToString.put(AttributeType.GENERIC_FOLLOW_RANGE, "");
		typeToInfo.put(AttributeType.GENERIC_KNOCKBACK_RESISTANCE,
				new AttributeInfo("minecraft:knockback_resistance", 0, 0, 2080));
		typeToInfo.put(AttributeType.GENERIC_MOVEMENT_SPEED, new AttributeInfo("minecraft:movement", 0, -1, 224791));
		typeToInfo.put(AttributeType.GENERIC_ATTACK_DAMAGE, new AttributeInfo("minecraft:attack_damage", 0, 1, 2));
		// typeToString.put(AttributeType.GENERIC_ATTACK_SPEED, "");
		// typeToString.put(AttributeType.GENERIC_ARMOR, "");
		// typeToString.put(AttributeType.GENERIC_ARMOR_TOUGHNESS, "");
		// typeToString.put(AttributeType.GENERIC_LUCK, "");
		// typeToString.put(AttributeType.GENERIC_FLYING_SPEED, "");
		typeToInfo.put(AttributeType.HORSE_JUMP_STRENGTH,
				new AttributeInfo("minecraft:horse.jump_strength", 0, 0.432084373616155F, 2));
		// typeToString.put(AttributeType.ZOMBIE_SPAWN_REINFORCEMENTS, "");
	}

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityPropertiesPacket packet) {
		List<Attribute> attributes = new ArrayList<>();
		for (misat11.hybrid.network.java.pabstract.data.game.entity.attribute.Attribute attribute : packet
				.getAttributes()) {
			if (typeToInfo.containsKey(attribute.getType())) {
				AttributeInfo info = typeToInfo.get(attribute.getType());
				attributes.add(new Attribute(info.peName, calcAttrValue(attribute.getValue(), attribute.getModifiers()),
						info.minValue, info.maxValue, info.defaultValue));
			}
		}
		return new BedrockPacket[] {
				create(packet.getEntityId(), attributes.toArray(new Attribute[attributes.size()])) };
	}

	protected static float calcAttrValue(double value, List<AttributeModifier> modifiers) {
		double add = 0;
		double mulInc = 1;
		double mulMore = 1;
		for (AttributeModifier modifier : modifiers) {
			switch (modifier.getOperation()) {
			case ADD:
				add += modifier.getAmount();
				break;
			case ADD_MULTIPLIED:
				mulInc += modifier.getAmount();
				break;
			case MULTIPLY:
				mulMore *= (modifier.getAmount() + 1);
				break;
			}
		}
		return (float) ((value + add) * mulInc * mulMore);
	}

	public static UpdateAttributesPacket create(long entityID, Attribute... attributes) {
		UpdateAttributesPacket uap = new UpdateAttributesPacket();
		uap.setRuntimeEntityId(entityID);
		for (Attribute attribute : attributes) {
			uap.getAttributes().add(attribute);
		}
		return uap;
	}

}
