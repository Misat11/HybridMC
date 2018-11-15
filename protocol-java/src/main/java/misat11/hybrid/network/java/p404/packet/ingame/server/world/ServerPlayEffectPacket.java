package misat11.hybrid.network.java.p404.packet.ingame.server.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.p404.util.NetUtil404;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.world.block.BlockState;
import misat11.hybrid.network.java.pabstract.data.game.world.effect.BonemealGrowEffectData;
import misat11.hybrid.network.java.pabstract.data.game.world.effect.BreakBlockEffectData;
import misat11.hybrid.network.java.pabstract.data.game.world.effect.BreakPotionEffectData;
import misat11.hybrid.network.java.pabstract.data.game.world.effect.ParticleEffect;
import misat11.hybrid.network.java.pabstract.data.game.world.effect.RecordEffectData;
import misat11.hybrid.network.java.pabstract.data.game.world.effect.SmokeEffectData;
import misat11.hybrid.network.java.pabstract.data.game.world.effect.SoundEffect;
import misat11.hybrid.network.java.pabstract.data.game.world.effect.WorldEffect;
import misat11.hybrid.network.java.pabstract.data.game.world.effect.WorldEffectData;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;

import java.io.IOException;

public class ServerPlayEffectPacket extends MinecraftPacket {
    private WorldEffect effect;
    private Position position;
    private WorldEffectData data;
    private boolean broadcast;

    @SuppressWarnings("unused")
    private ServerPlayEffectPacket() {
    }

    public ServerPlayEffectPacket(WorldEffect effect, Position position, WorldEffectData data) {
        this(effect, position, data, false);
    }

    public ServerPlayEffectPacket(WorldEffect effect, Position position, WorldEffectData data, boolean broadcast) {
        this.effect = effect;
        this.position = position;
        this.data = data;
        this.broadcast = broadcast;
    }

    public WorldEffect getEffect() {
        return this.effect;
    }

    public Position getPosition() {
        return this.position;
    }

    public WorldEffectData getData() {
        return this.data;
    }

    public boolean getBroadcast() {
        return this.broadcast;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.effect = MagicValues404.key(WorldEffect.class, in.readInt());
        this.position = NetUtil404.readPosition(in);
        int value = in.readInt();
        if(this.effect == SoundEffect.RECORD) {
            this.data = new RecordEffectData(value);
        } else if(this.effect == ParticleEffect.SMOKE) {
            this.data = MagicValues404.key(SmokeEffectData.class, value % 9);
        } else if(this.effect == ParticleEffect.BREAK_BLOCK) {
            this.data = new BreakBlockEffectData(new BlockState(value));
        } else if(this.effect == ParticleEffect.BREAK_SPLASH_POTION) {
            this.data = new BreakPotionEffectData(value);
        } else if(this.effect == ParticleEffect.BONEMEAL_GROW) {
            this.data = new BonemealGrowEffectData(value);
        }

        this.broadcast = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeInt(MagicValues404.value(Integer.class, this.effect));
        NetUtil404.writePosition(out, this.position);
        int value = 0;
        if(this.data instanceof RecordEffectData) {
            value = ((RecordEffectData) this.data).getRecordId();
        } else if(this.data instanceof SmokeEffectData) {
            value = MagicValues404.value(Integer.class, (SmokeEffectData) this.data);
        } else if(this.data instanceof BreakBlockEffectData) {
            value = ((BreakBlockEffectData) this.data).getBlockState().getId();
        } else if(this.data instanceof BreakPotionEffectData) {
            value = ((BreakPotionEffectData) this.data).getPotionId();
        } else if(this.data instanceof BonemealGrowEffectData) {
            value = ((BonemealGrowEffectData) this.data).getParticleCount();
        }

        out.writeInt(value);
        out.writeBoolean(this.broadcast);
    }
}
