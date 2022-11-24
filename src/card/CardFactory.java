package card;

import card.character.hero.EmpressThorina;
import card.character.hero.GeneralKocioraw;
import card.character.hero.KingMudface;
import card.character.hero.LordRoyce;
import card.character.minion.Disciple;
import card.character.minion.Minion;
import card.character.minion.Miraj;
import card.character.minion.TheCursedOne;
import card.character.minion.TheRipper;
import card.environment.Firestorm;
import card.environment.HeartHound;
import card.environment.Winterfell;
import fileio.CardInput;
import fileio.DecksInput;

import java.util.ArrayList;
import java.util.List;

import static card.Card.Type.MINION;
import static game.BoardRow.Type.BACK;
import static game.BoardRow.Type.FRONT;

public final class CardFactory {
    private CardFactory() {
    }

    /**
     * Converts a CardInput into a Card
     */
    public static Card makeCard(final CardInput cardInput) {
        Card card = switch (cardInput.getName()) {
            case "Sentinel", "Berserker" -> new Minion();
            case "Goliath", "Warden" -> new Minion(true);
            case "The Ripper" -> new TheRipper();
            case "Miraj" -> new Miraj();
            case "The Cursed One" -> new TheCursedOne();
            case "Disciple" -> new Disciple();
            case "Firestorm" -> new Firestorm();
            case "Winterfell" -> new Winterfell();
            case "Heart Hound" -> new HeartHound();
            case "Lord Royce" -> new LordRoyce();
            case "Empress Thorina" -> new EmpressThorina();
            case "King Mudface" -> new KingMudface();
            case "General Kocioraw" -> new GeneralKocioraw();
            default -> null;
        };

        card.setName(cardInput.getName());
        card.setMana(cardInput.getMana());
        card.setColors(cardInput.getColors());
        card.setDescription(cardInput.getDescription());

        if (card.getType() == MINION) {
            Minion minion = (Minion) card;

            minion.setAttackDamage(cardInput.getAttackDamage());
            minion.setHealth(cardInput.getHealth());

            minion.setPreferredRow(
                switch (card.getName()) {
                    case "The Ripper", "Miraj", "Goliath", "Warden" -> FRONT;
                    case "Sentinel", "Berserker", "The Cursed One", "Disciple" -> BACK;
                    default -> null;
                }
            );
        }

        return card;
    }

    /**
     * Converts a DecksInput into a List of Lists of Cards
     */
    public static List<List<Card>> makeDecks(final DecksInput decksInput) {
        List<List<Card>> decks = new ArrayList<>();

        for (List<CardInput> deckInput : decksInput.getDecks()) {
            List<Card> deck = new ArrayList<>();

            for (CardInput cardInput : deckInput) {
                deck.add(makeCard(cardInput));
            }

            decks.add(deck);
        }

        return decks;
    }
}
