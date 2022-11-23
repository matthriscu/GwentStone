package card.environment;

import card.character.minion.Minion;
import game.BoardRow;

public final class Winterfell extends Environment {
    public Winterfell() {
    }

    public Winterfell(final Winterfell winterfell) {
        super(winterfell);
    }

    /**
     * Uses this card's ability
     */
    public void useAbility(final BoardRow boardRow) throws Exception {
        if (boardRow.getOwner() == getOwner()) {
            throw new Exception("Chosen row does not belong to the enemy.");
        }

        for (Minion minion : boardRow.getRow()) {
            minion.setFrozen(true);
        }
    }

    /**
     * Create a deep copy of this card
     */
    public Winterfell copy() {
        return new Winterfell(this);
    }
}
