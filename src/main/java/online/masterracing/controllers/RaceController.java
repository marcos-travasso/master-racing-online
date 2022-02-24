package online.masterracing.controllers;

import online.masterracing.converters.RaceConverter;
import online.masterracing.exceptions.NotFoundException;
import online.masterracing.exceptions.RaceAlreadyStartedException;
import online.masterracing.model.Race;
import online.masterracing.model.RaceDTO;
import online.masterracing.model.RaceStats;
import online.masterracing.services.RaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/races")
@RestController
public class RaceController {
    private final RaceService raceService;
    private static final String RACE_NOT_FOUND = "Race not found";

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
        } catch (RaceAlreadyStartedException e){
            return new ResponseEntity<>("Race already started", HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e){
            return new ResponseEntity<>(RACE_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Race started", HttpStatus.OK);
    }

    @GetMapping("/{id}/stats")
    @ResponseBody
    public ResponseEntity<?> getStats(@PathVariable Long id){
        RaceStats stats;
        try{
            stats = raceService.getStats(id);
        } catch (NotFoundException e){
            return new ResponseEntity<>(RACE_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public RaceDTO postPilot(@RequestBody RaceDTO raceDTO){
        Race race = RaceConverter.convertToRace(raceDTO);
        raceService.save(race);

        return RaceConverter.convertToDTO(race);
    }
}
