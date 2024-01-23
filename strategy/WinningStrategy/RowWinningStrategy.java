package Project_TicTacToe.strategy.WinningStrategy;

import Project_TicTacToe.Models.Board;
import Project_TicTacToe.Models.Move;
import Project_TicTacToe.Models.Player;
import Project_TicTacToe.exception.GameDrawnException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RowWinningStrategy implements WinningStrategy{
    private int dimension;
    private int symbolsAdded;

    List<HashMap<Character,Integer>> rowSymbolCount = new ArrayList<>();

    public RowWinningStrategy(int dimension) {
        this.dimension = dimension;
        this.symbolsAdded= 0;

        for(int i = 0; i<dimension; i++){
            rowSymbolCount.add(new HashMap<>());
        }

    }
    @Override
    public Player checkWinner(Board board, Move lastMove) {
        symbolsAdded++;

        Player lastMovePLayer = lastMove.getPlayer();
        char symbol = lastMovePLayer.getSymbol().getSymbolChar();
        int row = lastMove.getCell().getRow();



        if(checkForRows( row, symbol,lastMove) != null){
            return lastMovePLayer;
        }


        return null;

    }

    private Player checkForRows(int row, char symbol, Move lastMove){
        if(!rowSymbolCount.get(row).containsKey(symbol)){
            rowSymbolCount.get(row).put(symbol,0);
        }

        rowSymbolCount.get(row).put(symbol, rowSymbolCount.get(row).get(symbol)+1);
        if(rowSymbolCount.get(row).get(symbol)==dimension){
            return lastMove.getPlayer();
        }

        return null;
    }
}
