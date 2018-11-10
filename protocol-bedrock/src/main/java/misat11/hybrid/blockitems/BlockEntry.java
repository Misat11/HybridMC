package misat11.hybrid.blockitems;

import lombok.Data;

@Data
public class BlockEntry {
	
	private int id;
	private int data;
	private boolean canBeWaterLogged;
	private boolean waterlogged;
	
	public BlockEntry(int id) {
		this(id, 0);
	}

	public BlockEntry(int id, int data) {
		this(id, data, false, false);
	}

	public BlockEntry(int id, int data, boolean canBeWaterLogged, boolean waterlogged) {
		this.id = id;
		this.data = data;
		this.canBeWaterLogged = canBeWaterLogged;
		this.waterlogged = waterlogged;
	}

}
