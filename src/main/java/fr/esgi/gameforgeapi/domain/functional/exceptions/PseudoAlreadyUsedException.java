package fr.esgi.gameforgeapi.domain.functional.exceptions;

public class PseudoAlreadyUsedException extends RuntimeException{
    public PseudoAlreadyUsedException(String message) {
        super(message);
    }
}
