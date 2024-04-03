package edu.unimagdalena.api.entities.exceptions;

public class NotAbleToDeleteException extends RuntimeException {

    public NotAbleToDeleteException() {

    }

    public NotAbleToDeleteException(String message) {
        super(message);
    }

    public NotAbleToDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
