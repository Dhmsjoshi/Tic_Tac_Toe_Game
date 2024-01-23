package Project_TicTacToe;

import Project_TicTacToe.Models.*;
import Project_TicTacToe.controller.GameController;
import Project_TicTacToe.exception.GameDrawnException;
import Project_TicTacToe.strategy.BotPlayingStrategy.BotPlayingStrategyFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {

    Scanner scn = new Scanner(System.in);
    GameController gameController = new GameController();


    //_________________________________________________________________
        System.out.println("Please enter the dimensions of the game: ");
        int dimension = scn.nextInt();

        System.out.println("Will there be any BOT int the game? Y/N ");
        char isBotPresent = scn.next().charAt(0);

    //___________________________________________________________________
    //Taking list of players
    List<Player> players = new ArrayList<>();

    //Checking how many players:
    int playerCount = dimension-1;
    if(isBotPresent=='Y'){
        playerCount = dimension-2;
    }

    //Adding players to list
        for(int i = 0; i<playerCount; i++){
            System.out.println("What is the name of the player number : "+(i+1));
            String playerName = scn.next();
            System.out.println("What is the symbol of the player "+playerName);
            char symbol = scn.next().charAt(0);

            players.add(new Player(playerName,new Symbol(symbol),PlayerType.HUMAN));
        }


    //Ading if there is a BOT
        if(isBotPresent=='Y'){
            System.out.println("What is the name of the BOT ");
            String playerName = scn.next();
            System.out.println("What is the symbol of the BOT "+playerName);
            char symbol = scn.next().charAt(0);
            System.out.println("Please enter difficulty level of BOT: EASY/MEDIUM/HARD ->  E/M/H");
            String botLevel = String.valueOf(scn.next().charAt(0));
            players.add(new Bot(playerName,new Symbol(symbol),BotDifficultyLevel.valueOf(botLevel), BotPlayingStrategyFactory.getBotPlayingStrategy(botLevel)));
        }

    //Adding winning strategies
        System.out.println("We have currently four winning strategies: ROW, COLUMN, DIAGONALS, CORNERS..");
        System.out.println("Please enter numbers of winning strategies you want to add: ");
        int strategyCount = scn.nextInt();
        System.out.println("1-> Row wise win , 2-> column wise win, 3-> diagonals win, 4-> 4 corners win");
        List<Integer> strategyNumbersList = new ArrayList<>();
        for(int i = 0; i<strategyCount; i++){
            strategyNumbersList.add(scn.nextInt());
        }

    //Shuffling the list of players
        Collections.shuffle(players);

    //Creating a new game
        Game game = gameController.createGame(dimension,players,strategyNumbersList);

    //Player index, count of symbols
        int playerIndex = 0;
        int symbolCount = 0;

    //Starting of game
        while(game.getGameState().equals(GameState.IN_PROGRESS)){
            System.out.println("Current Board Status: ");
            gameController.displayBoard(game);
            if(symbolCount == dimension*dimension){
                throw new GameDrawnException("Game is drawn as all cells are filled completely.!! :( ");
            }

            playerIndex++;
            playerIndex = playerIndex % players.size();
            Move movePlayed = gameController.executeMove(game,players.get(playerIndex));
            symbolCount++;
            Player winner = gameController.checkWinner(game,movePlayed);
            if(winner != null){
                gameController.displayBoard(game);
                System.out.println("Winner is: "+ winner.getName());
                break;
            }
        }
        
    }
}
