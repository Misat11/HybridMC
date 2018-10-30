package misat11.hybrid.blockitems;

public interface IItemTranslator<PC> {
	
	public ItemEntry itemPcToPe(int id);
	
	public ItemEntry itemPcToPe(int id, int data);
	
	public ItemEntry itemPcToPe(PC item);
	
	public PC itemPeToPc(int id);
	
	public PC itemPeToPc(int id, int data);
	
	public PC itemPeToPc(ItemEntry item);
}
