package action;

import com.fasterxml.jackson.databind.node.ObjectNode;
import game.Game;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class UseEnvironmentCardAction extends Action {
    private Game game;
    private int handIdx;
    private int affectedRow;

    /**
     * Uses an environment card from the current player's hand
     *
     * @return A json object describing any errors that have occurred
     */
    public ObjectNode perform() {
        try {
            game.getCurrentPlayer().useEnvironmentCard(handIdx, game.getRowMap()[affectedRow]);
            return null;
        } catch (Exception e) {
            ObjectNode objectNode = super.perform();
            objectNode.put("handIdx", handIdx);
            objectNode.put("affectedRow", affectedRow);
            objectNode.put("error", e.getMessage());

            return objectNode;
        }
    }

    public String getCommand() {
        return "useEnvironmentCard";
    }
}
