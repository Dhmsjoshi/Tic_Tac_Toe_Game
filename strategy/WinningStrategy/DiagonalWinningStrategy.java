package Project_TicTacToe.strategy.WinningStrategy;

import Project_TicTacToe.Models.Board;
import Project_TicTacToe.Models.Move;
import Project_TicTacToe.Models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiagonalWinningStrategy implements WinningStrategy{
    private int dimension;

    HashMap<Character,Integer> topRightdiagonalSymbolCount = new HashMap<>();
    HashMap<Character,Integer> bottomRightdiagonalSymbolCount = new HashMap<>();

    public DiagonalWinningStrategy(int dimension) {
        this.dimension = dimension;

    }

    public boolean isCellTopRightDiagonal(int row, int col){
        return row==col;
    }

    public boolean isCellBottomRightDiagonal(int row, int col){
        return (row+col)==dimension-1;
    }
    @Override
    public Player checkWinner(Board board, Move lastMove) {


        Player lastMovePLayer = lastMove.getPlayer();
        char symbol = lastMovePLayer.getSymbol().getSymbolChar();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();


        if(checkForDiagonals( row,col, symbol,lastMove) != null){
            return lastMovePLayer;
        }


        return null;

    }

    private Player checkForDiagonals(int row, int col, char symbol, Move lastMove){
        //topRightDiagonal
        if(isCellTopRightDiagonal(row, col)){
            if(!topRightdiagonalSymbolCount.containsKey(symbol)){
                topRightdiagonalSymbolCount.put(symbol,0);
            }

            topRightdiagonalSymbolCount.put(symbol, topRightdiagonalSymbolCount.get(symbol)+1);
            if(topRightdiagonalSymbolCount.get(symbol)==dimension){
                return lastMove.getPlayer();
            }
        }


        //BottomRightDiagonal
        if(isCellBottomRightDiagonal(row, col)){
            if(!bottomRightdiagonalSymbolCount.containsKey(symbol)){
                bottomRightdiagonalSymbolCount.put(symbol,0);
            }

            bottomRightdiagonalSymbolCount.put(symbol, bottomRightdiagonalSymbolCount.get(symbol)+1);
            if(bottomRightdiagonalSymbolCount.get(symbol)==dimension){
                return lastMove.getPlayer();
            }
        }

        return null;
    }
}
