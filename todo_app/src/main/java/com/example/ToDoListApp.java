package com.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a task in the to-do list with a title, description, and completion status.
 */
class Task {
    private String title;
    private String description;
    private boolean isCompleted;

    /**
     * Constructor to initialize a task with a title and description.
     * The isCompleted field is initialized to false by default.
     *
     * @param title       The title of the task.
     * @param description The description of the task.
     */
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Gets the title of the task.
     *
     * @return The title of the task.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is completed.
     *
     * @return True if the task is completed, false otherwise.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param completed True to mark the task as completed, false otherwise.
     */
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    /**
     * Updates the description of the task.
     *
     * @param description The new description of the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string containing the task's title, description, and completion status.
     */
    @Override
    public String toString() {
        return "Title: " + title + ", Description: " + description + ", Completed: " + isCompleted;
    }
}

/**
 * Main application class for managing a to-do list.
 */
public class ToDoListApp {

    /**
     * ANSI escape codes for colors.
     */
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";

    /**
     * A static list to store all tasks created by the user.
     */
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * A static scanner instance to handle user input throughout the application.
     */
    private static Scanner scanner = new Scanner(System.in);

    /**
     * The main method that starts the to-do list application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        displayHeader();
        while (true) {
            clearTerminal();
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Get rid of the newline character

            switch (choice) {
                case 1:
                    clearTerminal();
                    addTask();
                    break;
                case 2:
                    clearTerminal();
                    viewTasks();
                    break;
                case 3:
                    clearTerminal();
                    updateTask();
                    break;
                case 4:
                    clearTerminal();
                    deleteTask();
                    break;
                case 5:
                    clearTerminal();
                    System.out.println(GREEN + "Exiting... Goodbye!" + RESET);
                    return;
                default:
                    clearTerminal();
                    System.out.println(RED + "Invalid choice. Try again." + RESET);
                    pause();
                    break;
            }
        }
    }

    /**
     * Displays a colorful header with a wolf-style ASCII art decoration.
     */
    private static void displayHeader() {
        System.out.println(CYAN + "========================================");
        System.out.println("              TO-DO LIST APP");
        System.out.println("========================================");
        System.out.println("               /\\     /\\");
        System.out.println("              {  `---'  }");
        System.out.println("              {  O   O  }");
        System.out.println("~~ WOLF ~~     ~~\\  ^  /~~");
        System.out.println("                 |||||");
        System.out.println("                 |||||");
        System.out.println("========================================" + RESET);
        pause(); // Pause to allow the user to view the header
    }

    /**
     * Clears the terminal screen by executing the appropriate command based on the operating system.
     * If the command fails, it prints multiple newlines as a fallback to simulate clearing the screen.
     */
    private static void clearTerminal() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.print("\n".repeat(50));
        }
    }

    /**
     * Pauses the application and waits for the user to press Enter before continuing.
     */
    private static void pause() {
        System.out.println(YELLOW + "Press Enter to continue..." + RESET);
        scanner.nextLine();
    }

    /**
     * Displays the main menu of the to-do list application.
     */
    private static void displayMenu() {
        System.out.println(BLUE + "\nTo-Do List Menu:");
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Update Task");
        System.out.println("4. Delete Task");
        System.out.println("5. Exit" + RESET);
        System.out.print(CYAN + "Enter choice: " + RESET);
    }

    /**
     * Adds a new task to the to-do list.
     */
    private static void addTask() {
        System.out.print(CYAN + "Enter task title: " + RESET);
        String title = scanner.nextLine();
        System.out.print(CYAN + "Enter task description: " + RESET);
        String description = scanner.nextLine();
        tasks.add(new Task(title, description));
        System.out.println(GREEN + "Task added successfully!" + RESET);
        pause();
    }

    /**
     * Displays all tasks in the to-do list.
     * If no tasks are available, it notifies the user.
     */
    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println(RED + "No tasks available." + RESET);
            pause();
            return;
        }
        System.out.println(YELLOW + "Your Tasks:" + RESET);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(CYAN + (i + 1) + ". " + tasks.get(i) + RESET);
        }
        pause();
    }

    /**
     * Updates an existing task in the to-do list.
     * Allows the user to modify the description and mark the task as completed.
     */
    private static void updateTask() {
        viewTasks();
        if (tasks.isEmpty()) return;
        System.out.print(CYAN + "Enter task number to update: " + RESET);
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (index >= 0 && index < tasks.size()) {
            System.out.print(CYAN + "New description (leave blank to keep current): " + RESET);
            String description = scanner.nextLine();

            if (!description.isEmpty()) {
                tasks.get(index).setDescription(description);
            }
            System.out.print(CYAN + "Mark as completed? (y/n): " + RESET);
            String choice = scanner.nextLine();
            tasks.get(index).setCompleted(choice.equalsIgnoreCase("y"));
            System.out.println(GREEN + "Task updated!" + RESET);
            pause();
        } else {
            System.out.println(RED + "Invalid task number." + RESET);
            pause();
        }
    }

    /**
     * Deletes a task from the to-do list based on the user's input.
     */
    private static void deleteTask() {
        viewTasks();
        if (tasks.isEmpty()) return;
        System.out.print(CYAN + "Enter task number to delete: " + RESET);
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println(GREEN + "Task deleted!" + RESET);
            pause();
        } else {
            System.out.println(RED + "Invalid task number." + RESET);
            pause();
        }
    }
}