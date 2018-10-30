package misat11.hybrid.blockitems;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

}
