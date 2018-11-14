package misat11.hybrid.network.java.p404.data.game.world.particle;

import java.util.Objects;

import misat11.hybrid.network.java.p404.util.ObjectUtil;

public class Particle {
    private final ParticleType type;
    private final ParticleData data;

    public Particle(ParticleType type, ParticleData data) {
        this.type = type;
        this.data = data;
    }

    public ParticleType getType() {
        return this.type;
    }

    public ParticleData getData() {
        return this.data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof Particle)) return false;

        Particle that = (Particle) o;
        return Objects.equals(this.type, that.type)
                && Objects.equals(this.data, that.data);
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(this.type, this.data);
    }

    @Override
    public String toString() {
        return ObjectUtil.toString(this);
    }
}
