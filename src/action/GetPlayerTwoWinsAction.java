package action;

import com.fasterxml.jackson.databind.node.ObjectNode;
import game.Player;

public final class GetPlayerTwoWinsAction extends Action {
    /**
     * Gets the number of games won by player one
     *
     * @return A json object containing the number of games won by player one
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
