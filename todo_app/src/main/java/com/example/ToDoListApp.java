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
        System.out.println("Welcome to the To-Do List App!");
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
                    System.out.println("Exiting...");
                    return;
                default:
                    clearTerminal();
                    System.out.println("Invalid choice. Try again.");
                    pause();
                    break;
            }
        }
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
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    /**
     * Displays the main menu of the to-do list application.
     */
    private static void displayMenu() {
        System.out.println("\nTo-Do List Menu:");
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Update Task");
        System.out.println("4. Delete Task");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    /**
     * Adds a new task to the to-do list.
     */
    private static void addTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        tasks.add(new Task(title, description));
        System.out.println("Task added successfully!");
        pause();
    }

    /**
     * Displays all tasks in the to-do list.
     * If no tasks are available, it notifies the user.
     */
    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            pause();
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
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
        System.out.print("Enter task number to update: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (index >= 0 && index < tasks.size()) {
            System.out.print("New description (leave blank to keep current): ");
            String description = scanner.nextLine();

            if (!description.isEmpty()) {
                tasks.get(index).setDescription(description);
            }
            System.out.print("Mark as completed? (y/n): ");
            String choice = scanner.nextLine();
            tasks.get(index).setCompleted(choice.equalsIgnoreCase("y"));
            System.out.println("Task updated!");
            pause();
        } else {
            System.out.println("Invalid task number.");
            pause();
        }
    }

    /**
     * Deletes a task from the to-do list based on the user's input.
     */
    private static void deleteTask() {
        viewTasks();
        if (tasks.isEmpty()) return;
        System.out.print("Enter task number to delete: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("Task deleted!");
            pause();
        } else {
            System.out.println("Invalid task number.");
            pause();
        }
    }
}