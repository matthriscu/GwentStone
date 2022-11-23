package card.character.minion;

import static java.lang.Math.max;

public final class Disciple extends Minion {
    public Disciple() {
    }

    public Disciple(final Disciple disciple) {
        super(disciple);
    }

    /**
     * Uses this minion's ability
     */
    public void useAbility(final Minion minion) throws Exception {
        if (!canUseAbility()) {
            return;
        }

        if (minion.getOwner() != getOwner()) {
            throw new Exception("Attacked card does not belong to the current player.");
        }

        setHasAttacked(true);

        minion.setHealth(max(minion.getHealth() + 2, 0));
    }

    /**
     * Create a deep copy of this card
     */
    public Disciple copy() {
        return new Disciple(this);
    }
}
