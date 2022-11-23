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
     * Uses this card's ability
     */
    public void useAbility(final BoardRow boardRow) throws Exception {
        throw new Exception("This card does not have a special ability.");
    }

    /**
     * Create a deep copy of this card
     */
    public abstract Environment copy();
}
