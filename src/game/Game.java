package game;

import card.character.minion.Minion;
import fileio.Coordinates;
import lombok.Getter;
import lombok.Setter;

import static game.BoardRow.Type.BACK;
import static game.BoardRow.Type.FRONT;

public final class Game {
    @Getter
    @Setter
    private Player currentPlayer;
    @Getter
    @Setter
    private int turn;
    @Getter
    private BoardRow[] rowMap;

    public Game(final Player startingPlayer) {
        currentPlayer = startingPlayer;
        turn = 1;
        rowMap = new BoardRow[]{Player.getInstance(2).getBoardRows()[BACK.ordinal()],
                Player.getInstance(2).getBoardRows()[FRONT.ordinal()],
                Player.getInstance(1).getBoardRows()[FRONT.ordinal()],
                Player.getInstance(1).getBoardRows()[BACK.ordinal()]};
    }

    /**
     * Gets the current minion at a specific position
     */
    public Minion getMinionAtPosition(final Coordinates coordinates) {
        return getRowMap()[coordinates.getX()].getRow().get(coordinates.getY());
    }
}
