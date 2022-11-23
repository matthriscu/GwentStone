package main;

import action.Action;
import action.ActionFactory;
import card.CardFactory;
import checker.Checker;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import checker.CheckerConstants;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import fileio.GameInput;
import fileio.Input;
import fileio.StartGameInput;
import game.Game;
import game.Player;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(CheckerConstants.TESTS_PATH);
        Path path = Paths.get(CheckerConstants.RESULT_PATH);

        if (Files.exists(path)) {
            File resultFile = new File(String.valueOf(path));
            for (File file : Objects.requireNonNull(resultFile.listFiles())) {
                file.delete();
            }
            resultFile.delete();
        }
        Files.createDirectories(path);

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String filepath = CheckerConstants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getName(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(CheckerConstants.TESTS_PATH + filePath1),
                Input.class);

        ArrayNode output = objectMapper.createArrayNode();

        Player.resetStats();

        Player.getInstance(1).setDecks(CardFactory.makeDecks(inputData.getPlayerOneDecks()));
        Player.getInstance(2).setDecks(CardFactory.makeDecks(inputData.getPlayerTwoDecks()));

        for (GameInput gameInput : inputData.getGames()) {
            StartGameInput startGameInput = gameInput.getStartGame();

            Player.getInstance(1).startGameSetup(startGameInput.getPlayerOneDeckIdx(),
                    startGameInput.getShuffleSeed(),
                    startGameInput.getPlayerOneHero());

            Player.getInstance(2).startGameSetup(startGameInput.getPlayerTwoDeckIdx(),
                    startGameInput.getShuffleSeed(),
                    startGameInput.getPlayerTwoHero());

            Game game = new Game(Player.getInstance(startGameInput.getStartingPlayer()),
                    Player.getInstance(startGameInput.getStartingPlayer()).getOpponent());

            for (ActionsInput actionsInput : gameInput.getActions()) {
                Action action = ActionFactory.makeAction(actionsInput, game);
                ObjectNode objectNode = action.perform();
                if (objectNode != null) {
                    output.add(objectNode);
                }
            }
        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath2), output);
    }
}
