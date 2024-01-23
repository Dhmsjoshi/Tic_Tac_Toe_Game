package Project_TicTacToe.strategy.WinningStrategy;

import Project_TicTacToe.Models.Board;
import Project_TicTacToe.Models.Move;
import Project_TicTacToe.Models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ColumnWinningStrategy implements WinningStrategy{
    private int dimension;


    List<HashMap<Character,Integer>> colSymbolCount = new ArrayList<>();

    public ColumnWinningStrategy(int dimension) {
        this.dimension = dimension;
        for(int i = 0; i<dimension; i++){
            colSymbolCount.add(new HashMap<>());
        }
    }
    @Override
    public Player checkWinner(Board board, Move lastMove) {


        Player lastMovePLayer = lastMove.getPlayer();
        char symbol = lastMovePLayer.getSymbol().getSymbolChar();

        int col = lastMove.getCell().getCol();


        if(checkForColumns( col, symbol,lastMove) != null){
            return lastMovePLayer;
        }


        return null;

    }

    private Player checkForColumns(int col, char symbol, Move lastMove){
        if(!colSymbolCount.get(col).containsKey(symbol)){
            colSymbolCount.get(col).put(symbol,0);
        }

        colSymbolCount.get(col).put(symbol, colSymbolCount.get(col).get(symbol)+1);
        if(colSymbolCount.get(col).get(symbol)==dimension){
            return lastMove.getPlayer();
        }

        return null;
    }
}
