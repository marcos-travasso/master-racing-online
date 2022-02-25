/*package online.masterracing.bootstrap;

import online.masterracing.services.CircuitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

        Pilot senna = new Pilot("Ayrton Senna");
        Pilot schumacher = new Pilot("Michael Schumacher");

        Participation p1 = new Participation(senna, gp);
        Participation p2 = new Participation(schumacher, gp);

        gp.startRace();
        p1.addLap();
        TimeUnit.SECONDS.sleep(1);
        p2.addLap();
        p1.addLap();
        TimeUnit.SECONDS.sleep(2);
        p2.addLap();
        p1.addLap();
        TimeUnit.SECONDS.sleep(1);
        p2.addLap();

        Race race = new Race();
        race.setCategory("Carrinho de Compras");
        race.setDescription("Grande Prêmio de Adelaide de Compras");
        race.setLaps(2);
        race.setCircuit(adelaide);

        Participation p3 = new Participation(senna, race);

        race.startRace();
        p3.addLap();
        TimeUnit.SECONDS.sleep(5);
        p3.addLap();

        circuitService.save(adelaide);

        System.out.println("Bootstrap loaded ------------");
    }
}
*/