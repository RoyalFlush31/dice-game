package Exceptions;

public class WrongParameterException extends Exception {
    public WrongParameterException(String errorMessage) {
        super(errorMessage);
    }
}