package fr.esgi.gameforgeapi.domain.functional.exceptions;

public class CodeValidationDoesntExistException extends RuntimeException{
    public CodeValidationDoesntExistException(String message) {
        super(message);
    }
}
