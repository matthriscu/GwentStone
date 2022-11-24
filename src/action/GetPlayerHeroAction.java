package action;

import com.fasterxml.jackson.databind.node.ObjectNode;
import game.Player;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class GetPlayerHeroAction extends Action {
    private int playerIdx;

    /**
     * Gets the corresponding player's hero
     *
     * @return A json object describing the hero
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
