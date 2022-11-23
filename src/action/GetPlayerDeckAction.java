package action;

import card.Card;
import com.fasterxml.jackson.databind.node.ObjectNode;
import player.Player;
import java.util.ArrayList;
import java.util.List;

public final class GetPlayerDeckAction extends Action {
    private int playerIdx;
    private List<Card> deck;

    public GetPlayerDeckAction(final int playerIdx) {
        this.playerIdx = playerIdx;
    }

    /**
     * Gets the corresponding player's deck
     * @return
     */
    public ObjectNode perform() {
        deck = new ArrayList<>();
        for (Card card : Player.getInstance(playerIdx).getCurrentDeck()) {
            deck.add(card.copy());
        }

        ObjectNode objectNode = super.perform();
        objectNode.put("playerIdx", playerIdx);
        objectNode.putPOJO("output", deck);
        return objectNode;
    }

    public String getCommand() {
        return "getPlayerDeck";
    }
}
