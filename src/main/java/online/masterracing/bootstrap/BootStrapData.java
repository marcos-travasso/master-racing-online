package online.masterracing.bootstrap;

import online.masterracing.model.Circuit;
import online.masterracing.model.Participation;
import online.masterracing.model.Pilot;
import online.masterracing.model.Race;
import online.masterracing.services.ParticipationServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final ParticipationServiceImpl participationService;

    public BootStrapData(ParticipationServiceImpl participationService) {
        this.participationService = participationService;
    }

    @Override
    public void run(String... args) throws Exception {
        Circuit adelaide = new Circuit();
        adelaide.setName("Circuito de Rua de Adelaide");

        Race gp = new Race();
        gp.setCategory("F1");
        gp.setDescription("Grande Prêmio da Austrália de 1993");

        gp.setCircuit(adelaide);

        Pilot senna = new Pilot();
        senna.setName("Ayrton Senna");
        Pilot schumacher = new Pilot();
        schumacher.setName("Michael Schumacher");

        Participation participation1 = new Participation();
        participation1.setPilot(senna);
        participation1.setRace(gp);

        /*gp.startRace();
        TimeUnit.SECONDS.sleep(1);
        participation1.addLap();*/

        participationService.save(participation1);
    }
}
