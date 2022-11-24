package card.environment;

import card.character.minion.Minion;
import game.BoardRow;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class Winterfell extends Environment {
    public Winterfell(final Winterfell winterfell) {
        super(winterfell);
    }

    /**
     * Applies this card's effect to the targeted board row
     *
     * @throws Exception if this card cannot be used for any reason
     */
    public void applyEffect(final BoardRow boardRow) throws Exception {
        if (boardRow.getOwner() == getOwner()) {
            throw new Exception("Chosen row does not belong to the enemy.");
        }

        for (Minion minion : boardRow.getRow()) {
            minion.setFrozen(true);
        }
    }

    /**
     * Creates a deep copy of this card
     */
    public Winterfell copy() {
        return new Winterfell(this);
    }
}
