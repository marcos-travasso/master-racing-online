package online.masterracing.controllers;

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
    public @ResponseBody RaceStats getStats(@PathVariable Long id){
        RaceStats stats;
        try{
            stats = pilotService.getStats(id);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        }

        return stats;
    }
}
