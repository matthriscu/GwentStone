package action;

import card.character.minion.Minion;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.Coordinates;
import game.Game;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class CardUsesAttackAction extends Action {
    private Game game;
    private Coordinates cardAttacker;
    private Coordinates cardAttacked;

    /**
     * Performs the action of a minion attacking another minion
     *
     * @return A json object describing any errors that have occurred
     */
    public ObjectNode perform() {
        try {
            Minion attacker = game.getMinionAtPosition(cardAttacker);
            Minion attacked = game.getMinionAtPosition(cardAttacked);
            attacker.attackMinion(attacked);

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
