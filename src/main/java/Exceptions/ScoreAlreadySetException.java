package Exceptions;

public class ScoreAlreadySetException extends Exception {
    public ScoreAlreadySetException(String errorMessage){
        super(errorMessage);
    }
}