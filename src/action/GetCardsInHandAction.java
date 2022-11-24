package action;

import card.Card;
import com.fasterxml.jackson.databind.node.ObjectNode;
import game.Player;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public final class GetCardsInHandAction extends Action {
    private int playerIdx;

    /**
     * Gets a player's currently held cards
     *
     * @return A json node describing the player's hand
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
