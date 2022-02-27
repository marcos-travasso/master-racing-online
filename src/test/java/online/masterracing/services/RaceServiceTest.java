package online.masterracing.services;

import online.masterracing.exceptions.NotStartedRaceException;
import online.masterracing.model.*;
import online.masterracing.repositories.RaceRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class RaceServiceTest {

    @Mock
    private RaceRepository raceRepository;
    @Mock
    private CircuitService circuitService;

    RaceService raceService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        raceService = new RaceServiceImpl(raceRepository, circuitService);
    }

    @Test
    public void Test_StartRace(){
        Race race = new Race();
        Circuit circuit = new Circuit();

        race.setCircuit(circuit);
        circuit.setId(1L);

        when(raceRepository.findById(anyLong())).thenReturn(Optional.of(race));
        when(circuitService.findById(any())).thenReturn(circuit);

        raceService.startRace(1L);

        assertNotNull(race.getStartTime());
    }

    @Test
    public void Test_GetWinner_WhenRaceHasAWinner(){
        Race race = new Race();
        race.setLaps(1);

        Pilot pilot1 = new Pilot();
        Pilot pilot2 = new Pilot();
        Participation participation1 = new Participation(pilot1, race);
        Participation participation2 = new Participation(pilot2, race);
        race.setStartTime(Instant.now());

        participation1.setLaps(new ArrayList<>());
        Lap lap1 = new Lap();
        lap1.setTimeElapsed(1L);
        participation1.getLaps().add(lap1);

        participation2.setLaps(new ArrayList<>());
        Lap lap2 = new Lap();
        lap2.setTimeElapsed(2L);
        participation2.getLaps().add(lap2);

        when(raceRepository.findById(anyLong())).thenReturn(Optional.of(race));

        assertEquals(pilot1, raceService.getWinner(1L).orElse(null));
    }

    @Test
    public void Test_GetWinner_WhenRaceHasNoWinner(){
        Race race = new Race();
        race.setLaps(2);

        Pilot pilot1 = new Pilot();
        Participation participation1 = new Participation(pilot1, race);
        race.setStartTime(Instant.now());
        participation1.addLap();

        when(raceRepository.findById(anyLong())).thenReturn(Optional.of(race));

        assertNull(raceService.getWinner(1L).orElse(null));
    }

    @Test
    public void Test_GetWinner_FromNotStartedRace(){
        Participation participation = new Participation(new Pilot(), new Race());
        Race race = participation.getRace();

        when(raceRepository.findById(anyLong())).thenReturn(Optional.of(race));

        assertThrows(NotStartedRaceException.class, () -> {
            raceService.getWinner(1L);
        });
    }
}
