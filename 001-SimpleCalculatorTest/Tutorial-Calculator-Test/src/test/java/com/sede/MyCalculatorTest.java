package com.sede;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyCalculatorTest {

    @Test
    void twoPlusTwoShouldEqualFour(){
        var myCalculator = new MyCalculator();
        assertEquals(4, myCalculator.add(2,2));
    }

    @Test
    void fourPlusSixShouldEqualTen(){
        var myCalculator = new MyCalculator();
        assertEquals(10, myCalculator.add(4,6));
    }
}