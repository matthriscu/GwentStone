package game;

import card.character.minion.Minion;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public final class BoardRow {
    private static final int MAX_ROW_WIDTH = 5;

    public enum Type {
        FRONT, BACK
    }

    @Getter
    private Type type;
    @Getter
    private Player owner;
    @Getter
    private List<Minion> row;

    public BoardRow(final Player owner, final Type type) {
        this.owner = owner;
        this.type = type;
        this.row = new ArrayList<>();
    }

    public boolean isFull() {
        return row.size() >= MAX_ROW_WIDTH;
    }
}
