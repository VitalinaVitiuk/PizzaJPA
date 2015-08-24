package web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Winnie on 8/10/2015.
 */
@ResponseStatus(
        value = HttpStatus.NOT_FOUND,
        reason = "Pizza not found")
public class NotFoundPizzaException extends RuntimeException {

    public NotFoundPizzaException(String string) {
        super(string);
    }

}
