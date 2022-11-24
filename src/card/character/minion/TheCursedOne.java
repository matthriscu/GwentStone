package card.character.minion;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class TheCursedOne extends Minion {
    public TheCursedOne(final TheCursedOne theCursedOne) {
        super(theCursedOne);
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

        if (minion.getAttackDamage() <= 0) {
            minion.destroy();
        } else {
            int temp = minion.getHealth();
            minion.setHealth(minion.getAttackDamage());
            minion.setAttackDamage(temp);
        }
    }

    /**
     * Creates a deep copy of this card
     */
    public TheCursedOne copy() {
        return new TheCursedOne(this);
    }
}
