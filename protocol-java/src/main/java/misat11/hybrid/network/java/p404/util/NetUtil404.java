package misat11.hybrid.network.java.p404.util;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p393.util.NetUtil393;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.ItemStack;
import misat11.hybrid.network.java.pabstract.util.NetUtil;

import java.io.IOException;

public class NetUtil404 extends NetUtil393 {

	public static final NetUtil INSTANCE = new NetUtil404();
	
	public ItemStack readItem(NetInput in) throws IOException {
		boolean present = in.readBoolean();
		if (!present) {
			return null;
		}
		int item = in.readVarInt();
		return new ItemStack(item, in.readByte(), readNBT(in));
	}

	public void writeItem(NetOutput out, ItemStack item) throws IOException {
		out.writeBoolean(item != null);
		if (item != null) {
			out.writeVarInt(item.getId());
			out.writeByte(item.getAmount());
			writeNBT(out, item.getNBT());
		}
	}
}
