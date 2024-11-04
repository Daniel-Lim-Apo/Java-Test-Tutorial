# Java-Test-Tutorial
Tests in Java Tutorial for beginners - How to create tests in Java.

This totorial covers unit testing in Java. It goes over essential topics, including what unit testing is, why it's important, setting up JUnit, and writing and running various kinds of unit tests.

By following this guide, you should have a solid foundation for writing and running unit tests in Java using JUnit and Mockito. Happy testing!

---

# Java Unit Testing - A Detailed Tutorial

## Table of Contents
1. [Introduction to Unit Testing](#introduction-to-unit-testing)
2. [Why Unit Testing is Important](#why-unit-testing-is-important)
3. [JUnit Setup](#junit-setup)
4. [Writing Your First Unit Test](#writing-your-first-unit-test)
5. [Annotations in JUnit](#annotations-in-junit)
6. [Common Assertions](#common-assertions)
7. [Testing Exceptions](#testing-exceptions)
8. [Parameterized Tests](#parameterized-tests)
9. [Mocking with Mockito](#mocking-with-mockito)
10. [Best Practices](#best-practices)

---

## Introduction to Unit Testing

Unit testing is a software testing technique where individual components of the application are tested in isolation. Each "unit" — usually a method or function — is tested independently to ensure it behaves as expected. Unit tests are written by developers to verify that specific parts of the codebase work as intended.

---

## Why Unit Testing is Important

Unit testing has many benefits:
- **Improves Code Quality**: By catching issues early in development, code quality is maintained.
- **Facilitates Refactoring**: With good test coverage, refactoring code is safer, as the tests will highlight any unintended side effects.
- **Documentation**: Tests can serve as documentation, showing how each method or class should behave.
- **Reduces Bugs in Production**: Bugs caught early can prevent major production issues.

---

## JUnit Setup

JUnit is the most popular framework for unit testing in Java. We’ll be using JUnit 5, which offers several improvements over previous versions.

### Step 1: Adding JUnit Dependency
To add JUnit to your project, add the following dependency to your `pom.xml` if you're using Maven:

```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.7.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

Or, if you're using Gradle:

```groovy
testImplementation 'org.junit.jupiter:junit-jupiter:5.7.0'
```

### Step 2: Verify the Setup
After adding the dependencies, verify that JUnit is correctly installed by running a simple test (explained in the next section).

---

## Writing Your First Unit Test

Let's start with a simple example of a class and write a unit test for it.

### Example: Basic Calculator Class

```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }
}
```

### Writing a Test for `Calculator`

Create a test class in the `src/test/java` directory:

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void testAddition() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void testSubtraction() {
        Calculator calculator = new Calculator();
        assertEquals(2, calculator.subtract(5, 3));
    }
}
```

Here:
- `@Test` denotes a test method.
- `assertEquals` is used to check if the result is as expected.

---

## Annotations in JUnit

JUnit provides various annotations to control the test flow.

- **@Test**: Marks a method as a test method.
- **@BeforeEach**: Code that runs before each test. Useful for setting up test data.
- **@AfterEach**: Code that runs after each test.
- **@BeforeAll**: Code that runs once before any test method in the class.
- **@AfterAll**: Code that runs once after all tests.

### Example:

```java
import org.junit.jupiter.api.*;

public class LifecycleTest {

    @BeforeAll
    static void setupAll() {
        System.out.println("Setup for all tests.");
    }

    @BeforeEach
    void setup() {
        System.out.println("Setup for individual test.");
    }

    @Test
    void testOne() {
        System.out.println("Running Test One");
    }

    @Test
    void testTwo() {
        System.out.println("Running Test Two");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Teardown after individual test.");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Teardown after all tests.");
    }
}
```

---

## Common Assertions

JUnit provides various assertions to test conditions. Here are some commonly used ones:

- **assertEquals(expected, actual)**: Checks if two values are equal.
- **assertNotEquals(expected, actual)**: Checks if two values are not equal.
- **assertTrue(condition)**: Checks if a condition is true.
- **assertFalse(condition)**: Checks if a condition is false.
- **assertNull(object)**: Checks if an object is null.
- **assertNotNull(object)**: Checks if an object is not null.
- **assertArrayEquals(expectedArray, actualArray)**: Checks if two arrays are equal.

### Example:

```java
import static org.junit.jupiter.api.Assertions.*;

@Test
void testAssertions() {
    assertEquals(5, 5);
    assertTrue("Hello".startsWith("H"));
    assertNotNull("Hello");
}
```

---

## Testing Exceptions

JUnit provides a way to check if code throws the expected exception.

### Example

```java
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ExceptionTest {

    @Test
    void testException() {
        assertThrows(ArithmeticException.class, () -> {
            int result = 10 / 0;
        });
    }
}
```

---

## Parameterized Tests

JUnit 5 allows parameterized tests, where a test is run multiple times with different inputs.

### Example

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ParameterizedTestExample {

    @ParameterizedTest
    @ValueSource(strings = {"racecar", "radar", "level"})
    void testPalindrome(String word) {
        assertEquals(word, new StringBuilder(word).reverse().toString());
    }
}
```

This test will run three times, once for each word provided in the `ValueSource`.

---

## Mocking with Mockito

Mockito is a popular framework used for creating mock objects in unit tests. It is especially useful for testing code that depends on external systems or services.

### Example: Mocking a Service

```java
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ServiceTest {

    @Mock
    Database database;

    @InjectMocks
    UserService userService;

    @Test
    void testAddUser() {
        MockitoAnnotations.openMocks(this);
        when(database.save(any(User.class))).thenReturn(true);

        User user = new User("John");
        boolean result = userService.addUser(user);

        assertTrue(result);
        verify(database).save(user);
    }
}
```

### Explanation:
- `@Mock` creates a mock instance of `Database`.
- `@InjectMocks` injects the mock into `UserService`.
- `when` and `thenReturn` specify mock behavior.

---

## Best Practices

1. **Write Tests Early**: Write unit tests as you develop features.
2. **Test One Thing Per Test**: Each test should focus on one functionality.
3. **Use Meaningful Names**: Test method names should describe the scenario being tested.
4. **Mock External Dependencies**: Avoid testing external services directly.
5. **Make Tests Repeatable**: Tests should produce the same result every time.
6. **Clean Up After Tests**: Use `@AfterEach` and `@AfterAll` to clean up resources.

---

