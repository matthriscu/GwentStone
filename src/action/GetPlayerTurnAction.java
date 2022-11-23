package action;

import com.fasterxml.jackson.databind.node.ObjectNode;
import game.Game;

public final class GetPlayerTurnAction extends Action {
    private Game game;

    public GetPlayerTurnAction(final Game game) {
        this.game = game;
    }

    /**
     * Gets the player whose turn it is
     */
    public ObjectNode perform() {
        ObjectNode objectNode = super.perform();
        objectNode.put("output", game.getCurrentPlayer().getId());
        return objectNode;
    }

    public String getCommand() {
        return "getPlayerTurn";
    }
}
