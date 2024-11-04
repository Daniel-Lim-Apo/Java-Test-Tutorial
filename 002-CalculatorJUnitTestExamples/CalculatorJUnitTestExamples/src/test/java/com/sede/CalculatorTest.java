package com.sede;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;


import java.util.stream.Stream;

import java.time.Duration;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void setup() {
        calculator = new Calculator();
    }

    // 1. Simple addition test
    @Test
    void testAddition() {
        assertEquals(5, calculator.add(2, 3), "2 + 3 should equal 5");
    }

    // 2. Simple subtraction test
    @Test
    void testSubtraction() {
        assertEquals(4, calculator.subtract(7, 3), "7 - 3 should equal 4");
    }

    // 3. Simple multiplication test
    @Test
    void testMultiplication() {
        assertEquals(6, calculator.multiply(2, 3), "2 * 3 should equal 6");
    }

    // 4. Simple division test
    @Test
    void testDivision() {
        assertEquals(2.0, calculator.divide(6, 3), "6 / 3 should equal 2");
    }

    // 5. Division by zero should throw exception
    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(6, 0), "Divide by zero should throw ArithmeticException");
    }

    // 6. Test for an even number
    @Test
    void testIsEven() {
        assertTrue(calculator.isEven(4), "4 should be even");
    }

    // 7. Test for an odd number
    @Test
    void testIsOdd() {
        assertFalse(calculator.isEven(5), "5 should be odd");
    }

    // 8. Multiple assertions
    @Test
    void testMultipleAssertions() {
        assertAll(
                () -> assertEquals(4, calculator.add(2, 2)),
                () -> assertEquals(2, calculator.subtract(4, 2)),
                () -> assertEquals(9, calculator.multiply(3, 3))
        );
    }

    // 9. Testing with null - optional but demonstrates testing null cases
    @Test
    void testNullCheck() {
        Calculator calculator = null;
        assertNull(calculator, "Calculator should be null");
    }

    // 10. Testing not null
    @Test
    void testNotNull() {
        assertNotNull(calculator, "Calculator should not be null");
    }

    // 11. Timeout test - tests if code finishes within time
    @Test
    void testTimeout() {
        assertTimeout(Duration.ofMillis(100), () -> Thread.sleep(50));
    }

    // 12. Testing custom error message
    @Test
    void testCustomErrorMessage() {
        assertEquals(10, calculator.add(5, 5), "Custom error: 5 + 5 should equal 10");
    }

    // 13. Using assertTrue with a lambda expression
    @Test
    void testAssertTrueWithLambda() {
        assertTrue(() -> calculator.add(2, 3) == 5, "2 + 3 should be equal to 5");
    }

    // 14. Testing negative addition
    @Test
    void testNegativeAddition() {
        assertEquals(-5, calculator.add(-2, -3), "-2 + -3 should equal -5");
    }

    // 15. Testing subtraction resulting in negative
    @Test
    void testNegativeResultSubtraction() {
        assertEquals(-1, calculator.subtract(2, 3), "2 - 3 should equal -1");
    }

    // 16. Parameterized test for addition
    @ParameterizedTest
    @CsvSource({ "1, 1, 2", "2, 3, 5", "5, 5, 10" })
    void testParameterizedAddition(int a, int b, int result) {
        assertEquals(result, calculator.add(a, b));
    }

    // 17. Parameterized test for division with doubles
    @ParameterizedTest
    @CsvSource({ "6, 3, 2.0", "10, 2, 5.0", "5, 2, 2.5" })
    void testParameterizedDivision(int a, int b, double result) {
        assertEquals(result, calculator.divide(a, b), 0.001);
    }

    // 18. Testing with streams
    @ParameterizedTest
    @MethodSource("numberProvider")
    void testWithStream(int number) {
        assertTrue(calculator.isEven(number), "Number should be even");
    }

    static Stream<Integer> numberProvider() {
        return Stream.of(2, 4, 6, 8);
    }

    // 19. Exception test with specific message
    @Test
    void testDivisionByZeroWithMessage() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    // 20. Nested test class
    @Nested
    class NestedCalculatorTests {
        @Test
        void testAdditionInNestedClass() {
            assertEquals(3, calculator.add(1, 2), "1 + 2 should equal 3");
        }

        @Test
        void testMultiplicationInNestedClass() {
            assertEquals(12, calculator.multiply(3, 4), "3 * 4 should equal 12");
        }
    }
}