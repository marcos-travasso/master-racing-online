package online.masterracing.controllers;

import online.masterracing.exceptions.NotFoundException;
import online.masterracing.exceptions.NotStartedRaceException;
import online.masterracing.exceptions.PilotFinishedRaceException;
import online.masterracing.services.ParticipationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> postLap(@PathVariable Long id){
        try{
            participationService.addLap(id);
        } catch (NotFoundException e){
            return new ResponseEntity<>("Participation not found", HttpStatus.NOT_FOUND);
        } catch (NotStartedRaceException e){
            return new ResponseEntity<>("Race was not started", HttpStatus.BAD_REQUEST);
        } catch (PilotFinishedRaceException e){
            return new ResponseEntity<>("Pilot already finished the race", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Lap added", HttpStatus.OK);
    }
}
