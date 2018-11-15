package misat11.hybrid.network.java.pabstract.data.game.statistic;

import misat11.hybrid.network.java.pabstract.util.ObjectUtil;

public class BreakItemStatistic implements Statistic {
    private int id;

    public BreakItemStatistic(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof BreakItemStatistic)) return false;

        BreakItemStatistic that = (BreakItemStatistic) o;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(this.id);
    }

    @Override
    public String toString() {
        return ObjectUtil.toString(this);
    }
}
