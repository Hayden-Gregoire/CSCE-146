/*
 * Written by Hayden Gregoire
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CommandProcessor {
    // Loads commands from file into a queue, ignoring invalid commands
    public Queue<String> loadCommands(String filename) throws IOException {
        Queue<String> commands = new Queue<>();
        List<String> lines = Files.readAllLines(Paths.get(filename));
        for (String line : lines) {
            if (line.equals("Move Up") || line.equals("Move Down") ||
                line.equals("Move Left") || line.equals("Move Right")) {
                commands.enqueue(line);
            }
        }
        return commands;
    }
}
