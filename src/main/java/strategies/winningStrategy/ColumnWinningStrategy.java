package strategies.winningStrategy;

import models.Board;
import models.Move;
import models.Player;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy {
    private Map<Integer, Map<Symbol, Integer>> colMap = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        int col = move.getCell().getColumn();
        Symbol symbol = move.getPlayer().getSymbol();

        // If entire row is empty and i am the first one to make a move, initialise the hashMap.
        if(!colMap.containsKey(col)) {
            colMap.put(col, new HashMap<>());
        }

        Map<Symbol, Integer> currentColMap = colMap.get(col); // get the current row hashMap.

        // Check if symbol is there or not, if not, initialise the count with 0, otherwise increase the count by 1.
        if(!currentColMap.containsKey(symbol)) {
            currentColMap.put(symbol, 0);
        }
        currentColMap.put(symbol, currentColMap.get(symbol) + 1);

        // Check winner or not, by comparing the count of symbol with the dimension.
        return currentColMap.get(symbol) == board.getDimension();
    }
}
