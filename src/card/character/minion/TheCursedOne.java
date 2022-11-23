package card.character.minion;

public final class TheCursedOne extends Minion {
    public TheCursedOne() {
    }

    public TheCursedOne(final TheCursedOne theCursedOne) {
        super(theCursedOne);
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

        if (minion.getAttackDamage() <= 0) {
            minion.destroy();
        } else {
            int temp = minion.getHealth();
            minion.setHealth(minion.getAttackDamage());
            minion.setAttackDamage(temp);
        }
    }

    /**
     * Create a deep copy of this card
     */
    public TheCursedOne copy() {
        return new TheCursedOne(this);
    }
}
