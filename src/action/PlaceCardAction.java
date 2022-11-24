package action;

import com.fasterxml.jackson.databind.node.ObjectNode;
import game.Game;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class PlaceCardAction extends Action {
    private Game game;
    private int handIdx;

    /**
     * Places a minion from the current player's hand on the table
     *
     * @return A json object describing any errors that have occurred
     */
    public ObjectNode perform() {
        try {
            game.getCurrentPlayer().placeCard(handIdx);
            return null;
        } catch (Exception e) {
            ObjectNode objectNode = super.perform();
            objectNode.put("handIdx", handIdx);
            objectNode.put("error", e.getMessage());

            return objectNode;
        }
    }

    public String getCommand() {
        return "placeCard";
    }
}
