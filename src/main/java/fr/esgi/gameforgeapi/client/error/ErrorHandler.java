package fr.esgi.gameforgeapi.client.error;

import fr.esgi.gameforgeapi.domain.functional.exceptions.EmailAlreadyUsedException;
import fr.esgi.gameforgeapi.domain.functional.exceptions.NotFoundUserException;
import fr.esgi.gameforgeapi.domain.functional.exceptions.PseudoAlreadyUsedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundUserException.class)
    public ErrorDto handleNotFoundRentalCarException(NotFoundUserException notFoundUserException) {
        return new ErrorDto(notFoundUserException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDto handleMethodArgumentNotValidException() {
        return new ErrorDto("La requête envoyée est invalide");
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

}
