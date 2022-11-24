package card.character.hero;

import card.character.minion.Minion;
import game.BoardRow;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class KingMudface extends Hero {
    public KingMudface(final KingMudface kingMudface) {
        super(kingMudface);
    }

    /**
     * Uses this hero's ability on the given BoardRow
     *
     * @throws Exception if the hero cannot attack for any reason
     */
    public void useAbility(final BoardRow boardRow) throws Exception {
        if (!canUseAbility()) {
            return;
        }

        if (getOwner() != boardRow.getOwner()) {
            throw new Exception("Selected row does not belong to the current player.");
        }

        for (Minion minion : boardRow.getRow()) {
            minion.setHealth(minion.getHealth() + 1);
        }

        setHasAttacked(true);
    }

    /**
     * Creates a deep copy of this card
     */
    public KingMudface copy() {
        return new KingMudface(this);
    }
}
