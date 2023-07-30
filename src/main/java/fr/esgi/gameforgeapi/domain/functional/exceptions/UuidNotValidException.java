package fr.esgi.gameforgeapi.domain.functional.exceptions;

public class UuidNotValidException extends RuntimeException{
    public UuidNotValidException(String uuid) {
        super(uuid + " n'est pas un uuid");
    }
}
