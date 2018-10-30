package misat11.hybrid.blockitems.flattening;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;

import misat11.hybrid.utils.NMSUtil;
import misat11.hybrid.utils.ReflectionUtil;

public class BukkitFlatteningBlockData implements IFlatteningBlockData {
	
	private final HashMap<Integer, FlattedBlockState> flattedBlockStates = new HashMap<Integer, FlattedBlockState>();
	private final HashMap<String, FlattedBlock> stringFlattedBlocks = new HashMap<String, FlattedBlock>();

	public BukkitFlatteningBlockData() throws Exception {
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

	@Override
	public FlattedBlockState fromStateID(int id) {
		return flattedBlockStates.get(id);
	}

	@Override
	public FlattedBlock fromName(String name) {
		return stringFlattedBlocks.get(name);
	}

	@Override
	public FlattedBlockState fromNameDefault(String name) {
		return stringFlattedBlocks.get(name).defaultState;
	}

	@Override
	public List<FlattedBlockState> fromNameProperties(String name, Map<String, String> properties) {
		List<FlattedBlockState> result = new ArrayList<FlattedBlockState>();
		List<FlattedBlockState> list = stringFlattedBlocks.get(name).states;
		for (FlattedBlockState state : list) {
			if (state.properties != null) {
				boolean success = true;
				for (Map.Entry<String, String> entry : properties.entrySet()) {
					success = state.properties.containsKey(entry.getKey());
					if (!success) {
						break;
					}
					success = state.properties.get(entry.getKey()).equals(entry.getValue());
					if (!success) {
						break;
					}
				}
				if (success) {
					result.add(state);
				}
			}
		}
		return result;
	}

}
