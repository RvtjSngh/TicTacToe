package strategies.botPlayingStrategy;

import models.Board;
import models.Cell;
import models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {

    @Override
    public Move makeMove(Board board) {
        // Bot will iterate through the board and play the symbol at the first available cell.
        for(List<Cell> cells : board.getBoard()) {
            for(Cell cell : cells) {
                if(cell.isEmpty()) {
                    return new Move(null, cell);
                }
            }
        }
        return null;
    }
}
