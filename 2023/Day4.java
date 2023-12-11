import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class Day4 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("2023/inputs/4.txt");
        Stream<String> lines = Files.newBufferedReader(path).lines();
        Integer solution = lines.map(Day4::partOne).mapToInt(c -> c).sum();
    }
    private static Integer partOne(String line) {
        String[] parts = line.split(":");
        String interestingBit = parts[1];
        String[] cards = interestingBit.split("\\|");
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
        
        return Integer.valueOf(1 << (winningNumbers.size()-1));

    }

    // private static Integer matchingIntegers(String line) {

    // }
}
