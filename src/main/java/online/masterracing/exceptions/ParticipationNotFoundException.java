package online.masterracing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ParticipationNotFoundException extends RuntimeException {
    public ParticipationNotFoundException() {
        super("Participation not found");
    }
}
