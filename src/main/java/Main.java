import controllers.GameController;
import exceptions.InvalidMoveException;
import models.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidMoveException {
        GameController gameController = new GameController();

        int dimension = 3;
        List<Player> players = new ArrayList<>();

        players.add(new Player("Ravtej", new Symbol('X'))); // Human player
        players.add(new Bot("Scaler", new Symbol('O'), BotDifficultyLevel.EASY)); // Bot player

        Game game = gameController.startGame(dimension, players);

        while (gameController.getGameState(game).equals(GameState.IN_PROGRESS)) {
            gameController.printBoard(game);

            gameController.makeMove(game);
        }

        // We come out of the while loop if GameState is either ended or it is a draw
        if(gameController.getGameState(game).equals(GameState.ENDED)) {
            gameController.printBoard(game);
            System.out.println(gameController.getWinner(game).getName() + " has won the game!");
        } else {
            System.out.println("It is a DRAW!");
        }
    }
}

