package card.character.minion;

import lombok.NoArgsConstructor;

import static java.lang.Math.max;

@NoArgsConstructor
public final class Disciple extends Minion {
    public Disciple(final Disciple disciple) {
        super(disciple);
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

        if (minion.getOwner() != getOwner()) {
            throw new Exception("Attacked card does not belong to the current player.");
        }

        setHasAttacked(true);

        minion.setHealth(max(minion.getHealth() + 2, 0));
    }

    /**
     * Creates a deep copy of this card
     */
    public Disciple copy() {
        return new Disciple(this);
    }
}
