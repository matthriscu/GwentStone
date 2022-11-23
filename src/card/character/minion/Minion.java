package card.character.minion;

import card.character.Character;
import com.fasterxml.jackson.annotation.JsonIgnore;
import game.BoardRow;
import static card.Card.Type.MINION;

public class Minion extends Character {
    private int attackDamage;
    private boolean isTank;
    private boolean isFrozen;
    private BoardRow boardRow;
    private BoardRow.Type preferredRow;

    public Minion() {
        this(false);
    }

    public Minion(final boolean isTank) {
        super();
        setType(MINION);
        this.isTank = isTank;
        this.isFrozen = false;
        this.boardRow = null;
        this.preferredRow = null;
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

    public final int getAttackDamage() {
        return attackDamage;
    }

    public final void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }

    @JsonIgnore
    public final boolean isTank() {
        return isTank;
    }

    public final void setTank(final boolean tank) {
        isTank = tank;
    }

    @JsonIgnore
    public final boolean isFrozen() {
        return isFrozen;
    }

    public final void setFrozen(final boolean frozen) {
        isFrozen = frozen;
    }

    @JsonIgnore
    public final BoardRow getBoardRow() {
        return boardRow;
    }

    public final void setBoardRow(final BoardRow boardRow) {
        this.boardRow = boardRow;
    }

    @JsonIgnore
    public final BoardRow.Type getPreferredRow() {
        return preferredRow;
    }

    public final void setPreferredRow(final BoardRow.Type preferredRow) {
        this.preferredRow = preferredRow;
    }

    /**
     * Checks if this minion can use their ability
     */
    public boolean canUseAbility() throws Exception {
        if (isFrozen) {
            throw new Exception("Attacker card is frozen.");
        }

        if (getHasAttacked()) {
            throw new Exception("Attacker card has already attacked this turn.");
        }

        return true;
    }

    /**
     * Uses this minion's ability
     */
    public void useAbility(final Minion minion) throws Exception {
        throw new Exception("This minion doesn't have a special ability.");
    }

    /**
     * Destroys this minion
     */
    public final void destroy() {
        if (isTank) {
            getOwner().setTanks(getOwner().getTanks() - 1);
        }

        boardRow.getRow().remove(this);
    }

    /**
     * Resets this minion for the next round
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
     * Attacks another minions
     */
    public final void attackMinion(final Minion minion) throws Exception {
        if (getOwner() == minion.getOwner()) {
            throw new Exception("Attacked card does not belong to the enemy.");
        }

        if (getHasAttacked()) {
            throw new Exception("Attacker card has already attacked this turn.");
        }

        if (isFrozen()) {
            throw new Exception("Attacker card is frozen.");
        }

        if (minion.getOwner().hasTanks() && !minion.isTank()) {
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

        if (getHasAttacked()) {
            throw new Exception("Attacker card has already attacked this turn.");
        }

        if (getOwner().getOpponent().hasTanks()) {
            throw new Exception("Attacked card is not of type 'Tank'.");
        }

        getOwner().getOpponent().getHero()
                .setHealth(getOwner().getOpponent().getHero().getHealth() - attackDamage);
        setHasAttacked(true);
    }
}
