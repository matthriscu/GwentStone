package action;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.Coordinates;
import game.Game;
import game.Player;
import lombok.AllArgsConstructor;

@AllArgsConstructor
final class UseAttackHeroAction extends Action {
    private Game game;
    private Coordinates cardAttacker;

    /**
     * Attacks the current player's opponent's hero
     *
     * @return A json object describing any errors that have occurred, or if this attack resulted
     * in the current player winning the game
     */
    public ObjectNode perform() {
        ObjectNode objectNode = null;

        try {
            game.getMinionAtPosition(cardAttacker).attackHero();

            Player currentPlayer = game.getCurrentPlayer();

            if (currentPlayer.getOpponent().getHero().getHealth() <= 0) {
                currentPlayer.setGamesWon(currentPlayer.getGamesWon() + 1);
                objectNode = JsonNodeFactory.instance.objectNode();
                objectNode.put("gameEnded", game.getCurrentPlayer() == Player.getInstance(1)
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
