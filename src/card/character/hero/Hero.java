package card.character.hero;

import card.character.Character;
import game.BoardRow;

public abstract class Hero extends Character {
    private static final int HERO_HEALTH = 30;

    protected Hero() {
        setType(Type.HERO);
        setHealth(HERO_HEALTH);
    }

    protected Hero(final Hero hero) {
        super(hero);
    }

    /**
     * Uses this hero's ability on the given BoardRow
     *
     * @throws Exception if the hero cannot attack for any reason
     */
    public abstract void useAbility(BoardRow affectedRow) throws Exception;

    /**
     * Return's whether this hero can use their ability
     */
    public boolean canUseAbility() throws Exception {
        if (isHasAttacked()) {
            throw new Exception("Hero has already attacked this turn.");
        }

        return true;
    }

    /**
     * Resets this hero for the following round by clearing the "hasAttacked" field
     */
    public void reset() {
        setHasAttacked(false);
    }

    /**
     * Creates a deep copy of this card
     */
    public abstract Hero copy();
}
