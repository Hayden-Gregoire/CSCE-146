
/*
* Written By Hayden Gregoire
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class ShowcaseShowdown {

    // Store prizes in arrays
    private static String[] prizeNames;
    private static double[] prizePrices;
    public static final String FILE_NAME = "prizeList.txt";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            loadPrizes(FILE_NAME);  // Load prizes from file
            boolean keepPlaying = true;

            while (keepPlaying) {
                if (prizeNames.length < 5) {
                    System.out.println("Not enough valid prizes to select from.");
                    break;
                }
                playGame(scanner);
                System.out.println("Would you like to quit? Enter \"yes\" to quit.");
                String quitResponse = scanner.nextLine().toLowerCase();
                keepPlaying = !quitResponse.equals("yes");
            }

            System.out.println("Goodbye!");
        } 
    }

    private static void loadPrizes(String filename) {
        int prizeCount = 0;
        Scanner fileScanner = null;

        try {
            // First pass to count the number of valid prizes
            fileScanner = new Scanner(new File(filename));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\t");
                if (parts.length == 2 && isValidDouble(parts[1])) {
                    prizeCount++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } finally {
            if (fileScanner != null) {
                fileScanner.close();
            }
        }

        // Initialize arrays to store prize names and prices
        prizeNames = new String[prizeCount];
        prizePrices = new double[prizeCount];
        int index = 0;

        // Second pass to actually store valid prizes and prices
        try {
            fileScanner = new Scanner(new File(filename));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\t");
                if (parts.length == 2 && isValidDouble(parts[1])) {
                    prizeNames[index] = parts[0];
                    prizePrices[index] = Double.parseDouble(parts[1]);
                    index++;
                }
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            System.out.println("Error while reading the file.");
        } finally {
            if (fileScanner != null) {
                fileScanner.close();
            }
        }
    }

    private static boolean isValidDouble(String s) {
        try {
            Double.valueOf(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void playGame(Scanner scanner) {
        // Randomly select 5 unique prizes
        Random random = new Random();
        int[] selectedIndexes = new int[5];
        boolean unique;

        for (int i = 0; i < 5; i++) {
            do {
                unique = true;
                int randomIndex = random.nextInt(prizeNames.length);
                for (int j = 0; j < i; j++) {
                    if (selectedIndexes[j] == randomIndex) {
                        unique = false;
                        break;
                    }
                }
                if (unique) {
                    selectedIndexes[i] = randomIndex;
                }
            } while (!unique);
        }

        double totalPrice = 0.0;
        System.out.println("Your prizes are:");
        for (int index : selectedIndexes) {
            System.out.println(prizeNames[index]);
            totalPrice += prizePrices[index];
        }

        System.out.println("You must guess the total cost of the prizes without going over and within $1,300 of its actual price.");
        System.out.print("Enter your guess: ");
        double userGuess = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        System.out.println("The actual cost was " + totalPrice);

        if (userGuess >= totalPrice - 1300 && userGuess <= totalPrice) {
            System.out.println("You win!!!");
        } else if (userGuess > totalPrice) {
            System.out.println("Your guess was over. You lose.");
        } else {
            System.out.println("Your guess was close, but not close enough. You lose.");
        }
    }
}