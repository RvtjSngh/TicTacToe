package models;

import exceptions.InvalidMoveException;
import strategies.winningStrategy.ColumnWinningStrategy;
import strategies.winningStrategy.DiagonalWinningStrategy;
import strategies.winningStrategy.RowWinningStrategy;
import strategies.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextPlayerIndex;
    private List<WinningStrategy> winningStrategies;

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.winner = null;
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerIndex = 0;
        this.winningStrategies = winningStrategies;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public void makeMove() throws InvalidMoveException {
        // Player which is making the move
        Player currentPlayer = players.get(nextPlayerIndex);

        System.out.println("Current player is: " + currentPlayer.getName());

        Move move = currentPlayer.makeMove(board);

        // check if move is valid or not
        if(!validateMove(move)) {
            throw new InvalidMoveException("Invalid move, Please try again");
        }

        // if move is valid, make the move on the board
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();

        System.out.println(currentPlayer.getName() + " is making a move at " + row + ", " + col);

        // Make the cell status as filled
        Cell cell = board.getBoard().get(row).get(col); // going to the cell
        cell.setCellState(CellState.FILLED); // making it filled
        cell.setPlayer(currentPlayer); // this player has made the move

        nextPlayerIndex = (nextPlayerIndex + 1) % players.size(); // we want it in circular patter, after both players have made there moves, the index should go back to 0.

        Move finalMove = new Move(currentPlayer, cell);

        // add the move to the list of moves
        moves.add(finalMove);

        // Check after this move the current player has won or not, if yes then end the game
        if(checkWinner(finalMove)) {
             winner = currentPlayer;
             gameState = GameState.ENDED;
        } else if(moves.size() == board.getBoard().size() * board.getBoard().size()) {
            // DRAW -> if there is no place to make the move on the board
             gameState = GameState.DRAW;
        }
    }

    private boolean checkWinner(Move move) {
         // Check if any player has same symbol across any row, column or diagonal
        for(WinningStrategy winningStrategy : winningStrategies) {
            if(winningStrategy.checkWinner(move, board)) {
                return true;
            }
        }

        return false;
    }

    private boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();

        if(row < 0 || row >= board.getDimension() || col < 0 || col >= board.getDimension()) {
            // Outside the board
            return false;
        }

        // Cell is empty or not
        return board.getBoard().get(row).get(col).isEmpty();
    }

    public void displayBoard() {
        this.board.displayBoard();
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;

        public int getDimension() {
            return dimension;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public void validatePlayerCount() {
            if(players == null || players.size() != dimension - 1) {
                throw new IllegalArgumentException("Number players should be dimension - 1");
            }
        }

        public void validateSymbols() {
            Set<Character> symbols = new HashSet<>();

            for(Player player : players) {
                char symbol = player.getSymbol().getaChar();
                if(!symbols.add(symbol)) {
                    throw new IllegalArgumentException("Duplicate player symbol found: " + symbol);
                }
            }
        }

        public void validateBotCount() {
            int botCount = 0;

            for(Player player : players) {
                if(player.getPlayerType() == PlayerType.BOT ) {
                    botCount++;
                    if(botCount > 1) {
                        throw new IllegalArgumentException("Only one bot is allowed per game");
                    }
                }
            }
        }

        public void validateGame() {
            // No. of players should be dimension-1
            // Players should not have same symbol
            // Only 1 bot is allowed per game
            validatePlayerCount();
            validateSymbols();
            validateBotCount();
        }

        public Game build() {
            // Validate
            validateGame();

            List<WinningStrategy> winningStrategies = new ArrayList<>();
            winningStrategies.add(new RowWinningStrategy());
            winningStrategies.add(new ColumnWinningStrategy());
            winningStrategies.add(new DiagonalWinningStrategy());

            return new Game(
                    dimension,
                    players,
                    winningStrategies
            );
        }
    }

}
