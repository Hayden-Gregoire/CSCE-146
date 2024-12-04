/*
 * Written by Hayden Gregoire
 */
import javax.swing.*;
import java.awt.*;

public class SierpinskiTriangle extends JPanel {

    // Define the recursion limit for stopping (4 pixels in side length)
    private static final int PIXEL_LIMIT = 4;

    // Constructor for setting up the panel
    public SierpinskiTriangle() {
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.WHITE);
    }

    // Recursive method to draw the Sierpinski triangle
    private void drawSierpinski(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3) {
        // Calculate the side length
        int sideLength = Math.abs(x2 - x1);
        
        // Base case: if the side length is less than or equal to pixel limit, stop
        if (sideLength <= PIXEL_LIMIT) {
            return;
        }

        // Draw filled triangle with initial points
        int[] xPoints = { x1, x2, x3 };
        int[] yPoints = { y1, y2, y3 };
        g.setColor(Color.BLACK);
        g.fillPolygon(xPoints, yPoints, 3);

        // Calculate midpoints of each side of the triangle to draw the inner inverted triangle
        int midX1 = (x1 + x2) / 2;
        int midY1 = (y1 + y2) / 2;
        int midX2 = (x2 + x3) / 2;
        int midY2 = (y2 + y3) / 2;
        int midX3 = (x3 + x1) / 2;
        int midY3 = (y3 + y1) / 2;

        // Draw the inner inverted triangle to create the Sierpinski pattern
        int[] innerXPoints = { midX1, midX2, midX3 };
        int[] innerYPoints = { midY1, midY2, midY3 };
        g.setColor(Color.WHITE); // Inner triangle is white to match the sample output
        g.fillPolygon(innerXPoints, innerYPoints, 3);

        // Recursively draw three surrounding triangles
        drawSierpinski(g, x1, y1, midX1, midY1, midX3, midY3); // Left triangle
        drawSierpinski(g, midX1, midY1, x2, y2, midX2, midY2); // Right triangle
        drawSierpinski(g, midX3, midY3, midX2, midY2, x3, y3); // Bottom triangle
    }

    // Overridden paintComponent method to start drawing
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set initial triangle vertices
        int x1 = getWidth() / 2;
        int y1 = 20;
        int x2 = 20;
        int y2 = getHeight() - 20;
        int x3 = getWidth() - 20;
        int y3 = getHeight() - 20;

        // Initial call to the recursive method
        drawSierpinski(g, x1, y1, x2, y2, x3, y3);
    }

    // Main method to set up the JFrame and panel
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sierpinski Triangle Fractal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SierpinskiTriangle());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}