package online.masterracing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PilotNotFoundException extends RuntimeException {
    public PilotNotFoundException() {
        super("Pilot not found");
    }
}
