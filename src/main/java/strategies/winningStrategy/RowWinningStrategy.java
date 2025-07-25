package strategies.winningStrategy;

import models.Board;
import models.Move;
import models.Player;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {
    private Map<Integer, Map<Symbol, Integer>> rowMap = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        if(!rowMap.containsKey(row)) {
            rowMap.put(row, new HashMap<>());
        }

        Map<Symbol, Integer> currentRowMap = rowMap.get(row);

        if(!currentRowMap.containsKey(symbol)) {
            currentRowMap.put(symbol, 0);
        }
        currentRowMap.put(symbol, currentRowMap.get(symbol) + 1);

        return currentRowMap.get(symbol) == board.getDimension();
    }
}
