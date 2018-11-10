package misat11.hybrid.permission;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface Abilities {

    void setAbility(Ability ability, boolean value);

    boolean getAbility(Ability ability);
}