import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        File input = new File("2021/Day 3/input");
        System.out.println(input.getAbsolutePath());

        List<Map<Character, Integer>> values = new ArrayList<>();

        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                char[] inputLine = scanner.nextLine().toCharArray();

                if (values.size() == 0)
                    for (int i = 0; i < inputLine.length; i++) {
                        values.add(new HashMap<>());
                    }

                for (int i = 0; i < values.size(); i++) {
                    if (inputLine[i] != '0' && inputLine[i] != '1')
                        continue;

                    Map<Character, Integer> currentBitStates = values.get(i);

                    Integer currentCharCount = currentBitStates.get(inputLine[i]);
                    currentBitStates.put(
                            inputLine[i],
                            currentCharCount != null ? currentCharCount + 1 : 1
                    );
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(values.toString());

        StringBuilder mostSignificantBits = new StringBuilder();
        StringBuilder leastSignificantBits = new StringBuilder();
        for (Map<Character, Integer> map : values) {
            Integer totalTrue = map.get('1');
            Integer totalFalse = map.get('0');

            if (totalTrue == null)
                totalTrue = 0;

            if (totalFalse == null)
                totalFalse = 0;

            if (totalTrue > totalFalse) {
                mostSignificantBits.append('1');
                leastSignificantBits.append('0');
            } else {
                mostSignificantBits.append('0');
                leastSignificantBits.append('1');
            }
        }

        long gammaRate = Long.parseLong(mostSignificantBits.toString(), 2);
        long epsilonRate = Long.parseLong(leastSignificantBits.toString(), 2);

        System.out.printf("MostSignificant: %s (%d)\nLeastSignificant: %s (%d)\n",
                mostSignificantBits, gammaRate,
                leastSignificantBits, epsilonRate
        );
        System.out.printf("powerConsumptoin = %d\n", gammaRate * epsilonRate);

        // Part two
        // Most common
//        List<String> common
//        for (Map<Character, Integer> map : values) {
//
//             Most common
//
//        }



    }
}
