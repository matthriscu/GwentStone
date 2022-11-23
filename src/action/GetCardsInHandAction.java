package action;

import card.Card;
import com.fasterxml.jackson.databind.node.ObjectNode;
import player.Player;
import java.util.ArrayList;
import java.util.List;

public final class GetCardsInHandAction extends Action {
    private int playerIdx;

    public GetCardsInHandAction(final int playerIdx) {
        this.playerIdx = playerIdx;
    }

    /**
     * Gets the cards in the corresponding player's hand
     */
    public ObjectNode perform() {
        List<Card> hand = new ArrayList<>();
        for (Card card : Player.getInstance(playerIdx).getHand()) {
            hand.add(card.copy());
        }

        ObjectNode objectNode = super.perform();
        objectNode.put("playerIdx", playerIdx);
        objectNode.putPOJO("output", hand);
        return objectNode;
    }

    public String getCommand() {
        return "getCardsInHand";
    }
}
