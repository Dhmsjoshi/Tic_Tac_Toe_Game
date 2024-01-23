package Project_TicTacToe.strategy.WinningStrategy;

import Project_TicTacToe.Models.Board;
import Project_TicTacToe.Models.Move;
import Project_TicTacToe.Models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CornerWinningStrategy implements WinningStrategy{
    private int dimension;


    HashMap<Character,Integer> cornerSymbolCount = new HashMap<>();

    public CornerWinningStrategy(int dimension) {
        this.dimension = dimension;
    }

    public boolean isCellCorner(int row, int col){
        if(row==0 || row==dimension-1){
            return col==0 || col==dimension-1;
        }
        return false;
    }
    @Override
    public Player checkWinner(Board board, Move lastMove) {


        Player lastMovePLayer = lastMove.getPlayer();
        char symbol = lastMovePLayer.getSymbol().getSymbolChar();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();


        if(checkForCorners( row,col, symbol,lastMove) != null){
            return lastMovePLayer;
        }

        return null;

    }

    private Player checkForCorners(int row, int col, char symbol, Move lastMove){
        if(isCellCorner(row, col)){
            if(!cornerSymbolCount.containsKey(symbol)){
                cornerSymbolCount.put(symbol,0);
            }

            cornerSymbolCount.put(symbol, cornerSymbolCount.get(symbol)+1);
            if(cornerSymbolCount.get(symbol)==dimension){
                return lastMove.getPlayer();
            }
        }

        return null;
    }
}
