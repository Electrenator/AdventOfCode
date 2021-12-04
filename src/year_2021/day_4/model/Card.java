package year_2021.day_4.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Model for bingo cards
 *
 * @author Electrenator (electrenator2000@protonmail.com)
 * @since 17
 */
public class Card {
    public final static int CARD_SIZE = 5;

    private List<List<Integer>> cardGrid;

    public Card(List<List<Integer>> cardGrid) {
        this.cardGrid = cardGrid;
    }

    public boolean hasBingo(List<Integer> drawnNumbers) {
        return checkBingo(drawnNumbers) != null;
    }

    public List<Integer> checkBingo(List<Integer> drawnNumbers) {
        List<List<Integer>> rowOrColWithBingo = this.streamAllRowsAndColms()
                .filter((values) -> {
                    if (drawnNumbers.size() < CARD_SIZE)
                        return false;

                    for (Integer neededNumbers : values) {
                        int numberPosition = drawnNumbers.indexOf(neededNumbers);
                        if (numberPosition <= 0)
                            return false;
                    }
                    return true;
                }).toList();

        return rowOrColWithBingo.size() > 0 ? rowOrColWithBingo.get(0) : null;
    }

    private Stream<List<Integer>> streamAllRowsAndColms() {
        List<List<Integer>> allRowsAndColumns = new ArrayList<>();

        for (int i = 0; i < CARD_SIZE; i++) {
            allRowsAndColumns.add(getRow(i));
            allRowsAndColumns.add(getColumn(i));
        }

        return allRowsAndColumns.stream();
    }

    private List<Integer> getRow(int rowIndex) {
        return cardGrid.get(rowIndex);
    }

    private List<Integer> getColumn(int columnIndex) {
        List<Integer> cardColum = new ArrayList<>();

        for (List<Integer> row : cardGrid) {
            cardColum.add(row.get(columnIndex));
        }

        return cardColum;
    }

    public static Card fromLines(String[] inputLines) {
        List<List<Integer>> cardGrid = new ArrayList<>();

        for (int i = 0; i < CARD_SIZE; i++) {
            String[] lineValues = inputLines[i].strip().split("\\D+");
            List<Integer> lineNumbers = new ArrayList<>(CARD_SIZE);

            for (int j = 0; j < CARD_SIZE; j++) {
                lineNumbers.add(Integer.parseInt(lineValues[j]));
            }

            cardGrid.add(lineNumbers);
        }

        return new Card(cardGrid);
    }
}
