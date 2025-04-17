package wolf;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Basic Calculator!");
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            if (choice == 5) {
                System.out.println("Exiting calculator...");
                break;
            }
            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please try again.");
                pause();
                continue;
            }

            // Get two numbers
            double[] numbers = getNumbers();
            double num1 = numbers[0];
            double num2 = numbers[1];

            // Perform the operation
            double result = performOperation(choice, num1, num2);
            if (!Double.isNaN(result)) {
                System.out.printf("Result: %.2f %s %.2f = %.2f%n", 
                    num1, getOperator(choice), num2, result);
            }
            pause();
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\nCalculator Menu:");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Invalid input
        }
    }

    private static double[] getNumbers() {
        double num1 = 0, num2 = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter first number: ");
                num1 = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter second number: ");
                num2 = Double.parseDouble(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter valid numbers.");
            }
        }
        return new double[]{num1, num2};
    }

    private static double performOperation(int choice, double num1, double num2) {
        switch (choice) {
            case 1: return num1 + num2;
            case 2: return num1 - num2;
            case 3: return num1 * num2;
            case 4:
                if (num2 == 0) {
                    System.out.println("Error: Division by zero is not allowed.");
                    return Double.NaN;
                }
                return num1 / num2;
            default: return Double.NaN;
        }
    }

    private static String getOperator(int choice) {
        switch (choice) {
            case 1: return "+";
            case 2: return "-";
            case 3: return "*";
            case 4: return "/";
            default: return "";
        }
    }

    private static void pause() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
}