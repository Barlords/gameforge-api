package fr.esgi.gameforgeapi.domain.functional.exceptions;

public class ApplicationErrorException extends RuntimeException{
    public ApplicationErrorException(String message) {
        super(message);
    }
}
