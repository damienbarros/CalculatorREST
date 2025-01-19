package org.example;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public class CalculatorService {

    @KafkaListener(topics = "calculator-topic", groupId = "calculator-group")
    public void processCalc(ConsumerRecord<String, String> record) {
        String message = record.value();
        String[] parts = message.split(",");
        String operation = parts[0];
        BigDecimal a = new BigDecimal(parts[1]);
        BigDecimal b = new BigDecimal(parts[2]);

        try {
            BigDecimal result = performOperation(operation, a, b);
            System.out.printf("Operation: %s, A: %s, B: %s, Result: %s%n", operation, a, b, result);
        } catch (Exception e) {
            System.err.printf("Error processing operation: %s, Message: %s%n", operation, e.getMessage());
        }
    }

    private BigDecimal performOperation(String operation, BigDecimal a, BigDecimal b) {
        return switch (operation) {
            case "sum" -> a.add(b);
            case "subtract" -> a.subtract(b);
            case "multiply" -> a.multiply(b);
            case "divide" -> {
                if (b.equals(BigDecimal.ZERO)) {
                    throw new ArithmeticException("Division by zero");
                }
                yield a.divide(b, BigDecimal.ROUND_HALF_UP);
            }
            default -> throw new IllegalArgumentException("Invalid operation: " + operation);
        };
    }

}
