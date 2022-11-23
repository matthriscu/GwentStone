package card.environment;

import card.character.minion.Minion;
import game.BoardRow;
import java.util.ArrayList;

public final class Firestorm extends Environment {
    public Firestorm() {
    }

    private Firestorm(final Firestorm firestorm) {
        super(firestorm);
    }

    /**
     * Uses this card's ability
     */
    public void useAbility(final BoardRow boardRow) throws Exception {
        if (boardRow.getOwner() == getOwner()) {
            throw new Exception("Chosen row does not belong to the enemy.");
        }

        for (Minion minion : new ArrayList<>(boardRow.getRow())) {
            minion.setHealth(minion.getHealth() - 1);

            if (minion.getHealth() == 0) {
                minion.destroy();
            }
        }
    }

    /**
     * Create a deep copy of this card
     */
    public Firestorm copy() {
        return new Firestorm(this);
    }
}
