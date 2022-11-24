package action;

import card.character.minion.Minion;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.Coordinates;
import game.Game;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class GetCardAtPositionAction extends Action {
    private Game game;
    private Coordinates coordinates;

    /**
     * Gets a minion from the position specified in coordinates
     *
     * @return A json object describing the minion, or any errors that have occurred
     */
    public ObjectNode perform() {
        ObjectNode objectNode = super.perform();
        objectNode.put("x", coordinates.getX());
        objectNode.put("y", coordinates.getY());

        try {
            objectNode.putPOJO("output", new Minion(game.getMinionAtPosition(coordinates)));
        } catch (Exception e) {
            objectNode.put("output", "No card available at that position.");
        }

        return objectNode;
    }

    public String getCommand() {
        return "getCardAtPosition";
    }
}
