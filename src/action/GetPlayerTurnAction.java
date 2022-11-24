package action;

import com.fasterxml.jackson.databind.node.ObjectNode;
import game.Game;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class GetPlayerTurnAction extends Action {
    private Game game;

    /**
     * Gets the player whose turn it is
     *
     * @return A json object containing the current player's ID
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
