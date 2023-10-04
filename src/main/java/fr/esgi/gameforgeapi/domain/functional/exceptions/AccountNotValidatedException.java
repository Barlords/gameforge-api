package fr.esgi.gameforgeapi.domain.functional.exceptions;

public class AccountNotValidatedException extends RuntimeException{
    public AccountNotValidatedException(String message) {
        super(message);
    }
}
