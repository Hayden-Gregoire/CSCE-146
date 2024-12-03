/*
 * Written by Hayden Gregiure
 */
public class VideoGame {
    String title;
    String console;

    public VideoGame(String title, String console) {
        this.title = title;
        this.console = console;
    }

    @Override
    public String toString() {
        return title + " - " + console;
    }
}