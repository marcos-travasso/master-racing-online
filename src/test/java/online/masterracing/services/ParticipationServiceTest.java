package online.masterracing.services;

import online.masterracing.exceptions.NotStartedRaceException;
import online.masterracing.exceptions.PilotAlreadyInRaceException;
import online.masterracing.exceptions.PilotFinishedRaceException;
import online.masterracing.model.Lap;
import online.masterracing.model.Participation;
import online.masterracing.model.Pilot;
import online.masterracing.model.Race;
import online.masterracing.repositories.ParticipationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ParticipationServiceTest {

    @Mock
    private ParticipationRepository participationRepository;
    @Mock
    private RaceService raceService;
    @Mock
    private PilotService pilotService;
    @Mock

    private ParticipationService participationService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        participationService = new ParticipationServiceImpl(participationRepository, raceService, pilotService);
    }

    @Test
    public void Test_AddValidLap(){
        Participation participationSpy = spy(Participation.class);
        Race race = new Race();
        participationSpy.setRace(race);

        participationSpy.getRace().setLaps(1);
        participationSpy.getRace().startRace();

        when(participationRepository.findById(anyLong())).thenReturn(Optional.of(participationSpy));

        participationService.addLap(1L);

        verify(participationSpy, times(1)).addLap();
    }

    @Test
    public void Test_AddLapToRaceNotStarted(){
        Participation participation = new Participation(new Pilot(), new Race());

        when(participationRepository.findById(anyLong())).thenReturn(Optional.of(participation));

        assertThrows(NotStartedRaceException.class, () -> {
            participationService.addLap(1L);
        });
    }

    @Test
    public void Test_AddLapToFinishedRace(){
        Participation participation = new Participation(new Pilot(), new Race());
        participation.getRace().startRace();

        when(participationRepository.findById(anyLong())).thenReturn(Optional.of(participation));

        assertThrows(PilotFinishedRaceException.class, () -> {
            participationService.addLap(1L);
        });
    }

    @Test
    public void Test_AddPilotAlreadyInRace(){
        Pilot pilot = new Pilot();
        pilot.setId(1L);
        Race race = new Race();
        race.setId(1L);
        Participation participation = new Participation(pilot, race);

        participation.setPilot(pilot);
        participation.setRace(race);

        when(pilotService.findById(anyLong())).thenReturn(pilot);
        when(raceService.findById(anyLong())).thenReturn(race);

        assertThrows(PilotAlreadyInRaceException.class, () -> {
            participationService.addParticipation(participation);
        });
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

        Assert.assertEquals(pilot1, race.getWinner().orElse(null));
    }

    @Test
    public void Test_GetWinner_WhenRaceHasNoWinner(){
        Race race = new Race();
        race.setLaps(2);

        Pilot pilot1 = new Pilot();
        Participation participation1 = new Participation(pilot1, race);
        race.setStartTime(Instant.now());
        participation1.addLap();

        Assert.assertNull(race.getWinner().orElse(null));
    }

    @Test
    public void Test_GetWinner_FromNotStartedRace(){
        Participation participation = new Participation(new Pilot(), new Race());
        Race race = participation.getRace();

        assertThrows(NotStartedRaceException.class, race::getWinner);
    }
}
