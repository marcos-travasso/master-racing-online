package online.masterracing.services;

import online.masterracing.exceptions.NotStartedRaceException;
import online.masterracing.exceptions.PilotAlreadyInRaceException;
import online.masterracing.exceptions.PilotFinishedRaceException;
import online.masterracing.model.Participation;
import online.masterracing.model.Pilot;
import online.masterracing.model.Race;
import online.masterracing.repositories.ParticipationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        race.setLaps(1);
        race.startRace();
        participationSpy.setRace(race);

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

        when(pilotService.findById(anyLong())).thenReturn(pilot);
        when(raceService.findById(anyLong())).thenReturn(race);

        assertThrows(PilotAlreadyInRaceException.class, () -> {
            participationService.addParticipation(participation);
        });
    }
}
