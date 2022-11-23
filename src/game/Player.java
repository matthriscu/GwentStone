package game;

import card.Card;
import card.CardFactory;
import card.character.hero.Hero;
import card.character.minion.Minion;
import card.environment.Environment;
import fileio.CardInput;

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
    private static Player[] players  = new Player[]{
            new Player(1),
            new Player(2)
    };
    private BoardRow[] boardRows;
    private List<List<Card>> decks;
    private List<Card> currentDeck;
    private List<Card> hand;
    private int mana;
    private Hero hero;
    private int id;
    private int tanks;
    private int gamesWon;
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
     */
    public static void resetStats() {
        players[0].gamesPlayed = 0;
        players[0].gamesWon = 0;
        players[1].gamesPlayed = 0;
        players[1].gamesWon = 0;
    }

    public List<List<Card>> getDecks() {
        return decks;
    }

    /**
     * Sets this player's decks
     */
    public void setDecks(final List<List<Card>> decks) {
        this.decks = decks;

        for (List<Card> deck : decks) {
            for (Card card : deck) {
                card.setOwner(this);
            }
        }
    }

    public Hero getHero() {
        return hero;
    }

    public int getId() {
        return id;
    }

    public int getTanks() {
        return tanks;
    }

    public void setTanks(final int tanks) {
        this.tanks = tanks;
    }

    public List<Card> getCurrentDeck() {
        return currentDeck;
    }
    public List<Card> getHand() {
        return hand;
    }
    public int getMana() {
        return mana;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    /**
     * Increments this player's number of wins
     */
    public void incrementGamesWon() {
        ++gamesWon;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Gets one of the 2 players
     */
    public static Player getInstance(final int id) {
        return players[id - 1];
    }

    /**
     * Prepares this player for a new game
     */
    public void startGameSetup(final int deckIndex,
                               final int shuffleSeed,
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

    public BoardRow[] getBoardRows() {
        return boardRows;
    }

    /**
     * Checks if a player has tanks
     */
    public boolean hasTanks() {
        return tanks > 0;
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
     * @param roundIndex
     */
    public void roundStart(final int roundIndex) {
        drawCard();
        mana += min(roundIndex, MAX_ROUND_MANA);
    }

    /**
     * Places a minion from a player's hand
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
     */
    public void useEnvironmentCard(final int index, final BoardRow affectedRow) throws Exception {
        if (hand.get(index).getType() != Card.Type.ENVIRONMENT) {
            throw new Exception("Chosen card is not of type environment.");
        }

        Environment environment = (Environment) hand.get(index);

        if (environment.getMana() > mana) {
            throw new Exception("Not enough mana to use environment card.");
        }

        environment.useAbility(affectedRow);
        mana -= environment.getMana();
        hand.remove(index);
    }

    /**
     * Uses this player's hero's ability
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
