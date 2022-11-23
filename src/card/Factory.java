package card;

import action.Action;
import action.CardUsesAbilityAction;
import action.CardUsesAttackAction;
import action.EndPlayerTurnAction;
import action.GetCardAtPositionAction;
import action.GetCardsInHandAction;
import action.GetCardsOnTableAction;
import action.GetEnvironmentCardsInHandAction;
import action.GetFrozenCardsOnTableAction;
import action.GetPlayerDeckAction;
import action.GetPlayerHeroAction;
import action.GetPlayerManaAction;
import action.GetPlayerOneWinsAction;
import action.GetPlayerTurnAction;
import action.GetPlayerTwoWinsAction;
import action.GetTotalGamesPlayedAction;
import action.PlaceCardAction;
import action.UseAttackHeroAction;
import action.UseEnvironmentCardAction;
import action.UseHeroAbilityAction;
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
import fileio.ActionsInput;
import fileio.CardInput;
import fileio.Coordinates;
import fileio.DecksInput;
import game.Game;

import java.util.ArrayList;
import java.util.List;

import static card.Card.Type.MINION;
import static game.BoardRow.Type.BACK;
import static game.BoardRow.Type.FRONT;

public final class Factory {
    private Factory() {
    }

    /**
     * Translates CardInput into Card
     */
    public static Card makeCard(final CardInput cardInput) {
        Card card = switch (cardInput.getName()) {
            case "Sentinel", "Berserker" -> new Minion(false);
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
                    case "Sentinel", "Berserker", "The Cursed One", "Disciple"
                            -> BACK;
                    default -> null;
                }
            );
        }

        return card;
    }

    /**
     * Translates decksInput into lists of Card
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

    /**
     * Translates actionsInput into Action objects
     */
    public static Action makeAction(final ActionsInput actionsInput, final Game game) {
        return switch (actionsInput.getCommand()) {
            case "getPlayerDeck" ->
                    new GetPlayerDeckAction(actionsInput.getPlayerIdx());
            case "getPlayerHero" ->
                    new GetPlayerHeroAction(actionsInput.getPlayerIdx());
            case "getPlayerTurn" ->
                    new GetPlayerTurnAction(game);
            case "getCardsInHand" ->
                    new GetCardsInHandAction(actionsInput.getPlayerIdx());
            case "endPlayerTurn" ->
                    new EndPlayerTurnAction(game);
            case "placeCard" ->
                    new PlaceCardAction(game, actionsInput.getHandIdx());
            case "getPlayerMana" ->
                    new GetPlayerManaAction(actionsInput.getPlayerIdx());
            case "getCardsOnTable" ->
                    new GetCardsOnTableAction(game);
            case "getEnvironmentCardsInHand" ->
                    new GetEnvironmentCardsInHandAction(
                            actionsInput.getPlayerIdx()
                    );
            case "getCardAtPosition" ->
                    new GetCardAtPositionAction(
                            game,
                            new Coordinates(actionsInput.getX(), actionsInput.getY())
                    );
            case "useEnvironmentCard" ->
                    new UseEnvironmentCardAction(
                            game,
                            actionsInput.getHandIdx(),
                            actionsInput.getAffectedRow()
                    );
            case "getFrozenCardsOnTable" ->
                    new GetFrozenCardsOnTableAction(game);
            case "cardUsesAttack" ->
                    new CardUsesAttackAction(
                            game,
                            actionsInput.getCardAttacker(),
                            actionsInput.getCardAttacked()
                    );
            case "cardUsesAbility" ->
                    new CardUsesAbilityAction(
                            game,
                            actionsInput.getCardAttacker(),
                            actionsInput.getCardAttacked()
                    );
            case "useAttackHero" ->
                    new UseAttackHeroAction(game, actionsInput.getCardAttacker());
            case "useHeroAbility" ->
                    new UseHeroAbilityAction(game, actionsInput.getAffectedRow());
            case "getPlayerOneWins" ->
                    new GetPlayerOneWinsAction();
            case "getPlayerTwoWins" ->
                    new GetPlayerTwoWinsAction();
            case "getTotalGamesPlayed" ->
                    new GetTotalGamesPlayedAction();
            default -> null;
        };
    }
}
