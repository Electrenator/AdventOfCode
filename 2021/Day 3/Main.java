import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        File input = new File("2021/Day 3/input");
        System.out.println(input.getAbsolutePath());

        List<char[]> rawInputLines = new ArrayList<>();
        List<Map<Character, Integer>> charAccordanceValues = new ArrayList<>();

        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                char[] inputLine = scanner.nextLine().toCharArray();
                rawInputLines.add(inputLine);

                if (charAccordanceValues.size() == 0)
                    for (int i = 0; i < inputLine.length; i++) {
                        charAccordanceValues.add(new HashMap<>());
                    }

                for (int i = 0; i < charAccordanceValues.size(); i++) {
                    if (inputLine[i] != '0' && inputLine[i] != '1')
                        continue;

                    Map<Character, Integer> currentBitStates = charAccordanceValues.get(i);

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
        System.out.println(charAccordanceValues.toString());

        StringBuilder mostSignificantBits = new StringBuilder();
        StringBuilder leastSignificantBits = new StringBuilder();
        for (Map<Character, Integer> map : charAccordanceValues) {
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
        List<char[]> mostCommonBitList = new ArrayList<>(rawInputLines);
        List<char[]> mostCommonBitListFiltered = new ArrayList<>(rawInputLines);


        for (int charIndex = 0; charIndex < mostCommonBitList.get(0).length; charIndex++) {
            int totalTrue = 0;
            int totalFalse = 0;

            for (char[] bitInput : mostCommonBitList){
                if (bitInput[charIndex] == '1') {
                    totalTrue++;
                }else{
                    totalFalse++;
                }
            }

            char mostCommonBit = totalTrue >= totalFalse ? '1' : '0';


            for (char[] bitInput : mostCommonBitList) {
                if (bitInput[charIndex] != mostCommonBit)
                    mostCommonBitListFiltered.remove(bitInput);
            }
            if (mostCommonBitListFiltered.size() <= 1)
                break;

            mostCommonBitList = new ArrayList<>(mostCommonBitListFiltered);
        }

        // Least common
        List<char[]> leastCommonBitList = new ArrayList<>(rawInputLines);
        List<char[]> leastCommonBitListFiltered = new ArrayList<>(rawInputLines);


        for (int charIndex = 0; charIndex < leastCommonBitList.get(0).length; charIndex++) {
            int totalTrue = 0;
            int totalFalse = 0;

            for (char[] bitInput : leastCommonBitList){
                if (bitInput[charIndex] == '1') {
                    totalTrue++;
                }else{
                    totalFalse++;
                }
            }

            char leastCommonBit = totalTrue >= totalFalse ? '0' : '1';


            for (char[] bitInput : leastCommonBitList) {
                if (bitInput[charIndex] != leastCommonBit)
                    leastCommonBitListFiltered.remove(bitInput);
            }
            if (leastCommonBitListFiltered.size() <= 1)
                break;

            leastCommonBitList = new ArrayList<>(leastCommonBitListFiltered);
        }


        // Result
        String filteredStringMostCommon = new String(mostCommonBitListFiltered.get(0));
        System.out.printf("Most common: %s (%d)\n",
                filteredStringMostCommon, Long.parseLong(filteredStringMostCommon, 2)
        );

        String filteredStringLeastCommon = new String(leastCommonBitListFiltered.get(0));
        System.out.printf("Least common: %s (%d)\n",
                filteredStringLeastCommon, Long.parseLong(filteredStringLeastCommon, 2)
        );
    }
}
