package misat11.hybrid.blockitems;

import lombok.Data;

@Data
public class ItemEntry {
	
	private int id;
	private int data;
	
	public ItemEntry(int id) {
		this(id, 0);
	}
	
	public ItemEntry(int id, int data) {
		this.id = id;
		this.data = data;
	}

}
