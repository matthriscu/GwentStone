package card.character.minion;

import card.character.Character;
import card.character.hero.Hero;
import com.fasterxml.jackson.annotation.JsonIgnore;
import game.BoardRow;
import lombok.Getter;
import lombok.Setter;

import static card.Card.Type.MINION;

public class Minion extends Character {
    @Getter
    @Setter
    private int attackDamage;
    @Getter
    @Setter
    @JsonIgnore
    private boolean isTank;
    @Getter
    @Setter
    @JsonIgnore
    private boolean isFrozen;
    @Getter
    @Setter
    @JsonIgnore
    private BoardRow boardRow;
    @Getter
    @Setter
    @JsonIgnore
    private BoardRow.Type preferredRow;

    public Minion() {
        super();
        setType(MINION);
        this.isTank = false;
        this.isFrozen = false;
        this.boardRow = null;
        this.preferredRow = null;
    }

    public Minion(final boolean isTank) {
        this();
        this.isTank = isTank;
    }

    public Minion(final Minion minion) {
        super(minion);
        setAttackDamage(minion.getAttackDamage());
        setPreferredRow(minion.getPreferredRow());
        setTank(minion.isTank());
        setFrozen(minion.isFrozen());
        setBoardRow(minion.getBoardRow());
        setPreferredRow(minion.getPreferredRow());
    }

    /**
     * Checks if this minion can use its ability
     *
     * @throws Exception if this minion can't use its ability for any reason
     */
    public boolean canUseAbility() throws Exception {
        if (isFrozen) {
            throw new Exception("Attacker card is frozen.");
        }

        if (isHasAttacked()) {
            throw new Exception("Attacker card has already attacked this turn.");
        }

        return true;
    }

    /**
     * Uses this minion's ability on another minion
     *
     * @throws Exception if this minion can't use its ability for any reason
     */
    public void useAbility(final Minion minion) throws Exception {
        throw new Exception("This minion doesn't have a special ability.");
    }

    /**
     * Removes this minion from its board row
     */
    public final void destroy() {
        if (isTank) {
            getOwner().setTanks(getOwner().getTanks() - 1);
        }

        boardRow.getRow().remove(this);
    }

    /**
     * Resets this minion for the next round by clearing its "hasAttacked" field and unfreezing it
     */
    public final void reset() {
        setHasAttacked(false);
        this.isFrozen = false;
    }

    /**
     * Creates a deep copy of this card
     */
    public Minion copy() {
        return new Minion(this);
    }

    /**
     * Attacks another minion
     *
     * @throws Exception if this minion can't attack for any reason
     */
    public final void attackMinion(final Minion minion) throws Exception {
        if (getOwner() == minion.getOwner()) {
            throw new Exception("Attacked card does not belong to the enemy.");
        }

        if (isHasAttacked()) {
            throw new Exception("Attacker card has already attacked this turn.");
        }

        if (isFrozen()) {
            throw new Exception("Attacker card is frozen.");
        }

        if (minion.getOwner().getTanks() > 0 && !minion.isTank()) {
            throw new Exception("Attacked card is not of type 'Tank'.");
        }

        minion.setHealth(minion.getHealth() - attackDamage);
        if (minion.getHealth() <= 0) {
            minion.destroy();
        }

        setHasAttacked(true);
    }

    /**
     * Attacks the opponent's hero
     */
    public final void attackHero() throws Exception {
        if (isFrozen()) {
            throw new Exception("Attacker card is frozen.");
        }

        if (isHasAttacked()) {
            throw new Exception("Attacker card has already attacked this turn.");
        }

        if (getOwner().getOpponent().getTanks() > 0) {
            throw new Exception("Attacked card is not of type 'Tank'.");
        }

        Hero attackedHero = getOwner().getOpponent().getHero();
        attackedHero.setHealth(attackedHero.getHealth() - attackDamage);

        setHasAttacked(true);
    }
}
