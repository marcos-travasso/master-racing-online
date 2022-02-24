package online.masterracing.controllers;

import online.masterracing.converters.RaceConverter;
import online.masterracing.model.Race;
import online.masterracing.model.RaceDTO;
import online.masterracing.model.RaceStats;
import online.masterracing.services.RaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RequestMapping("/races")
@RestController
public class RaceController {
    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping
    public @ResponseBody Set<Race> getRaces(){
        return raceService.findAll();
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<String> startRace(@PathVariable Long id){
        try{
            raceService.startRace(id);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/{id}/stats")
    @ResponseBody
    public RaceStats getStats(@PathVariable Long id){
        RaceStats stats;
        try{
            stats = raceService.getStats(id);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        }

        return stats;
    }

    @PostMapping
    @ResponseBody
    public RaceDTO postPilot(@RequestBody RaceDTO raceDTO){
        Race race = RaceConverter.convertToRace(raceDTO);
        raceService.save(race);

        return RaceConverter.convertToDTO(race);
    }
}
