package action;

import com.fasterxml.jackson.databind.node.ObjectNode;
import game.Game;

public final class UseHeroAbilityAction extends Action {
    private Game game;
    private int affectedRow;

    public UseHeroAbilityAction(final Game game, final int affectedRow) {
        this.game = game;
        this.affectedRow = affectedRow;
    }

    /**
     * Uses the current player's ability
     */
    public ObjectNode perform() {
        try {
            game.getCurrentPlayer().useHeroAbility(game.getRowMap()[affectedRow]);
            return null;
        } catch (Exception e) {
            ObjectNode objectNode = super.perform();
            objectNode.put("affectedRow", affectedRow);
            objectNode.put("error", e.getMessage());
            return objectNode;
        }
    }

    public String getCommand() {
        return "useHeroAbility";
    }
}
