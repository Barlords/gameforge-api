package fr.esgi.gameforgeapi.domain.functional.exceptions;

public class FriendAlreadyExistException extends RuntimeException{
    public FriendAlreadyExistException(String pseudo) {
        super(pseudo + " est déjà votre ami");
    }
}
