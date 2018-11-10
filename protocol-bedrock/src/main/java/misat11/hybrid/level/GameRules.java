package misat11.hybrid.level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import misat11.hybrid.level.data.GameRule;

public interface GameRules {

    void setGameRule(@Nonnull GameRule gameRule, boolean value);

    void setGameRule(@Nonnull GameRule gameRule, int value);

    void setGameRule(@Nonnull GameRule gameRule, float value);

    boolean getBoolean(@Nonnull GameRule gameRule);

    int getInteger(@Nonnull GameRule gameRule);

    float getFloat(@Nonnull GameRule gameRule);

    @Nonnull
    String getString(@Nonnull GameRule gameRule);

    boolean contains(@Nullable GameRule gameRule);

    GameRule[] getRules();
}
