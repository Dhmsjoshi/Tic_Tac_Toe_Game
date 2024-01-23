package Project_TicTacToe.strategy.BotPlayingStrategy;

import Project_TicTacToe.Models.Board;
import Project_TicTacToe.Models.Move;
import Project_TicTacToe.Models.Player;

public interface BotPlayingStrategy {
    public Move makeMove(Player player, Board board);
}
