package fr.esgi.gameforgeapi.domain.functional.exceptions;

public class TokenNotValidException extends RuntimeException{
    public TokenNotValidException(String token) {
        super("Le token '"+ token + "' n'est pas valide");
    }
}
