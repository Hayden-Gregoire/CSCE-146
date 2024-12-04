/*
 * Written by Hayden Gregoire
 */

import java.io.IOException;
import java.util.Scanner;

public class RobotSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Robot Simulator");
        
        while (true) {
            try {
                // Prompt user for board file and load the board
                System.out.print("Enter file for the Board: ");
                String boardFile = scanner.nextLine();
                Board board = new Board(boardFile);

                // Prompt user for command file and load commands
                System.out.print("Enter file for the Robot Commands: ");
                String commandFile = scanner.nextLine();
                CommandProcessor processor = new CommandProcessor();
                Queue<String> commands = processor.loadCommands(commandFile);

                // Initialize the robot and start the command simulation
                Robot robot = new Robot();
                int commandNum = 0;
                boolean crashed = false;

                System.out.println("\nSimulation begin");

                // Process each command until the robot crashes or commands run out
                while (!commands.isEmpty() && !crashed) {
                    String command = commands.dequeue();
                    System.out.println("\nCommand " + commandNum++);
                    crashed = !robot.move(command, board); // Move robot and check for crashes
                    board.display(robot.getX(), robot.getY()); // Display board after each move
                }

                System.out.println("\nSimulation end");
                
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage()); // Handle file reading errors
            }

            // Prompt to quit or restart
            System.out.print("Quit? Enter 'true' to quit or hit enter to run another simulation: ");
            if (scanner.nextLine().equalsIgnoreCase("true")) break;
        }
        scanner.close();
    }
}