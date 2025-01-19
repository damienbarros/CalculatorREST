package org.example;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/calculate")
public class CalculatorController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public CalculatorController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/sum")
    public ResponseEntity<BigDecimal> sum(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        kafkaTemplate.send("calculator-topic", "sum," + a + "," + b);
        return ResponseEntity.ok(a.add(b));
    }

    @GetMapping("/subtract")
    public ResponseEntity<BigDecimal> subtract(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        kafkaTemplate.send("calculator-topic", "subtract," + a + "," + b);
        return ResponseEntity.ok(a.subtract(b));
    }

    @GetMapping("/multiply")
    public ResponseEntity<BigDecimal> multiply(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        kafkaTemplate.send("calculator-topic", "multiply," + a + "," + b);
        return ResponseEntity.ok(a.multiply(b));
    }

    @GetMapping("/divide")
    public ResponseEntity<BigDecimal> divide(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        if (b.equals(BigDecimal.ZERO)) {
            return ResponseEntity.badRequest().body(null);
        }
        kafkaTemplate.send("calculator-topic", "divide," + a + "," + b);
        return ResponseEntity.ok(a.divide(b));
    }

}
