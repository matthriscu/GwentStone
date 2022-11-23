package action;

import com.fasterxml.jackson.databind.node.ObjectNode;
import game.Player;

public final class GetPlayerManaAction extends Action {
    private int playerIdx;

    public GetPlayerManaAction(final int playerIdx) {
        this.playerIdx = playerIdx;
    }

    /**
     * Gets the corresponding player's mana
     */
    public ObjectNode perform() {
        ObjectNode objectNode = super.perform();
        objectNode.put("playerIdx", playerIdx);
        objectNode.put("output", Player.getInstance(playerIdx).getMana());
        return objectNode;
    }

    public String getCommand() {
        return "getPlayerMana";
    }
}
