package game;

import card.Card;
import card.CardFactory;
import card.character.hero.Hero;
import card.character.minion.Minion;
import card.environment.Environment;
import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static game.BoardRow.Type.BACK;
import static game.BoardRow.Type.FRONT;
import static java.lang.Math.min;

public final class Player {
    private static final int MAX_ROUND_MANA = 10;
    private static Player[] players = new Player[]{new Player(1), new Player(2)};
    @Getter
    private BoardRow[] boardRows;
    @Getter
    private List<List<Card>> decks;
    @Getter
    private List<Card> currentDeck;
    @Getter
    private List<Card> hand;
    @Getter
    private int mana;
    @Getter
    @Setter
    private Hero hero;
    @Getter
    private int id;
    @Getter
    @Setter
    private int tanks;
    @Getter
    @Setter
    private int gamesWon;
    @Getter
    private int gamesPlayed;

    private Player(final int id) {
        decks = new ArrayList<>();
        this.id = id;
    }

    /**
     * Gets this player's opponent
     */
    public Player getOpponent() {
        if (this == players[0]) {
            return players[1];
        }
        return players[0];
    }

    /**
     * Resets all player stats
     * <p>
     * This method is necessary because all tests are run within one instance of the program and
     * player stats persist because the players are static.
     */
    public static void resetStats() {
        players[0].gamesPlayed = 0;
        players[0].gamesWon = 0;
        players[1].gamesPlayed = 0;
        players[1].gamesWon = 0;
    }

    /**
     * Sets this player's decks and sets this player as the owner of all cards within them
     */
    public void setDecks(final List<List<Card>> decks) {
        this.decks = decks;

        for (List<Card> deck : decks) {
            for (Card card : deck) {
                card.setOwner(this);
            }
        }
    }

    /**
     * Gets one of the player instances
     */
    public static Player getInstance(final int id) {
        return players[id - 1];
    }

    /**
     * Prepares this player for a new game
     */
    public void startGameSetup(final int deckIndex, final int shuffleSeed,
                               final CardInput heroInput) {
        currentDeck = new LinkedList<>();
        for (Card card : decks.get(deckIndex)) {
            currentDeck.add(card.copy());
        }

        Collections.shuffle(currentDeck, new Random(shuffleSeed));

        boardRows = new BoardRow[]{new BoardRow(this, FRONT), new BoardRow(this, BACK)};
        hero = (Hero) CardFactory.makeCard(heroInput);
        hero.setOwner(this);

        hand = new ArrayList<>();
        mana = 0;
        tanks = 0;
        ++gamesPlayed;

        roundStart(1);
    }

    /**
     * Draws a card from a player's deck, if it isn't empty
     */
    public void drawCard() {
        if (!currentDeck.isEmpty()) {
            hand.add(currentDeck.remove(0));
        }
    }

    /**
     * Gives a player resources at the beginning of every round
     */
    public void roundStart(final int roundIndex) {
        if (!currentDeck.isEmpty()) {
            hand.add(currentDeck.remove(0));
        }
        mana += min(roundIndex, MAX_ROUND_MANA);
    }

    /**
     * Places a minion from a player's hand
     *
     * @throws Exception if this player cannot place the specified card
     */
    public void placeCard(final int index) throws Exception {
        if (hand.get(index).getType() == Card.Type.ENVIRONMENT) {
            throw new Exception("Cannot place environment card on table.");
        }

        Minion minion = (Minion) hand.get(index);

        if (minion.getMana() > mana) {
            throw new Exception("Not enough mana to place card on table.");
        }

        if (boardRows[minion.getPreferredRow().ordinal()].isFull()) {
            throw new Exception("Cannot place card on table since row is full.");
        }

        if (minion.isTank()) {
            ++tanks;
        }

        mana -= minion.getMana();
        boardRows[minion.getPreferredRow().ordinal()].getRow().add(minion);
        minion.setBoardRow(boardRows[minion.getPreferredRow().ordinal()]);
        hand.remove(index);
    }

    /**
     * Uses an environment card from this player's hand
     *
     * @throws Exception if this player cannot use the specified environment card
     */
    public void useEnvironmentCard(final int index, final BoardRow affectedRow) throws Exception {
        if (hand.get(index).getType() != Card.Type.ENVIRONMENT) {
            throw new Exception("Chosen card is not of type environment.");
        }

        Environment environment = (Environment) hand.get(index);

        if (environment.getMana() > mana) {
            throw new Exception("Not enough mana to use environment card.");
        }

        environment.applyEffect(affectedRow);
        mana -= environment.getMana();
        hand.remove(index);
    }

    /**
     * Uses this player's hero's ability
     *
     * @throws Exception if this player cannot use their hero's ability for any reason
     */
    public void useHeroAbility(final BoardRow affectedRow) throws Exception {
        if (getHero().getMana() > mana) {
            throw new Exception("Not enough mana to use hero's ability.");
        }

        if (!getHero().canUseAbility()) {
            return;
        }

        getHero().useAbility(affectedRow);
        mana -= getHero().getMana();
    }
}
