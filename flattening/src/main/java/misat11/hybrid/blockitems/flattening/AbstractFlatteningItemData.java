package misat11.hybrid.blockitems.flattening;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractFlatteningItemData implements IFlatteningItemData {
	
	protected final Map<String, FlattedItem> nameToItem = new HashMap<>();
	protected final Map<Integer, FlattedItem> idToItem = new HashMap<>();

	@Override
	public FlattedItem fromName(String name) {
		return nameToItem.get(name);
	}

	@Override
	public FlattedItem fromID(int id) {
		return idToItem.get(id);
	}

}
