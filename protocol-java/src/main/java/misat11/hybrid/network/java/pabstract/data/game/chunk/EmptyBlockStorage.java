package misat11.hybrid.network.java.pabstract.data.game.chunk;

import java.io.IOException;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.pabstract.util.NetUtil;

public class EmptyBlockStorage extends BlockStorage {

	@Override
	public void write(NetOutput out, NetUtil util) throws IOException {
	}

	@Override
	public void read(NetInput in, NetUtil util) throws IOException {
	}

}
