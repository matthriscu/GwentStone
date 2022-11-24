package card.character.minion;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class Miraj extends Minion {
    public Miraj(final Miraj miraj) {
        super(miraj);
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

        int temp = getHealth();
        setHealth(minion.getHealth());
        minion.setHealth(temp);
    }

    /**
     * Creates a deep copy of this card
     */
    public Minion copy() {
        return new Miraj(this);
    }
}
