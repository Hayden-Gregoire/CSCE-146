/*
 * Written by Hayden Gregiure
 */
import java.io.*;
import java.util.*;

public class VideoGameDatabase {

    private static LinkedList<VideoGame> gameList = new LinkedList<>();
    private static LinkedList<VideoGame> searchResults = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Video Game Database!");

        while (running) {
            System.out.println("\nEnter 1 to load the video game database");
            System.out.println("Enter 2 to search the database");
            System.out.println("Enter 3 to print current results to the console");
            System.out.println("Enter 4 to print current results to file");
            System.out.println("Enter 0 to quit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter the file name:");
                    String fileName = scanner.nextLine();
                    loadGameDatabase(fileName);
                    break;
                case 2:
                    searchGames(scanner);
                    break;
                case 3:
                    printResultsToConsole();
                    break;
                case 4:
                    printResultsToFile(scanner);
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        scanner.close();
    }

    private static void loadGameDatabase(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            gameList.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 2) {
                    gameList.add(new VideoGame(parts[0], parts[1]));
                }
            }
            System.out.println("Video game database loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading the file: " + e.getMessage());
        }
    }

    private static void searchGames(Scanner scanner) {
        System.out.println("Enter the name of the game or '*' for all:");
        String title = scanner.nextLine();
        System.out.println("Enter the name of the console or '*' for all:");
        String console = scanner.nextLine();
        searchResults = gameList.search(title, console);

        if (searchResults.isEmpty()) {
            System.out.println("No games found.");
        } else {
            System.out.println("Search results:");
            searchResults.printToConsole();
        }
    }

    private static void printResultsToConsole() {
        if (searchResults.isEmpty()) {
            System.out.println("No search results to display.");
        } else {
            searchResults.printToConsole();
        }
    }

    private static void printResultsToFile(Scanner scanner) {
        if (searchResults.isEmpty()) {
            System.out.println("No search results to print.");
            return;
        }

        System.out.println("Enter the file name:");
        String fileName = scanner.nextLine();
        System.out.println("Would you like to append? (true/false)");
        boolean append = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline

        try {
            // Attempt to write the search results to the specified file
            searchResults.printToFile(fileName, append);
        } catch (IOException e) {
            System.out.println("Error printing results to file: " + e.getMessage());
        }
    }
}