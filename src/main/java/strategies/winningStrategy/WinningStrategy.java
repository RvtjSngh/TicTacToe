package strategies.winningStrategy;

import models.Board;
import models.Move;
import models.Player;

public interface WinningStrategy {

    boolean checkWinner(Move move, Board board);
}
