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
     * Uses this hero's ability
     */
    public void useAbility(final BoardRow affectedRow) throws Exception {
        throw new Exception("This hero doesn't have a special ability.");
    }

    /**
     * Return's whether this hero can use their ability
     */
    public boolean canUseAbility() throws Exception {
        if (getHasAttacked()) {
            throw new Exception("Hero has already attacked this turn.");
        }

        return true;
    }

    /**
     * Resets this hero for the following round
     */
    public void reset() {
        setHasAttacked(false);
    }

    /**
     * Create a deep copy of this card
     */
    public abstract Hero copy();
}
