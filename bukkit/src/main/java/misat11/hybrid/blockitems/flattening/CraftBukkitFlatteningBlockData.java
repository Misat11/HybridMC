package misat11.hybrid.blockitems.flattening;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;

import misat11.hybrid.utils.NMSUtil;
import misat11.hybrid.utils.ReflectionUtil;

public class CraftBukkitFlatteningBlockData extends AbstractFlatteningBlockData {

	public CraftBukkitFlatteningBlockData() throws Exception {
		Object REGISTRY = ReflectionUtil.getStatic(NMSUtil.nms("IRegistry"), "BLOCK", Object.class); // since 1.13.1
		for (Iterator iterator = (Iterator) ReflectionUtil.invoke(Iterable.class, REGISTRY, "iterator"); iterator.hasNext();) {
			Object block = iterator.next();
			Object minecraftKey = REGISTRY.getClass().getDeclaredMethod("getKey", Object.class).invoke(REGISTRY, block); // b -> getKey since 1.13.1
			List<FlattedBlockState> states = new ArrayList<FlattedBlockState>();
			FlattedBlock flattedBlock = new FlattedBlock(minecraftKey.toString(), states);
			Object blockStateList = ReflectionUtil.invoke(NMSUtil.nms("Block"), block, "getStates");
			ImmutableList blockStateListA = (ImmutableList) ReflectionUtil.invoke(blockStateList, "a");
			ImmutableList blockStateListD = (ImmutableList) ReflectionUtil.invoke(blockStateList, "d");
			for (Iterator blockStateListAIterator = blockStateListA.iterator(); blockStateListAIterator.hasNext();) {
				Object stateData = blockStateListAIterator.next();
				int combinedId = (int) NMSUtil.nms("Block").getDeclaredMethod("getCombinedId", NMSUtil.nms("IBlockData"))
						.invoke(null, stateData);
				Map<String, String> properties = new HashMap<>();
				for (Iterator blockStateListDIterator = blockStateListD.iterator(); blockStateListDIterator
						.hasNext();) {
					Object property = blockStateListDIterator.next();
					properties.put((String) ReflectionUtil.invoke(NMSUtil.nms("IBlockState"), property, "a"),
							(String) NMSUtil.nms("SystemUtils")
									.getDeclaredMethod("a", NMSUtil.nms("IBlockState"), Object.class).invoke(null, property,
											NMSUtil.nms("IBlockDataHolder").getDeclaredMethod("get", NMSUtil.nms("IBlockState"))
													.invoke(stateData, property)));
				}
				FlattedBlockState fbs = new FlattedBlockState(combinedId, flattedBlock, properties);
				flattedBlockStates.put(fbs.id, fbs);
				states.add(fbs);
				if (stateData == ReflectionUtil.invoke(NMSUtil.nms("Block"), block, "getBlockData")) {
					flattedBlock.defaultState = fbs;
				}
			}
        	stringFlattedBlocks.put(minecraftKey.toString(), flattedBlock);
		}
	}

}
