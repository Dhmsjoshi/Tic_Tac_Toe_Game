package Project_TicTacToe.strategy.WinningStrategy;

import Project_TicTacToe.exception.InvalidWinningStrategyException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WinningStrategyFactory {
    public static List<WinningStrategy> getWinningStrategy(int dimension,List<Integer> winningStrategies){
        HashSet<WinningStrategy> set = new HashSet<>();
        for(int i: winningStrategies){
            if(i==1){
                set.add(new RowWinningStrategy(dimension));
            }else if(i==2){
                set.add(new ColumnWinningStrategy(dimension));
            } else if (i==3) {
                set.add(new DiagonalWinningStrategy(dimension));
            }else if(i==4){
                set.add(new CornerWinningStrategy(dimension));
            }else{
                throw new InvalidWinningStrategyException("Please enter valid number for required winning strategies!!");
            }
        }

        List<WinningStrategy> winningStrategyList = new ArrayList<>();
        for(WinningStrategy winningStrategy : set){
            winningStrategyList.add(winningStrategy);
        }

        return winningStrategyList;
    }
}
