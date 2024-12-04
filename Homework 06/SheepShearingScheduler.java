/*
 * Written by Hayden Gregoire
 */

import java.util.*;
import java.io.*;

public class SheepShearingScheduler {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter the file name: ");
            String filename = input.nextLine();

            try {
                File file = new File(filename);
                Scanner fileReader = new Scanner(file);
                ArrayList<Sheep> allSheep = new ArrayList<>();

                while (fileReader.hasNextLine()) {
                    String line = fileReader.nextLine().trim();  // Trim to remove leading/trailing spaces
                    if (line.isEmpty()) continue;  // Skip empty lines

                    String[] data = line.split("\\t");
                    if (data.length != 3) {  // Check if the line has exactly 3 fields
                        System.out.println("Skipping malformed line: " + line);
                        continue;
                    }

                    try {
                        String name = data[0];
                        int shearingTime = Integer.parseInt(data[1]);
                        int arrivalTime = Integer.parseInt(data[2]);
                        allSheep.add(new Sheep(name, shearingTime, arrivalTime));
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping line with invalid number format: " + line);
                    }
                }
                fileReader.close();

                simulateShearing(allSheep);

            } catch (FileNotFoundException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
    }

    public static void simulateShearing(ArrayList<Sheep> sheepList) {
        sheepList.sort(Comparator.comparingInt(s -> s.arrivalTime));  // Sort by arrival time
        MinHeap<Sheep> waitHeap = new MinHeap<>();
        int currentTime = 0;

        for (Sheep sheep : sheepList) {
            // Process waiting sheep
            while (!waitHeap.isEmpty() && currentTime < sheep.arrivalTime) {
                Sheep next = waitHeap.remove();
                System.out.println("Shearing " + next + " at time " + currentTime);
                currentTime += next.shearingTime;
            }

            // Add the current sheep to the heap
            waitHeap.add(sheep);
            currentTime = Math.max(currentTime, sheep.arrivalTime);
        }

        // Process any remaining sheep
        while (!waitHeap.isEmpty()) {
            Sheep next = waitHeap.remove();
            System.out.println("Shearing " + next + " at time " + currentTime);
            currentTime += next.shearingTime;
        }
    }
}