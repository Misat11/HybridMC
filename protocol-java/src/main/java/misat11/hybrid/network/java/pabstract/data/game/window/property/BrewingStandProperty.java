package misat11.hybrid.network.java.pabstract.data.game.window.property;

/**
 * Window properties of a brewing stand.
 */
public enum BrewingStandProperty implements WindowProperty {
    /**
     * Time remaining for potions to finish brewing.
     * Usually a value between 0 (done) and 400 (just started).
     */
    BREW_TIME,
}