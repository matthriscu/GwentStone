package action;

import com.fasterxml.jackson.databind.node.ObjectNode;
import game.Game;

public final class PlaceCardAction extends Action {
    private Game game;
    private int handIdx;

    public PlaceCardAction(final Game game, final int handIdx) {
        this.game = game;
        this.handIdx = handIdx;
    }

    /**
     * Places a card from the current player's hand
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
