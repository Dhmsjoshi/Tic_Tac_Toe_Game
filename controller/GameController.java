package Project_TicTacToe.controller;

import Project_TicTacToe.Models.*;
import Project_TicTacToe.strategy.WinningStrategy.WinningStrategy;
import Project_TicTacToe.strategy.WinningStrategy.WinningStrategyFactory;

import java.util.List;

public class GameController {

    public Game createGame(int dimension, List<Player> players, List<Integer> winningStrategies){
        try{
            return Game.builder()
                    .setDimension(dimension)
                    .setPlayers(players)
                    .setWinningStrategies(WinningStrategyFactory.getWinningStrategy(dimension,winningStrategies))
                    .build();
        }catch(Exception e){
            System.out.println("Could not build the game, something went wrong !!");
        }
        return null;
    }

    public void displayBoard(Game game){
        game.getBoard().printBoard();
    }

    public GameState gameState(Game game){
        return game.getGameState();
    }

    private void updateGameMoves(Game game, Move move){
        game.getMoves().add(move);
    }
    public Move executeMove(Game game, Player player){
        Move move = player.makeMove(game.getBoard());
        updateGameMoves(game, move);
        return move;
    }

    public Player checkWinner(Game game, Move recentMove){
        for(WinningStrategy winningStrategy : game.getWinningStrategies()){
            Player player = winningStrategy.checkWinner(game.getBoard(), recentMove);
            if(player != null){
                return player;
            }
        }
        return null;
    }

    public String getWinner(Game game){
        return game.getWinner().getName();
    }

}
