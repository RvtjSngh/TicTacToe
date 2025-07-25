package strategies.winningStrategy;

import models.Board;
import models.Move;
import models.Player;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy {
    Map<Symbol, Integer> leftMap = new HashMap<>();
    Map<Symbol, Integer> rightMap = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();
        Symbol symbol = move.getPlayer().getSymbol();

        // left diagonal has row = col i.e (0,0) (1,1) (2,2)
        if(row == col) {
            if(!leftMap.containsKey(symbol)) {
                leftMap.put(symbol, 0);
            }
            leftMap.put(symbol, leftMap.get(symbol) + 1);

            if(leftMap.get(symbol) == board.getDimension()) {
                return true;
            }
        }

        // Right diagonal is r + c = dimension - 1, like (0,2) (1,1) (2,0)
        if(row + col == board.getDimension() - 1) {
            if(!rightMap.containsKey(symbol)) {
                rightMap.put(symbol, 0);
            }
            rightMap.put(symbol, rightMap.get(symbol) + 1);

            if(rightMap.get(symbol) == board.getDimension()) {
                return true;
            }
        }

        return false;
    }
}
