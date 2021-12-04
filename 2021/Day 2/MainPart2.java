import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainPart2 {
    public static void main(String[] args) {
        int horizontalPos = 0;
        int depth = 0;
        File input = new File("2021/Day 2/input");
        System.out.println(input.getAbsolutePath());

        if (!input.exists()) {
            System.err.println("Can't find input file");
        }

        try (Scanner scanner = new Scanner(input)) {
            while(scanner.hasNextLine()) {
                String[] fullCommand = scanner.nextLine().split(" ");
                String command = fullCommand[0];
                int argument = Integer.parseInt(fullCommand[1].strip());

                System.out.printf("Found '%s' with %d", command, argument);

                switch (command) {
                    case "forward" -> {
                        horizontalPos += argument;
                        System.out.println("Forward");
                    }
                    case "down" -> {
                        depth += argument;
                        System.out.println("Forward");
                    }
                    case "up" -> {
                        depth -= argument;
                        System.out.println("Forward");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.printf("%d * %d = %d", horizontalPos, depth, horizontalPos * depth);

    }
}
