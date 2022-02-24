package online.masterracing.controllers;

import online.masterracing.services.ParticipationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/races")
@RestController
public class ParticipationController {
    private final ParticipationService participationService;

    public ParticipationController(ParticipationService participationService) {
        this.participationService = participationService;
    }

    //Aqui eu pensei em fazer /{raceId}/pilots/{pilotId}/lap
    //daí instanciar um objeto Participation e colocar os ids,
    //mas acho que ficaria uma bagunça, então optei por deixar
    //o id da participation de uma vez.
    @PostMapping("/{id}/lap")
    public ResponseEntity<HttpStatus> postLap(@PathVariable Long id){
        try{
            participationService.addLap(id);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}