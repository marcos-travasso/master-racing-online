package online.masterracing.controllers;

import online.masterracing.converters.PilotConverter;
import online.masterracing.converters.RaceConverter;
import online.masterracing.exceptions.NotFoundException;
import online.masterracing.exceptions.RaceAlreadyStartedException;
import online.masterracing.model.*;
import online.masterracing.services.RaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
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
    public ResponseEntity<RaceStats> getStats(@PathVariable Long id){
        RaceStats stats = raceService.getStats(id);

        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RaceDTO> postRace(@RequestBody RaceDTO raceDTO){
        Race race = RaceConverter.convertToRace(raceDTO);
        raceService.save(race);

        return new ResponseEntity<>(RaceConverter.convertToDTO(race), HttpStatus.OK);
    }

    @GetMapping("/{id}/winner")
    @Nullable
    public ResponseEntity<PilotDTO> getWinner(@PathVariable Long id){
        Optional<Pilot> optionalWinner = raceService.getWinner(id);

        if(optionalWinner.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        return new ResponseEntity<>(PilotConverter.convertToDTO(optionalWinner.get()), HttpStatus.OK);
    }
}
