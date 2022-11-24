package action;

import card.Card;
import com.fasterxml.jackson.databind.node.ObjectNode;
import game.Player;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
final class GetPlayerDeckAction extends Action {
    private int playerIdx;

    /**
     * Gets the corresponding player's deck
     *
     * @return A json object describing the deck
     */
    public ObjectNode perform() {
        List<Card> deck = new ArrayList<>();
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
