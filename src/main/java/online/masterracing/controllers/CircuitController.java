package online.masterracing.controllers;

import online.masterracing.converters.CircuitConverter;
import online.masterracing.model.Circuit;
import online.masterracing.model.CircuitDTO;
import online.masterracing.services.CircuitService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/circuits")
@RestController
public class CircuitController {
    private final CircuitService circuitService;

    public CircuitController(CircuitService circuitService) {
        this.circuitService = circuitService;
    }

    @PostMapping
    @ResponseBody
    public CircuitDTO postCircuit(@RequestBody CircuitDTO circuitDTO){
        Circuit circuit = CircuitConverter.convertToCircuit(circuitDTO);
        circuitService.save(circuit);

        return CircuitConverter.convertToDTO(circuit);
    }
}
