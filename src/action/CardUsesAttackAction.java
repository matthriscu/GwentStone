package action;

import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.Coordinates;
import game.Game;

public final class CardUsesAttackAction extends Action {
    private Game game;
    private Coordinates cardAttacker;
    private Coordinates cardAttacked;

    public CardUsesAttackAction(final Game game, final Coordinates cardAttacker,
                                final Coordinates cardAttacked) {
        this.game = game;
        this.cardAttacker = cardAttacker;
        this.cardAttacked = cardAttacked;
    }

    /**
     * Makes the attacker minion attack the attacked minion
     */
    public ObjectNode perform() {
        try {
            game.getMinionAtPosition(cardAttacker)
                    .attackMinion(game.getMinionAtPosition(cardAttacked));
            return null;
        } catch (Exception e) {
            ObjectNode objectNode = super.perform();
            objectNode.putPOJO("cardAttacker", cardAttacker);
            objectNode.putPOJO("cardAttacked", cardAttacked);
            objectNode.putPOJO("error", e.getMessage());
            return objectNode;
        }
    }

    public String getCommand() {
        return "cardUsesAttack";
    }
}
