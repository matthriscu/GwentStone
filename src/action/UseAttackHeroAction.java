package action;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.Coordinates;
import game.Game;
import game.Player;

public final class UseAttackHeroAction extends Action {
    private Game game;
    private Coordinates cardAttacker;

    public UseAttackHeroAction(final Game game, final Coordinates cardAttacker) {
        this.game = game;
        this.cardAttacker = cardAttacker;
    }

    /**
     * Attacks the enemy player's hero
     */
    public ObjectNode perform() {
        ObjectNode objectNode = null;

        try {
            game.getMinionAtPosition(cardAttacker).attackHero();

            if (game.getCurrentPlayer().getOpponent().getHero().getHealth() <= 0) {
                game.getCurrentPlayer().incrementGamesWon();
                objectNode = JsonNodeFactory.instance.objectNode();
                objectNode.put("gameEnded",
                        game.getCurrentPlayer() == Player.getInstance(1)
                                ? "Player one killed the enemy hero."
                                : "Player two killed the enemy hero.");
            }
        } catch (Exception e) {
            objectNode = super.perform();
            objectNode.putPOJO("cardAttacker", cardAttacker);
            objectNode.put("error", e.getMessage());
        }

        return objectNode;
    }

    public String getCommand() {
        return "useAttackHero";
    }
}
