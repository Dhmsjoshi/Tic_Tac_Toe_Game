package Project_TicTacToe.exception;

public class GameDrawnException extends RuntimeException{
    public GameDrawnException(String message) {
        super(message);
    }
}
