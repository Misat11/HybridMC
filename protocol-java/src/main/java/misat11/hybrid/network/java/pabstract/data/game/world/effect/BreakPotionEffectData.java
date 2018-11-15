package misat11.hybrid.network.java.pabstract.data.game.world.effect;

import misat11.hybrid.network.java.pabstract.util.ObjectUtil;

public class BreakPotionEffectData implements WorldEffectData {
    private int potionId;

    public BreakPotionEffectData(int potionId) {
        this.potionId = potionId;
    }

    public int getPotionId() {
        return this.potionId;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof BreakPotionEffectData)) return false;

        BreakPotionEffectData that = (BreakPotionEffectData) o;
        return this.potionId == that.potionId;
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(this.potionId);
    }

    @Override
    public String toString() {
        return ObjectUtil.toString(this);
    }
}
