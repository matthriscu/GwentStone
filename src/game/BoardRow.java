package game;

import card.character.minion.Minion;
import player.Player;
import java.util.ArrayList;

public final class BoardRow {
    private static final int ROW_WIDTH = 5;
    public enum Type {
        FRONT, BACK
    }

    private Type type;
    private Player owner;

    private ArrayList<Minion> row;

    public BoardRow(final Player owner, final Type type) {
        this.owner = owner;
        this.type = type;
        this.row = new ArrayList<>();
    }

    public Player getOwner() {
        return owner;
    }

    public ArrayList<Minion> getRow() {
        return row;
    }

    public Type getType() {
        return type;
    }

    public boolean isFull() {
        return row.size() >= ROW_WIDTH;
    }
}
