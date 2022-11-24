package card.environment;

import card.Card;
import game.BoardRow;

public abstract class Environment extends Card {
    protected Environment() {
        setType(Type.ENVIRONMENT);
    }

    protected Environment(final Environment environment) {
        super(environment);
    }

    /**
     * Applies this card's effect to the targeted board row
     *
     * @throws Exception if this card cannot be used for any reason
     */
    public abstract void applyEffect(BoardRow boardRow) throws Exception;

    /**
     * Creates a deep copy of this card
     */
    public abstract Environment copy();
}
