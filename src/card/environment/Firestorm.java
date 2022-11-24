package card.environment;

import card.character.minion.Minion;
import game.BoardRow;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
public final class Firestorm extends Environment {
    private Firestorm(final Firestorm firestorm) {
        super(firestorm);
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

        for (Minion minion : new ArrayList<>(boardRow.getRow())) {
            minion.setHealth(minion.getHealth() - 1);

            if (minion.getHealth() == 0) {
                minion.destroy();
            }
        }
    }

    /**
     * Creates a deep copy of this card
     */
    public Firestorm copy() {
        return new Firestorm(this);
    }
}
