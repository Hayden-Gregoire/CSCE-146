/*
 * Written by Hayden Gregoire
 */

public class Sheep implements Comparable<Sheep> {
    String name;
    int shearingTime;
    int arrivalTime;

    public Sheep(String name, int shearingTime, int arrivalTime) {
        this.name = name;
        this.shearingTime = shearingTime;
        this.arrivalTime = arrivalTime;
    }

    // Sorting by shearing time, then by name
    @Override
    public int compareTo(Sheep other) {
        if (this.shearingTime != other.shearingTime)
            return Integer.compare(this.shearingTime, other.shearingTime);
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Shear Time: " + shearingTime + ", Arrival Time: " + arrivalTime;
    }
}