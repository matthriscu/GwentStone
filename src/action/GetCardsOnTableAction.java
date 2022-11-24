package action;

import card.character.minion.Minion;
import com.fasterxml.jackson.databind.node.ObjectNode;
import game.BoardRow;
import game.Game;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public final class GetCardsOnTableAction extends Action {
    private Game game;

    /**
     * Gets the minions which are currently on the table
     *
     * @return A json object describing the minions
     */
    public ObjectNode perform() {
        List<List<Minion>> onTable = new ArrayList<>();
        int cards = 0;

        for (BoardRow boardRow : game.getRowMap()) {
            List<Minion> onRow = new ArrayList<>();
            for (Minion minion : boardRow.getRow()) {
                onRow.add(new Minion(minion));
                ++cards;
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
        return "getCardsOnTable";
    }
}
