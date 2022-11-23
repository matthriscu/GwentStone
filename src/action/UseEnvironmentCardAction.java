package action;

import com.fasterxml.jackson.databind.node.ObjectNode;
import game.Game;

public final class UseEnvironmentCardAction extends Action {
    private Game game;
    private int handIdx;
    private int affectedRow;


    public UseEnvironmentCardAction(final Game game, final int handIdx, final int affectedRow) {
        this.game = game;
        this.handIdx = handIdx;
        this.affectedRow = affectedRow;
    }

    /**
     * Uses an environment card from the player's hand
     */
    public ObjectNode perform() {
        try {
            game.getCurrentPlayer().useEnvironmentCard(handIdx, game.getRowMap()[affectedRow]);
            return null;
        } catch (Exception e) {
            ObjectNode objectNode = super.perform();
            objectNode.put("handIdx", handIdx);
            objectNode.put("affectedRow", affectedRow);
            objectNode.put("error", e.getMessage());
            return objectNode;
        }
    }

    public String getCommand() {
        return "useEnvironmentCard";
    }
}
