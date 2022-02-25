package online.masterracing.controllers;

import online.masterracing.converters.ParticipationConverter;
import online.masterracing.exceptions.NotFoundException;
import online.masterracing.exceptions.NotStartedRaceException;
import online.masterracing.exceptions.PilotAlreadyInRaceException;
import online.masterracing.exceptions.PilotFinishedRaceException;
import online.masterracing.model.Participation;
import online.masterracing.model.ParticipationDTO;
import online.masterracing.services.ParticipationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/participation")
    public ResponseEntity<?> postParticipation(@RequestBody ParticipationDTO participationDTO){
        Participation participation = ParticipationConverter.convertToParticipation(participationDTO);

        try {
            participationService.addParticipation(participation);
        } catch (PilotAlreadyInRaceException e){
            return new ResponseEntity<>("Pilot is already in the race", HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e){
            return new ResponseEntity<>("Pilot ou race was not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ParticipationConverter.convertToDTO(participation), HttpStatus.OK);
    }
}
