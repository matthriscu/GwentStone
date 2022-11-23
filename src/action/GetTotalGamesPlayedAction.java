package action;

import com.fasterxml.jackson.databind.node.ObjectNode;
import player.Player;

public final class GetTotalGamesPlayedAction extends Action {
    /**
     * Gets the total number of games played
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
