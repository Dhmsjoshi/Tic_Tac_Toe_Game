package Project_TicTacToe.strategy.WinningStrategy;

import Project_TicTacToe.Models.Board;
import Project_TicTacToe.Models.Move;
import Project_TicTacToe.Models.Player;

public interface WinningStrategy {
    Player checkWinner(Board board, Move lastMove);
}
