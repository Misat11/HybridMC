package misat11.hybrid.blockitems;

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

	public boolean isWaterlogged() {
		return waterlogged;
	}

	public void setWaterlogged(boolean waterlogged) {
		this.waterlogged = waterlogged;
	}

	public boolean canBeWaterLogged() {
		return canBeWaterLogged;
	}

	public void setCanBeWaterLogged(boolean canBeWaterLogged) {
		this.canBeWaterLogged = canBeWaterLogged;
	}

}
