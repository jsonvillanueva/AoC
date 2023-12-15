import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class Day4 {
    private static final List<Integer> numberOfCards = new ArrayList<>();
    private static int cardIteration = 0;

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("2023/inputs/4.txt");
        Stream<String> lines = Files.newBufferedReader(path).lines();
        Integer solution = lines.map(Day4::partOne).mapToInt(c -> c).sum();
        System.out.println(solution);

        lines = Files.newBufferedReader(path).lines();
        solution = lines.map(Day4::partTwo).mapToInt(c -> c).sum();
        System.out.println(solution);
    }

    private static Integer partOne(String line) {
        numberOfCards.add(1);
        Integer partOneHelper = partOneAndTwoHelper(line);
        return Integer.valueOf(1 << (partOneHelper - 1));
    }

    private static Integer partOneAndTwoHelper(String line) {
        numberOfCards.add(1);
        String[] parts = line.split(":");
        String lotteryNumbersAndCards = parts[1];
        String[] cards = lotteryNumbersAndCards.split("\\|");
        Pattern pattern = Pattern.compile("(\\d+)");
        HashSet<Integer> winningNumbers = new HashSet<>();
        HashSet<Integer> cardNumbers = new HashSet<>();
        Matcher matcher = pattern.matcher(cards[0]);
        while (matcher.find()) {
            winningNumbers.add(Integer.valueOf(matcher.group(1)));
        }
        matcher = pattern.matcher(cards[1]);
        while (matcher.find()) {
            cardNumbers.add(Integer.valueOf(matcher.group(1)));
        }
        winningNumbers.retainAll(cardNumbers);
        if (winningNumbers.size() == 0) {
            return Integer.valueOf(0);
        }
        return Integer.valueOf(winningNumbers.size());
    }

    private static Integer partTwo(String line) {
        Integer cardsToAdd = numberOfCards.get(cardIteration);
        Integer matches = partOneAndTwoHelper(line);
        for (int i = cardIteration + 1; i < numberOfCards.size()
                && i < cardIteration + matches + 1; ++i) {
            numberOfCards.set(i, numberOfCards.get(i) + cardsToAdd);
        }

        return numberOfCards.get(cardIteration++);
    }
}
