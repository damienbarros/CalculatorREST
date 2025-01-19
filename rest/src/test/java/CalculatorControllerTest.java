import org.example.CalculatorController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class CalculatorControllerTest {

    private KafkaTemplate<String, String> kafkaTemplate;
    private CalculatorController calculatorController;

    @BeforeEach
    void setUp() {
        kafkaTemplate = Mockito.mock(KafkaTemplate.class);
        calculatorController = new CalculatorController(kafkaTemplate);
    }


    @Test
    void testSum() {
        BigDecimal a = new BigDecimal("5");
        BigDecimal b = new BigDecimal("10");

        ResponseEntity<BigDecimal> response = calculatorController.sum(a, b);

        assertEquals(new BigDecimal("15"), response.getBody());
        verify(kafkaTemplate).send("calculator-topic", "sum,5,10");
    }

    @Test
    void testSubtract() {
        BigDecimal a = new BigDecimal("20");
        BigDecimal b = new BigDecimal("8");

        ResponseEntity<BigDecimal> response = calculatorController.subtract(a, b);

        assertEquals(new BigDecimal("12"), response.getBody());
        verify(kafkaTemplate).send("calculator-topic", "subtract,20,8");
    }

    @Test
    void testMultiply() {
        BigDecimal a = new BigDecimal("4");
        BigDecimal b = new BigDecimal("7");

        ResponseEntity<BigDecimal> response = calculatorController.multiply(a, b);

        assertEquals(new BigDecimal("28"), response.getBody());
        verify(kafkaTemplate).send("calculator-topic", "multiply,4,7");
    }

    @Test
    void testDivide() {
        BigDecimal a = new BigDecimal("40");
        BigDecimal b = new BigDecimal("8");

        ResponseEntity<BigDecimal> response = calculatorController.divide(a, b);

        assertEquals(new BigDecimal("5"), response.getBody());
        verify(kafkaTemplate).send("calculator-topic", "divide,40,8");
    }

    @Test
    void testDivideByZero() {
        BigDecimal a = new BigDecimal("40");
        BigDecimal b = BigDecimal.ZERO;

        ResponseEntity<BigDecimal> response = calculatorController.divide(a, b);

        assertEquals(null, response.getBody());
    }

}
