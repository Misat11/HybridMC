package misat11.hybrid.network.java.p393.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.window.ClientEnchantItemPacket;

import java.io.IOException;

@Getter
public class ClientEnchantItemPacket393 extends MinecraftPacket implements ClientEnchantItemPacket {
    private int windowId;
    private int enchantment;

    @SuppressWarnings("unused")
    private ClientEnchantItemPacket393() {
    }

    public ClientEnchantItemPacket393(int windowId, int enchantment) {
        this.windowId = windowId;
        this.enchantment = enchantment;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.windowId = in.readByte();
        this.enchantment = in.readByte();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.windowId);
        out.writeByte(this.enchantment);
    }
}
