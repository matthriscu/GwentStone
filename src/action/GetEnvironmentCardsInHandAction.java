package action;

import card.Card;
import com.fasterxml.jackson.databind.node.ObjectNode;
import game.Player;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public final class GetEnvironmentCardsInHandAction extends Action {
    private int playerIdx;

    /**
     * Gets the environment cards currently in a player's hand
     *
     * @return A json object describing the cards, or any errors that have occurred
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
