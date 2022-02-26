package online.masterracing.controllers;

import online.masterracing.converters.CircuitConverter;
import online.masterracing.model.Circuit;
import online.masterracing.model.CircuitDTO;
import online.masterracing.services.CircuitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/circuits")
@RestController
public class CircuitController {
    private final CircuitService circuitService;

    public CircuitController(CircuitService circuitService) {
        this.circuitService = circuitService;
    }

    @PostMapping
    public ResponseEntity<CircuitDTO> postCircuit(@RequestBody CircuitDTO circuitDTO){
        Circuit circuit = CircuitConverter.convertToCircuit(circuitDTO);
        circuitService.save(circuit);

        return new ResponseEntity<>(CircuitConverter.convertToDTO(circuit), HttpStatus.OK);
    }
}
