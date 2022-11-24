package action;

import card.character.minion.Minion;
import com.fasterxml.jackson.databind.node.ObjectNode;
import game.BoardRow;
import game.Game;
import game.Player;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class EndPlayerTurnAction extends Action {
    private Game game;

    /**
     * Ends the current player's turn + sets up the next round if both players have played their
     * turn
     *
     * @return null - this command never produces an output
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
