package card.character.hero;

import card.character.minion.Minion;
import game.BoardRow;

import java.util.Comparator;

public final class LordRoyce extends Hero {
    public LordRoyce() {
    }

    public LordRoyce(final LordRoyce lordRoyce) {
        super(lordRoyce);
    }

    /**
     * Uses this hero's ability
     */
    public void useAbility(final BoardRow boardRow) throws Exception {
        if (!canUseAbility()) {
            return;
        }

        if (getOwner() == boardRow.getOwner()) {
            throw new Exception("Selected row does not belong to the enemy.");
        }

        boardRow.getRow().stream().max(new Comparator<Minion>() {
            public int compare(final Minion o1, final Minion o2) {
                return o1.getAttackDamage() - o2.getAttackDamage();
            }
        }).get().setFrozen(true);

        setHasAttacked(true);
    }

    /**
     * Create a deep copy of this card
     */
    public LordRoyce copy() {
        return new LordRoyce(this);
    }
}
