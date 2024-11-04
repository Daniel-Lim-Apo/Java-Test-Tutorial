package com.sede;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Calculator!");
        System.out.println("Please select an operation:");
        System.out.println("1. Add");
        System.out.println("2. Subtract");
        System.out.println("3. Multiply");
        System.out.println("4. Divide");
        System.out.println("5. Check if a number is even");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1: // Addition
                System.out.println("Enter two numbers to add:");
                int addNum1 = scanner.nextInt();
                int addNum2 = scanner.nextInt();
                int addResult = calculator.add(addNum1, addNum2);
                System.out.println("Result: " + addNum1 + " + " + addNum2 + " = " + addResult);
                break;

            case 2: // Subtraction
                System.out.println("Enter two numbers to subtract:");
                int subNum1 = scanner.nextInt();
                int subNum2 = scanner.nextInt();
                int subResult = calculator.subtract(subNum1, subNum2);
                System.out.println("Result: " + subNum1 + " - " + subNum2 + " = " + subResult);
                break;

            case 3: // Multiplication
                System.out.println("Enter two numbers to multiply:");
                int mulNum1 = scanner.nextInt();
                int mulNum2 = scanner.nextInt();
                int mulResult = calculator.multiply(mulNum1, mulNum2);
                System.out.println("Result: " + mulNum1 + " * " + mulNum2 + " = " + mulResult);
                break;

            case 4: // Division
                System.out.println("Enter two numbers to divide:");
                int divNum1 = scanner.nextInt();
                int divNum2 = scanner.nextInt();
                try {
                    double divResult = calculator.divide(divNum1, divNum2);
                    System.out.println("Result: " + divNum1 + " / " + divNum2 + " = " + divResult);
                } catch (ArithmeticException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case 5: // Check even or odd
                System.out.println("Enter a number to check if it is even:");
                int num = scanner.nextInt();
                boolean isEven = calculator.isEven(num);
                System.out.println("Result: " + num + " is " + (isEven ? "even" : "odd"));
                break;

            default:
                System.out.println("Invalid choice. Please restart and choose a valid operation.");
                break;
        }

        scanner.close();
    }
}
