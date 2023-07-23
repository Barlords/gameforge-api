package fr.esgi.gameforgeapi.client.error;

import fr.esgi.gameforgeapi.domain.functional.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorDto handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        return new ErrorDto(resourceNotFoundException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDto handleMethodArgumentNotValidException() {
        return new ErrorDto("La requête envoyée est invalide");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UuidNotValidException.class)
    public ErrorDto handleUuidNotValidException() {
        return new ErrorDto("Le string fourni n'est pas un UUID");
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TokenNotValidException.class)
    public ErrorDto handleTokenNotValidException() {
        return new ErrorDto("Le token fourni est invalide");
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ErrorDto handleEmailAlreadyUsedException() {
        return new ErrorDto("L'email est déjà utilisé");
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(PseudoAlreadyUsedException.class)
    public ErrorDto handlePseudoAlreadyUsedException() {
        return new ErrorDto("Le pseudo est déjà utilisé");
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(FriendAlreadyExistException.class)
    public ErrorDto handleFriendAlreadyExistException() {
        return new ErrorDto("C'est déjà votre amie");
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserHasAlreadyASessionException.class)
    public ErrorDto handleUserHasAlreadyASessionException() {
        return new ErrorDto("Vous avez deja une Session d'ouverte");
    }

}
