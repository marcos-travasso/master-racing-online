package online.masterracing.controllers;

import online.masterracing.converters.PilotConverter;
import online.masterracing.model.Pilot;
import online.masterracing.model.PilotDTO;
import online.masterracing.model.RaceStats;
import online.masterracing.services.PilotService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/pilots")
@RestController
public class PilotController {
    private final PilotService pilotService;

    public PilotController(PilotService pilotService) {
        this.pilotService = pilotService;
    }

    @GetMapping("/{id}/stats")
    @ResponseBody
    public RaceStats getStats(@PathVariable Long id){
        RaceStats stats;
        try{
            stats = pilotService.getStats(id);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        }

        return stats;
    }

    @PostMapping
    @ResponseBody
    public PilotDTO postPilot(@RequestBody PilotDTO pilotDTO){
        Pilot pilot = PilotConverter.convertToPilot(pilotDTO);
        pilotService.save(pilot);

        return PilotConverter.convertToDTO(pilot);
    }
}
