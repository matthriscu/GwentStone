package action;

import com.fasterxml.jackson.databind.node.ObjectNode;
import game.Player;

public final class GetPlayerHeroAction extends Action {
    private int playerIdx;

    public GetPlayerHeroAction(final int playerIdx) {
        this.playerIdx = playerIdx;
    }

    /**
     * Gets the corresponding player's hero
     */
    public ObjectNode perform() {
        ObjectNode objectNode = super.perform();
        objectNode.put("playerIdx", playerIdx);
        objectNode.putPOJO("output", Player.getInstance(playerIdx).getHero().copy());
        return objectNode;
    }

    public String getCommand() {
        return "getPlayerHero";
    }
}
