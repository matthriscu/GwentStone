package card.character;

import card.Card;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Character extends Card {
    private int health;
    @JsonIgnore
    private boolean hasAttacked;

    protected Character() {
    }

    protected Character(final Character character) {
        super(character);
        setHealth(character.getHealth());
        setHasAttacked(character.getHasAttacked());
    }

    public final int getHealth() {
        return health;
    }

    public final void setHealth(final int health) {
        this.health = health;
    }

    @JsonIgnore
    public final boolean getHasAttacked() {
        return hasAttacked;
    }

    public final void setHasAttacked(final boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    /**
     * @return Whether this minion can use their ability
     */
    public abstract boolean canUseAbility() throws Exception;

    /**
     * Resets this minion for the following round
     */
    public abstract void reset();

    /**
     * Create a deep copy of this card
     */
    public abstract Character copy();
}
