package Project_TicTacToe.strategy.BotPlayingStrategy;

import Project_TicTacToe.Models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategy(String level){
        BotDifficultyLevel botDifficultyLevel = BotDifficultyLevel.valueOf(level);
        return switch (botDifficultyLevel){
            case E -> new RandomBotPlayingStrategy();
            case M -> new RandomBotPlayingStrategy();
            case H -> new RandomBotPlayingStrategy();
        };
    }
}
