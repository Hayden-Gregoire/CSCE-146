/*
Written By Hayden Gregoire
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TaskOrganizer {
    private GenLL<Task>[] organizedTasks;

    // Constructor initializes the array of linked lists for tasks organized by priority
    public TaskOrganizer() {
        organizedTasks = new GenLL[5];
        for (int i = 0; i < organizedTasks.length; i++) {
            organizedTasks[i] = new GenLL<>();
        }
    }

    // Method to add a task based on its action and priority
    public void addTask(String action, int priority) {
        Task newTask = new Task(action, priority);
        
        // Check if the task already exists to avoid duplicates
        if (!isDuplicateTask(newTask, priority)) {
            organizedTasks[priority].add(newTask);
            System.out.println("Task added successfully.");
        } else {
            System.out.println("Duplicate task detected. Task not added.");
        }
    }

    // Helper method to check if a task with the same action and priority already exists
    private boolean isDuplicateTask(Task newTask, int priority) {
        for (Task task : organizedTasks[priority]) {
            if (task.equals(newTask)) {
                return true;
            }
        }
        return false;
    }

    // Method to remove a task based on its action and priority
    public void removeTask(String action, int priority) {
        Task taskToRemove = new Task(action, priority);
        
        // Attempt to remove the task, and inform the user if not found
        if (!organizedTasks[priority].remove(taskToRemove)) {
            System.out.println("Task not found.");
        } else {
            System.out.println("Task removed successfully.");
        }
    }

    // Method to print all tasks in priority order (0 to 4)
    public void printTasks() {
        for (int i = 0; i < organizedTasks.length; i++) {
            System.out.println("Priority " + i + " Tasks:");
            organizedTasks[i].printList();
        }
    }

    // Method to load tasks from a file
    public void loadFromFile(String filename) {
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            // Clear the current list and reload from the file
            for (int i = 0; i < organizedTasks.length; i++) {
                organizedTasks[i] = new GenLL<>();
            }

            // Read each line from the file and add valid tasks
            while (fileScanner.hasNextLine()) {
                String[] taskData = fileScanner.nextLine().split("\t");
                if (taskData.length == 2) {
                    int priority = Integer.parseInt(taskData[0]);
                    String action = taskData[1];
                    // Add the task if the priority is valid
                    if (priority >= 0 && priority <= 4) {
                        addTask(action, priority);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    // Method to save tasks to a file
    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            // Write each task with its priority to the file
            for (int i = 0; i < organizedTasks.length; i++) {
                GenLL<Task> list = organizedTasks[i];
                for (Task task : list) {  // Using the iterator to traverse the list
                    writer.println(i + "\t" + task.getAction());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving to file.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskOrganizer organizer = new TaskOrganizer();

        boolean running = true;
        while (running) {
            System.out.println("\nEnter 1. To Add a Task\nEnter 2. To Remove a Task\nEnter 3. To Print Tasks To Console\nEnter 4. To Read from a Task File\nEnter 5. To Write to a Task File\nEnter 9. To Quit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    System.out.println("Enter the task's priority (0-4):");
                    int priority = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    System.out.println("Enter the task's action:");
                    String action = scanner.nextLine();
                    organizer.addTask(action, priority);
                    break;
                case 2:
                    System.out.println("Enter the task's priority (0-4):");
                    int removePriority = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    System.out.println("Enter the task's action:");
                    String removeAction = scanner.nextLine();
                    organizer.removeTask(removeAction, removePriority);
                    break;
                case 3:
                    organizer.printTasks();
                    break;
                case 4:
                    System.out.println("Enter the file name:");
                    String filename = scanner.nextLine();
                    organizer.loadFromFile(filename);
                    break;
                case 5:
                    System.out.println("Enter the file name to save:");
                    String saveFilename = scanner.nextLine();
                    organizer.saveToFile(saveFilename);
                    break;
                case 9:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}