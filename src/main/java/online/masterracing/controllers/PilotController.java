package online.masterracing.controllers;

import online.masterracing.converters.PilotConverter;
import online.masterracing.exceptions.NotFoundException;
import online.masterracing.model.Pilot;
import online.masterracing.model.PilotDTO;
import online.masterracing.model.RaceStats;
import online.masterracing.services.PilotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/pilots")
@RestController
public class PilotController {
    private final PilotService pilotService;

    public PilotController(PilotService pilotService) {
        this.pilotService = pilotService;
    }

    @GetMapping("/{id}/stats")
    public ResponseEntity<?> getStats(@PathVariable Long id){
        RaceStats stats;
        try{
            stats = pilotService.getStats(id);
        } catch (NotFoundException e){
            return new ResponseEntity<>("Pilot not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

    @PostMapping
    public PilotDTO postPilot(@RequestBody PilotDTO pilotDTO){
        Pilot pilot = PilotConverter.convertToPilot(pilotDTO);
        pilotService.save(pilot);

        return PilotConverter.convertToDTO(pilot);
    }
}
