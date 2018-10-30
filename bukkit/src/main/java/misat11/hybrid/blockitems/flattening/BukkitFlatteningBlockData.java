package misat11.hybrid.blockitems.flattening;

import java.util.List;
import java.util.Map;

public class BukkitFlatteningBlockData implements IFlatteningBlockData {
	
	public BukkitFlatteningBlockData() {
		
	}

	@Override
	public FlattedBlockState fromStateID(int id) {
		return null;
	}

	@Override
	public FlattedBlock fromName(String name) {
		return null;
	}

	@Override
	public FlattedBlockState fromNameDefault(String name) {
		return null;
	}

	@Override
	public List<FlattedBlockState> fromNameProperties(String name, Map<String, String> properties) {
		return null;
	}

}
