import org.example.CalculatorService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService();
    }

    //sum test
    @Test
    void testSum() {
        String message = "sum,5,10";
        ConsumerRecord<String, String> record = new ConsumerRecord<>("calculator-topic", 0, 0L, null, message);

        calculatorService.processCalc(record);

    }

    //subtract test
    @Test
    void testSubtract() {
        String message = "subtract,20,8";
        ConsumerRecord<String, String> record = new ConsumerRecord<>("calculator-topic", 0, 0L, null, message);

        calculatorService.processCalc(record);

    }

    //multiply test
    @Test
    void testMultiply() {
        String message = "multiply,4,7";
        ConsumerRecord<String, String> record = new ConsumerRecord<>("calculator-topic", 0, 0L, null, message);

        calculatorService.processCalc(record);

    }

    //divide test
    @Test
    void testDivide() {
        String message = "divide,40,8";
        ConsumerRecord<String, String> record = new ConsumerRecord<>("calculator-topic", 0, 0L, null, message);

        calculatorService.processCalc(record);

    }


}
