package online.masterracing.bootstrap;

import online.masterracing.model.Circuit;
import online.masterracing.model.Participation;
import online.masterracing.model.Pilot;
import online.masterracing.model.Race;
import online.masterracing.services.CircuitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class BootStrapData implements CommandLineRunner {

    public BootStrapData(CircuitService circuitService) {
        this.circuitService = circuitService;
    }

    private final CircuitService circuitService;

    @Override
    public void run(String... args) throws Exception {
        Circuit adelaide = new Circuit();
        adelaide.setName("Circuito de Rua de Adelaide");

        Race gp = new Race();
        gp.setCategory("F1");
        gp.setDescription("Grande Prêmio da Austrália de 1993");
        gp.setLaps(3);
        gp.setCircuit(adelaide);

        Pilot senna = new Pilot();
        senna.setName("Ayrton Senna");
        Pilot schumacher = new Pilot();
        schumacher.setName("Michael Schumacher");

        Participation participation1 = new Participation();
        participation1.setPilot(senna);
        participation1.setRace(gp);

        Participation participation2 = new Participation();
        participation2.setPilot(schumacher);
        participation2.setRace(gp);

        gp.startRace();
        participation1.addLap();
        TimeUnit.SECONDS.sleep(1);
        participation2.addLap();
        participation1.addLap();
        TimeUnit.SECONDS.sleep(2);
        participation2.addLap();
        participation1.addLap();
        TimeUnit.SECONDS.sleep(1);
        participation2.addLap();

        Race race = new Race();
        race.setCategory("Carrinho de Compras");
        race.setDescription("Grande Prêmio de Adelaide de Compras");
        race.setLaps(2);
        race.setCircuit(adelaide);

        Participation participation3 = new Participation();
        participation3.setRace(race);
        participation3.setPilot(senna);

        race.startRace();
        participation3.addLap();
        TimeUnit.SECONDS.sleep(5);
        participation3.addLap();

        circuitService.save(adelaide);

        System.out.println("Bootstrap loaded ------------");
    }
}
