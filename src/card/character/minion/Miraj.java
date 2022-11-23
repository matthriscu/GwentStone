package card.character.minion;

public final class Miraj extends Minion {
    public Miraj() {
    }

    public Miraj(final Miraj miraj) {
        super(miraj);
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

        int temp = getHealth();
        setHealth(minion.getHealth());
        minion.setHealth(temp);
    }

    /**
     * Create a deep copy of this card
     */
    public Minion copy() {
        return new Miraj(this);
    }
}
