package controllers;

import exceptions.InvalidMoveException;
import models.Game;
import models.GameState;
import models.Player;

import java.util.List;

public class GameController {
    // Players will interact with GameController to make any operations
    // startGame(), makeMove(), getGameState(), printBoard(), getWinner()

    public Game startGame(int dimension, List<Player> players) {
        return Game.getBuilder()
                     .setDimension(dimension)
                       .setPlayers(players)
                         .build();
    }

    public void makeMove(Game game) throws InvalidMoveException {
        game.makeMove();
    }

    public GameState getGameState(Game game) {
        return game.getGameState();
    }

    public void printBoard(Game game) {
        game.displayBoard();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }
}
