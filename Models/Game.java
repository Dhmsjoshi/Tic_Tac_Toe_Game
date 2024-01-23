package Project_TicTacToe.Models;

import Project_TicTacToe.exception.InvalidBotCountException;
import Project_TicTacToe.exception.InvalidDimensionException;
import Project_TicTacToe.exception.InvalidPlayerCountException;
import Project_TicTacToe.exception.InvalidUniqueSymbolException;
import Project_TicTacToe.strategy.WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private List<Player> players;
    private List<Move> moves;
    private Board board;
    private Player winner;
    private GameState gameState;
    private List<WinningStrategy> winningStrategies;

    private Game(Builder builder) {
        this.players =builder.players;
        this.moves = new ArrayList<Move>();
        this.board = new Board(builder.dimension);
        this.gameState = GameState.IN_PROGRESS;
        this.winningStrategies = builder.winningStrategies;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public Board getBoard() {
        return board;
    }

    public Player getWinner() {
        return winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private List<Player> players;
        private int dimension;
        private List<WinningStrategy> winningStrategies;

//        private Builder() {
//
//            this.players = new ArrayList<Player>();
//            this.dimension = 0;
//            this.winningStrategies = new ArrayList<WinningStrategy>();
//        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }


        public void addPlayer(Player player){
            players.add(player);
        }

        public void addWinningStrategy(WinningStrategy winningStrategy){
            winningStrategies.add(winningStrategy);
        }

        private void validateBotCount(){
            int botCount = 0;
            for(Player player : players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if(botCount>1){
                throw new InvalidBotCountException("Bot count has exceeded 1.!!");
            }

        }

        private void validateDimension(){
            if(dimension <3 || dimension>10){
                throw new InvalidDimensionException("Dimension of board should be grater than 3 or less than 10.");
            }
        }

        private void validatePlayerCount(){
            if(players.size() != dimension-1){
                throw new InvalidPlayerCountException("Number of player should be 1 less than dimension!");
            }
        }

        private  void validatePlayerSymbol(){
            HashSet<Character> set =new HashSet<>();
            for(Player player : players){
                set.add(player.getSymbol().getSymbolChar());
            }
            if(set.size() != players.size()){
                throw new InvalidUniqueSymbolException("Every player should have unique symbol.!!!");
            }
        }

        private void validate(){
            validateBotCount();
            validateDimension();
            validatePlayerCount();
            validatePlayerSymbol();

        }

        public Game build(){
            validate();
            return new Game(this);
        }






    }

}
