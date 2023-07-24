package fr.esgi.gameforgeapi.domain.functional.exceptions;

public class NameAlreadyUsedException extends RuntimeException{
    public NameAlreadyUsedException(String message) {
        super(message);
    }
}
