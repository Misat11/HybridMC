package misat11.hybrid.blockitems.flattening;

import java.util.Iterator;

import misat11.hybrid.utils.NMSUtil;
import misat11.hybrid.utils.ReflectionUtil;

public class CraftBukkitFlatteningItemData extends AbstractFlatteningItemData {
	
	public CraftBukkitFlatteningItemData(int protocolVersion) throws Exception {
		Object REGISTRY;
		if (protocolVersion < 401) {
			REGISTRY = ReflectionUtil.getStatic(NMSUtil.nms("Item"), "REGISTRY", Object.class); // before 1.13.1
		} else {
			REGISTRY = ReflectionUtil.getStatic(NMSUtil.nms("IRegistry"), "ITEM", Object.class); // since 1.13.1
		}
		for (Iterator iterator = (Iterator) ReflectionUtil.invoke(Iterable.class, REGISTRY, "iterator"); iterator.hasNext();) {
			Object item = iterator.next();
			Object minecraftKey;
			if (protocolVersion < 401) {
				minecraftKey = REGISTRY.getClass().getDeclaredMethod("b", Object.class).invoke(REGISTRY, item);
			} else {
				minecraftKey = REGISTRY.getClass().getDeclaredMethod("getKey", Object.class).invoke(REGISTRY, item); 
			}
			int id = (int) NMSUtil.nms("Item").getDeclaredMethod("getId", NMSUtil.nms("Item")).invoke(null, item);
			FlattedItem flattedItem = new FlattedItem(id, minecraftKey.toString());
			idToItem.put(flattedItem.id, flattedItem);
			nameToItem.put(flattedItem.name, flattedItem);
		}
	}

}
