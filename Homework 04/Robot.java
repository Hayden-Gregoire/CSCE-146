/*
 * Written by Hayden Gregoire
 */

public class Robot {
    private int x, y; // Robot's position (initially top-left corner)

    public Robot() {
        this.x = 0;
        this.y = 0;
    }

    // Attempts to move the robot based on the given command, returns false if it crashes
    public boolean move(String command, Board board) {
        int newX = x, newY = y;

        switch (command) {
            case "Move Up":    newX--; break;
            case "Move Down":  newX++; break;
            case "Move Left":  newY--; break;
            case "Move Right": newY++; break;
            default: return true; // Ignores invalid commands
        }

        // Check for collision with obstacle or boundary
        if (board.isObstacle(newX, newY)) {
            System.out.println("CRASH!");
            return false;
        }

        // Update position if no collision occurs
        x = newX;
        y = newY;
        return true;
    }

    public int getX() { return x; } // Returns the robot's current x-coordinate
    public int getY() { return y; } // Returns the robot's current y-coordinate
}
