package misat11.hybrid.blockitems;

public interface IBlockTranslator<PC> {

	public BlockEntry blockPcToPe(int id);

	public BlockEntry blockPcToPe(int id, int data);
	
	public BlockEntry blockPcToPe(PC block);

	public PC blockPeToPc(int id);

	public PC blockPeToPc(int id, int data);
	
	public PC blockPeToPc(BlockEntry block);

}
