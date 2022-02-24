package online.masterracing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RaceAlreadyStartedException extends RuntimeException{
    public RaceAlreadyStartedException() {
        super("Race already started");
    }
}
