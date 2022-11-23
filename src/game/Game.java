package game;

import card.character.minion.Minion;
import fileio.Coordinates;
import player.Player;

import static game.BoardRow.Type.BACK;
import static game.BoardRow.Type.FRONT;

public final class Game {
    private Player startingPlayer, secondPlayer;
    private Player currentPlayer;
    private Player winner;
    private int turn;
    private BoardRow[] rowMap;

    public Game(final Player startingPlayer, final Player secondPlayer) {
        this.startingPlayer = startingPlayer;
        this.secondPlayer = secondPlayer;
        currentPlayer = startingPlayer;
        turn = 1;
        rowMap = new BoardRow[]{
            Player.getInstance(2).getBoardRows()[BACK.ordinal()],
            Player.getInstance(2).getBoardRows()[FRONT.ordinal()],
            Player.getInstance(1).getBoardRows()[FRONT.ordinal()],
            Player.getInstance(1).getBoardRows()[BACK.ordinal()],
        };
        winner = null;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(final Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(final int turn) {
        this.turn = turn;
    }

    public BoardRow[] getRowMap() {
        return rowMap;
    }

    /**
     * Gets the minion at a specific position
     */
    public Minion getMinionAtPosition(final Coordinates coordinates) {
        return getRowMap()[coordinates.getX()].getRow().get(coordinates.getY());
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(final Player winner) {
        this.winner = winner;
    }
}
