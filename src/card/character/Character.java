package card.character;

import card.Card;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Character extends Card {
    @Getter
    @Setter
    private int health;
    @Getter
    @Setter
    @JsonIgnore
    private boolean hasAttacked;

    protected Character(final Character character) {
        super(character);
        setHealth(character.getHealth());
        setHasAttacked(character.isHasAttacked());
    }

    /**
     * @return true - if this minion can use its ability
     * @throws Exception if this character can't use its ability, or if it doesn't have one
     */
    public abstract boolean canUseAbility() throws Exception;

    /**
     * Resets this characther for the start of the next round
     */
    public abstract void reset();

    /**
     * Creates a deep copy of this card
     */
    public abstract Character copy();
}
