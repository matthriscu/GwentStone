package action;

import card.character.minion.Minion;
import com.fasterxml.jackson.databind.node.ObjectNode;
import game.BoardRow;
import game.Game;
import java.util.ArrayList;
import java.util.List;

public final class GetFrozenCardsOnTableAction extends Action {
    private Game game;

    public GetFrozenCardsOnTableAction(final Game game) {
        this.game = game;
    }

    /**
     * Gets the frozen cards on the table
     */
    public ObjectNode perform() {
        List<List<Minion>> onTable = new ArrayList<>();
        int cards = 0;

        for (BoardRow boardRow : game.getRowMap()) {
            List<Minion> onRow = new ArrayList<>();
            for (Minion minion : boardRow.getRow()) {
                if (minion.isFrozen()) {
                    onRow.add(new Minion(minion));
                    ++cards;
                }
            }

            onTable.add(onRow);
        }

        if (cards == 0) {
            onTable.clear();
        }

        ObjectNode objectNode = super.perform();
        objectNode.putPOJO("output", onTable);
        return objectNode;
    }

    public String getCommand() {
        return "getFrozenCardsOnTable";
    }
}
