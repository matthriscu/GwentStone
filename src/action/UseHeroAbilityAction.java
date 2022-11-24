package action;

import com.fasterxml.jackson.databind.node.ObjectNode;
import game.Game;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class UseHeroAbilityAction extends Action {
    private Game game;
    private int affectedRow;

    /**
     * Uses the current player's hero's ability
     *
     * @return A json object describing any errors that have occurred
     */
    public ObjectNode perform() {
        try {
            game.getCurrentPlayer().useHeroAbility(game.getRowMap()[affectedRow]);
            return null;
        } catch (Exception e) {
            ObjectNode objectNode = super.perform();
            objectNode.put("affectedRow", affectedRow);
            objectNode.put("error", e.getMessage());

            return objectNode;
        }
    }

    public String getCommand() {
        return "useHeroAbility";
    }
}
