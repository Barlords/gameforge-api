package fr.esgi.gameforgeapi.domain.functional.exceptions;

public class NotFoundUserException extends RuntimeException{
    public NotFoundUserException(String message) {
        super(message);
    }
}
