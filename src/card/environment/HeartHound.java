package card.environment;

import card.character.minion.Minion;
import game.BoardRow;
import java.util.Comparator;

public final class HeartHound extends Environment {
    public HeartHound() {
    }

    public HeartHound(final HeartHound heartHound) {
        super(heartHound);
    }

    /**
     * Uses this minion's ability
     */
    public void useAbility(final BoardRow boardRow) throws Exception {
        if (boardRow.getOwner() == getOwner()) {
            throw new Exception("Chosen row does not belong to the enemy.");
        }

        BoardRow mirror = getOwner().getBoardRows()[boardRow.getType().ordinal()];

        if (mirror.isFull()) {
            throw new Exception("Cannot steal enemy card since the player's row is full.");
        }

        Minion affectedMinion = boardRow.getRow().stream().max(new Comparator<Minion>() {
            public int compare(final Minion o1, final Minion o2) {
                return o1.getHealth() - o2.getHealth();
            }
        }).get();

        affectedMinion.destroy();
        affectedMinion.setBoardRow(mirror);
        affectedMinion.setOwner(getOwner());
        mirror.getRow().add(affectedMinion);
        if (affectedMinion.isTank()) {
            affectedMinion.getOwner().setTanks(affectedMinion.getOwner().getTanks() + 1);
        }
    }

    /**
     * Create a deep copy of this card
     */
    public HeartHound copy() {
        return new HeartHound(this);
    }
}
