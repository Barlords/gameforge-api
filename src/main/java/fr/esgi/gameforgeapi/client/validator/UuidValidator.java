package fr.esgi.gameforgeapi.client.validator;

import fr.esgi.gameforgeapi.domain.functional.exceptions.UuidNotValidException;

import java.util.UUID;

public interface UuidValidator {

    static UUID validate(String id) {
        try {
            return UUID.fromString(id);
        }
        catch(Exception e) {
            throw new UuidNotValidException(id);
        }
    }

}
