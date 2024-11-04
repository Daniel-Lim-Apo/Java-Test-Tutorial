package com.sede;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Use coverage
// With Intellij
// > More Run/Debug > Run 'GraderTest with coverage'
// Then, after that see the colors in the grader class view to see green for what is covered by tests.

class GraderTest {
@Test
    void fiftyEightShouldReturnF(){
    var grader = new Grader();
    assertEquals('F', grader.getLetterGrade(58));
}

    @Test
    void eightyEightShouldReturnB(){
        var grader = new Grader();
        assertEquals('B', grader.getLetterGrade(88));
    }

    @Test
    void negativeTwoShouldReturnIllegalArgumentException(){
        var grader = new Grader();
        assertThrows(
            IllegalArgumentException.class,
                () -> {
                grader.getLetterGrade(-2);
                }
        );
    }
}