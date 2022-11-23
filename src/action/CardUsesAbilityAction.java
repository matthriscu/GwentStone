package action;

import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.Coordinates;
import game.Game;

public final class CardUsesAbilityAction extends Action {
    private Game game;
    private Coordinates cardAttacker;
    private Coordinates cardAttacked;

    public CardUsesAbilityAction(final Game game, final Coordinates cardAttacker,
                                 final Coordinates cardAttacked) {
        this.game = game;
        this.cardAttacker = cardAttacker;
        this.cardAttacked = cardAttacked;
    }

    /**
     * Use the attacking minion's ability on the attacked minion
     */
    public ObjectNode perform() {
        try {
            game.getMinionAtPosition(cardAttacker)
                    .useAbility(game.getMinionAtPosition(cardAttacked));
            return null;
        } catch (Exception e) {
            ObjectNode objectNode = super.perform();
            objectNode.putPOJO("cardAttacker", cardAttacker);
            objectNode.putPOJO("cardAttacked", cardAttacked);
            objectNode.put("error", e.getMessage());
            return objectNode;
        }
    }

    public String getCommand() {
        return "cardUsesAbility";
    }
}
