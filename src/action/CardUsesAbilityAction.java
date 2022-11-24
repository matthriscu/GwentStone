package action;

import card.character.minion.Minion;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.Coordinates;
import game.Game;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class CardUsesAbilityAction extends Action {
    private Game game;
    private Coordinates cardAttacker;
    private Coordinates cardAttacked;

    /**
     * Uses one of the current player's minion abilities
     *
     * @return A json object describing any errors that have occurred
     */
    public ObjectNode perform() {
        try {
            Minion attacker = game.getMinionAtPosition(cardAttacker);
            Minion attacked = game.getMinionAtPosition(cardAttacked);
            attacker.useAbility(attacked);

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
