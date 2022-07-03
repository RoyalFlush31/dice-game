package Exceptions;

public class FieldIsCrossedOutException extends Exception{
    public FieldIsCrossedOutException(String errorMessage) {
        super(errorMessage);
    }
}