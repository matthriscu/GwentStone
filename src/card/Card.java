package card;

import com.fasterxml.jackson.annotation.JsonIgnore;
import game.Player;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Card {
    public enum Type {
        ENVIRONMENT, MINION, HERO
    }

    @Getter
    @Setter
    @JsonIgnore
    private Type type;
    @Getter
    @Setter
    private int mana;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private List<String> colors;
    @Getter
    @Setter
    @JsonIgnore
    private Player owner;

    protected Card(final Card card) {
        setOwner(card.getOwner());
        setMana(card.getMana());
        setColors(card.getColors());
        setName(card.getName());
        setDescription(card.getDescription());
        setType(card.getType());
    }

    /**
     * Creates a deep copy of this card
     */
    public abstract Card copy();
}
