package misat11.hybrid.blockitems.flattening;

import java.util.List;
import java.util.Map;

public interface IFlatteningBlockData {
	
	public FlattedBlockState fromStateID(int id);
	
	public FlattedBlock fromName(String name);
	
	public FlattedBlockState fromNameDefault(String name);
	
	public List<FlattedBlockState> fromNameProperties(String name, Map<String, String> properties);
}
