package action;

import com.fasterxml.jackson.databind.node.ObjectNode;
import game.Player;

public final class GetTotalGamesPlayedAction extends Action {
    /**
     * Gets the total number of games played by both players
     *
     * @return A json object containing the number of games played by both players
     */
    public ObjectNode perform() {
        ObjectNode objectNode = super.perform();
        objectNode.put("output", Player.getInstance(1).getGamesPlayed());
        return objectNode;
    }

    public String getCommand() {
        return "getTotalGamesPlayed";
    }
}
