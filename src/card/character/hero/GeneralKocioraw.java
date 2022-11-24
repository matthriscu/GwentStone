package card.character.hero;

import card.character.minion.Minion;
import game.BoardRow;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class GeneralKocioraw extends Hero {
    public GeneralKocioraw(final GeneralKocioraw generalKocioraw) {
        super(generalKocioraw);
    }

    /**
     * Uses this hero's ability on the given BoardRow
     *
     * @throws Exception if the hero cannot attack for any reason
     */
    public void useAbility(final BoardRow boardRow) throws Exception {
        if (!canUseAbility()) {
            return;
        }

        if (getOwner() != boardRow.getOwner()) {
            throw new Exception("Selected row does not belong to the current player.");
        }

        for (Minion minion : boardRow.getRow()) {
            minion.setAttackDamage(minion.getAttackDamage() + 1);
        }

        setHasAttacked(true);
    }

    /**
     * Creates a deep copy of this card
     */
    public GeneralKocioraw copy() {
        return new GeneralKocioraw(this);
    }
}
