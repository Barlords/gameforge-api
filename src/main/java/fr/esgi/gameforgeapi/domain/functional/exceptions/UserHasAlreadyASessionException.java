package fr.esgi.gameforgeapi.domain.functional.exceptions;

public class UserHasAlreadyASessionException extends RuntimeException{
    public UserHasAlreadyASessionException(String message) {
        super(message);
    }
}
