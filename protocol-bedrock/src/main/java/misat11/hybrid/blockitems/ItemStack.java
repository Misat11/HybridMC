package misat11.hybrid.blockitems;

import lombok.Data;

@Data
public class ItemStack extends ItemEntry {

	private int amount;
	
	public ItemStack(int id) {
		this(id, 0);
	}
	

	public ItemStack(int id, int data) {
		this(id, data, 1);
	}
	
	public ItemStack(int id, int data, int amount) {
		super(id, data);
		this.amount = amount;
	}
	
}
