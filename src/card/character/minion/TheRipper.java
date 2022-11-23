package card.character.minion;

import static java.lang.Math.max;

public final class TheRipper extends Minion {
    public TheRipper() {
    }

    public TheRipper(final TheRipper theRipper) {
        super(theRipper);
    }

    /**
     * Uses this minion's ability
     */
    public void useAbility(final Minion minion) throws Exception {
        if (!canUseAbility()) {
            return;
        }

        if (minion.getOwner() == getOwner()) {
            throw new Exception("Attacked card does not belong to the enemy.");
        }

        if (minion.getOwner().hasTanks() && !minion.isTank()) {
            throw new Exception("Attacked card is not of type 'Tank'.");
        }

        setHasAttacked(true);

        minion.setAttackDamage(max(minion.getAttackDamage() - 2, 0));
    }

    /**
     * Create a deep copy of this card
     */
    public TheRipper copy() {
        return new TheRipper(this);
    }
}
