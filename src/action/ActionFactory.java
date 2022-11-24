package action;

import fileio.ActionsInput;
import fileio.Coordinates;
import game.Game;

public final class ActionFactory {
    private ActionFactory() {
    }

    /**
     * Converts an ActionsInput into an Action
     */
    public static Action makeAction(final ActionsInput actionsInput, final Game game) {
        return switch (actionsInput.getCommand()) {
            case "getPlayerDeck" -> new GetPlayerDeckAction(actionsInput.getPlayerIdx());
            case "getPlayerHero" -> new GetPlayerHeroAction(actionsInput.getPlayerIdx());
            case "getPlayerTurn" -> new GetPlayerTurnAction(game);
            case "getCardsInHand" -> new GetCardsInHandAction(actionsInput.getPlayerIdx());
            case "endPlayerTurn" -> new EndPlayerTurnAction(game);
            case "placeCard" -> new PlaceCardAction(game, actionsInput.getHandIdx());
            case "getPlayerMana" -> new GetPlayerManaAction(actionsInput.getPlayerIdx());
            case "getCardsOnTable" -> new GetCardsOnTableAction(game);
            case "getEnvironmentCardsInHand" ->
                    new GetEnvironmentCardsInHandAction(actionsInput.getPlayerIdx());
            case "getCardAtPosition" ->
                    new GetCardAtPositionAction(game, new Coordinates(actionsInput.getX(),
                            actionsInput.getY()));
            case "useEnvironmentCard" ->
                    new UseEnvironmentCardAction(game, actionsInput.getHandIdx(),
                            actionsInput.getAffectedRow());
            case "getFrozenCardsOnTable" -> new GetFrozenCardsOnTableAction(game);
            case "cardUsesAttack" ->
                    new CardUsesAttackAction(game, actionsInput.getCardAttacker(),
                            actionsInput.getCardAttacked());
            case "cardUsesAbility" ->
                    new CardUsesAbilityAction(game, actionsInput.getCardAttacker(),
                            actionsInput.getCardAttacked());
            case "useAttackHero" -> new UseAttackHeroAction(game, actionsInput.getCardAttacker());
            case "useHeroAbility" -> new UseHeroAbilityAction(game, actionsInput.getAffectedRow());
            case "getPlayerOneWins" -> new GetPlayerOneWinsAction();
            case "getPlayerTwoWins" -> new GetPlayerTwoWinsAction();
            case "getTotalGamesPlayed" -> new GetTotalGamesPlayedAction();
            default -> null;
        };
    }
}
