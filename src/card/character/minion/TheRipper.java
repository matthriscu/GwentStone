package card.character.minion;

import lombok.NoArgsConstructor;

import static java.lang.Math.max;

@NoArgsConstructor
public final class TheRipper extends Minion {
    public TheRipper(final TheRipper theRipper) {
        super(theRipper);
    }

    /**
     * Uses this minion's ability on another minion
     *
     * @throws Exception if this minion can't use its ability for any reason
     */
    public void useAbility(final Minion minion) throws Exception {
        if (!canUseAbility()) {
            return;
        }

        if (minion.getOwner() == getOwner()) {
            throw new Exception("Attacked card does not belong to the enemy.");
        }

        if (minion.getOwner().getTanks() > 0 && !minion.isTank()) {
            throw new Exception("Attacked card is not of type 'Tank'.");
        }

        setHasAttacked(true);

        minion.setAttackDamage(max(minion.getAttackDamage() - 2, 0));
    }

    /**
     * Creates a deep copy of this card
     */
    public TheRipper copy() {
        return new TheRipper(this);
    }
}
