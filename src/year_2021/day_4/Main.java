package year_2021.day_4;

import year_2021.day_4.model.Card;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;


/**
 * Advent of Code bingo with Giant Squid
 *
 * @author Electrenator (electrenator2000@protonmail.com)
 * @since 17
 */
public class Main {
    static List<Integer> drawList = new ArrayList<>();
    static List<Card> cardList = new ArrayList<>();

    public static void main(String[] args) {
        // Load data
        File inputFile = new File("src/year_2021/day_4/input");
        Main.importDataFromFile(inputFile);

        // Start drawing numbers until one card wins
        List<Integer> drawnNumbers = new ArrayList<>();
        List<Card> cardsWithBingo = null;

        for (Integer number : drawList) {
            drawnNumbers.add(number);
            cardsWithBingo = cardList.stream()
                    .filter((card) -> card.hasBingo(drawnNumbers))
                    .toList();

            if (cardsWithBingo.size() != 0)
                break;
        }

        System.out.println("Drawn numbers: " + drawnNumbers);

        if (cardsWithBingo == null)
            System.out.println("No card won :/");

        List<Integer> unmarkedNumbersOfWinning = cardsWithBingo.get(0).getUnmarkedNumber(drawnNumbers);
        System.out.println("CARD WON " + unmarkedNumbersOfWinning);
        System.out.printf("Sum: %d\nLast call: %d\nTotalScore: %d",
                unmarkedNumbersOfWinning.stream().mapToInt(Integer::intValue).sum(),
                drawnNumbers.get(drawnNumbers.size() - 1),
                unmarkedNumbersOfWinning.stream().mapToInt(Integer::intValue).sum()
                        * drawnNumbers.get(drawnNumbers.size() - 1)
        );

    }

    public static void importDataFromFile(File inputFile) {
        Scanner scanner;
        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Import drawn list
        Main.drawList = Arrays.stream(scanner.nextLine().split(","))
                .map(Integer::parseInt)
                .toList();

        System.out.println(Main.drawList);

        // Import card Data
        Main.cardList = scanner.findAll("((( )*\\d+( )*){5}\\n){5}")
                .map(MatchResult::group)
                .map((rawCard) -> Card.fromLines(rawCard.split("\n")))
                .toList();
    }
}
