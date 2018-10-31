package misat11.hybrid.blockitems.flattening;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractFlatteningBlockData implements IFlatteningBlockData {
	
	protected final HashMap<Integer, FlattedBlockState> flattedBlockStates = new HashMap<Integer, FlattedBlockState>();
	protected final HashMap<String, FlattedBlock> stringFlattedBlocks = new HashMap<String, FlattedBlock>();

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
