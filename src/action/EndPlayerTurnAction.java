package action;

import card.character.minion.Minion;
import com.fasterxml.jackson.databind.node.ObjectNode;
import game.BoardRow;
import game.Game;
import game.Player;

public final class EndPlayerTurnAction extends Action {
    private Game game;

    public EndPlayerTurnAction(final Game game) {
        this.game = game;
    }

    /**
     * Ends the current turn and starts the next round if the previous one is over
     */
    public ObjectNode perform() {
        for (BoardRow boardRow : game.getCurrentPlayer().getBoardRows()) {
            for (Minion minion : boardRow.getRow()) {
                minion.setFrozen(false);
                minion.setHasAttacked(false);
            }
        }

        game.getCurrentPlayer().getHero().setHasAttacked(false);

        game.setCurrentPlayer(game.getCurrentPlayer().getOpponent());

        if (game.getTurn() % 2 == 0) {
            Player.getInstance(1).roundStart(game.getTurn() / 2 + 1);
            Player.getInstance(2).roundStart(game.getTurn() / 2 + 1);
        }

        game.setTurn(game.getTurn() + 1);

        return null;
    }

    public String getCommand() {
        return "endPlayerTurn";
    }
}
