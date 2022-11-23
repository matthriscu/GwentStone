package action;

import card.Card;
import com.fasterxml.jackson.databind.node.ObjectNode;
import game.Player;
import java.util.ArrayList;
import java.util.List;

public final class GetEnvironmentCardsInHandAction extends Action {
    private int playerIdx;

    public GetEnvironmentCardsInHandAction(final int playerIdx) {
        this.playerIdx = playerIdx;
    }

    /**
     * Gets the environment cards in the corresponding player's hand
     */
    public ObjectNode perform() {
        List<Card> hand = new ArrayList<>();
        for (Card card : Player.getInstance(playerIdx).getHand()) {
            if (card.getType() == Card.Type.ENVIRONMENT) {
                hand.add(card.copy());
            }
        }
        ObjectNode objectNode = super.perform();
        objectNode.put("playerIdx", playerIdx);
        objectNode.putPOJO("output", hand);
        return objectNode;
    }

    public String getCommand() {
        return "getEnvironmentCardsInHand";
    }
}
