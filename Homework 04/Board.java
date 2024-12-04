/*
 * Written by Hayden Gregoire
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Board {
    private char[][] grid; // 2D grid representing the board
    private int rows, cols; // Dimensions of the board

    // Reads the board layout from a file
    public Board(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        rows = lines.size();
        cols = lines.get(0).length();
        grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            grid[i] = lines.get(i).toCharArray();
        }
    }

    // Displays the current board with the robot's position
    public void display(int robotX, int robotY) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == robotX && j == robotY) {
                    System.out.print("O");  // Represents the robot's position
                } else {
                    System.out.print(grid[i][j]);
                }
            }
            System.out.println();
        }
    }

    // Checks if a given position on the board is an obstacle or out of bounds
    public boolean isObstacle(int x, int y) {
        return x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] == 'X';
    }
}
