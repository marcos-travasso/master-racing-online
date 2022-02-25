package online.masterracing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PilotAlreadyInRaceException extends RuntimeException{
    public PilotAlreadyInRaceException() {
        super("Pilot is already in the race");
    }
}
