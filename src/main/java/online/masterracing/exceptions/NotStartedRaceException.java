package online.masterracing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotStartedRaceException extends RuntimeException{
    public NotStartedRaceException() {
        super("Race is not started");
    }
}
