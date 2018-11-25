package misat11.hybrid.network.java.p404.packet.ingame.server.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.GameMode;
import misat11.hybrid.network.java.pabstract.data.game.world.notify.ClientNotification;
import misat11.hybrid.network.java.pabstract.data.game.world.notify.ClientNotificationValue;
import misat11.hybrid.network.java.pabstract.data.game.world.notify.DemoMessageValue;
import misat11.hybrid.network.java.pabstract.data.game.world.notify.EnterCreditsValue;
import misat11.hybrid.network.java.pabstract.data.game.world.notify.RainStrengthValue;
import misat11.hybrid.network.java.pabstract.data.game.world.notify.ThunderStrengthValue;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.world.ServerNotifyClientPacket;

import java.io.IOException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServerNotifyClientPacket404 extends MinecraftPacket implements ServerNotifyClientPacket {
    private ClientNotification notification;
    private ClientNotificationValue value;

    @Override
    public void read(NetInput in) throws IOException {
        this.notification = MagicValues404.key(ClientNotification.class, in.readUnsignedByte());
        float value = in.readFloat();
        if(this.notification == ClientNotification.CHANGE_GAMEMODE) {
            this.value = MagicValues404.key(GameMode.class, (int) value);
        } else if(this.notification == ClientNotification.DEMO_MESSAGE) {
            this.value = MagicValues404.key(DemoMessageValue.class, (int) value);
        } else if(this.notification == ClientNotification.ENTER_CREDITS) {
            this.value = MagicValues404.key(EnterCreditsValue.class, (int) value);
        } else if(this.notification == ClientNotification.RAIN_STRENGTH) {
            this.value = new RainStrengthValue(value);
        } else if(this.notification == ClientNotification.THUNDER_STRENGTH) {
            this.value = new ThunderStrengthValue(value);
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(MagicValues404.value(Integer.class, this.notification));
        float value = 0;
        if(this.value instanceof Enum<?>) {
            value = MagicValues404.value(Integer.class, (Enum<?>) this.value);
        } else if(this.value instanceof RainStrengthValue) {
            value = ((RainStrengthValue) this.value).getStrength();
        } else if(this.value instanceof ThunderStrengthValue) {
            value = ((ThunderStrengthValue) this.value).getStrength();
        }

        out.writeFloat(value);
    }
}
