package online.masterracing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PilotFinishedRaceException extends RuntimeException{
    public PilotFinishedRaceException() {
        super("Pilot already finished race");
    }
}
