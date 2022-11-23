package action;

import com.fasterxml.jackson.databind.node.ObjectNode;
import player.Player;

public final class GetPlayerOneWinsAction extends Action {
    /**
     * Gets the number of games won by player one
     */
    public ObjectNode perform() {
        ObjectNode objectNode = super.perform();
        objectNode.put("output", Player.getInstance(1).getGamesWon());
        return objectNode;
    }

    public String getCommand() {
        return "getPlayerOneWins";
    }
}
