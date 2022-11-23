package card;

import com.fasterxml.jackson.annotation.JsonIgnore;
import game.Player;
import java.util.List;

public abstract class Card {
    public enum Type {
        ENVIRONMENT,
        MINION,
        HERO
    }

    @JsonIgnore
    private Type type;
    private int mana;
    private String name;
    private String description;
    private List<String> colors;
    @JsonIgnore
    private Player owner;

    protected Card() {
    }

    protected Card(final Card card) {
        setOwner(card.getOwner());
        setMana(card.getMana());
        setColors(card.getColors());
        setName(card.getName());
        setDescription(card.getDescription());
        setType(card.getType());
    }

    public final Type getType() {
        return type;
    }

    public final void setType(final Type type) {
        this.type = type;
    }

    public final int getMana() {
        return mana;
    }

    public final void setMana(final int mana) {
        this.mana = mana;
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(final String description) {
        this.description = description;
    }

    public final List<String> getColors() {
        return colors;
    }

    public final void setColors(final List<String> colors) {
        this.colors = colors;
    }

    public final Player getOwner() {
        return owner;
    }

    public final void setOwner(final Player owner) {
        this.owner = owner;
    }

    /**
     * Create a deep copy of this card
     */
    public abstract Card copy();
}
