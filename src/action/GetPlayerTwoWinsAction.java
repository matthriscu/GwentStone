package action;

import com.fasterxml.jackson.databind.node.ObjectNode;
import player.Player;

public final class GetPlayerTwoWinsAction extends Action {
    /**
     * Gets the number of games won by player two
     */
    public ObjectNode perform() {
        ObjectNode objectNode = super.perform();
        objectNode.put("output", Player.getInstance(2).getGamesWon());
        return objectNode;
    }

    public String getCommand() {
        return "getPlayerTwoWins";
    }
}
