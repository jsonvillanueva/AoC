import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;


class Day1{
    private static final Map<String, Integer> DIGIT_NAMES = Map.of(
        "one", 1,
        "two", 2,
        "three", 3,
        "four", 4,
        "five", 5,
        "six", 6,
        "seven", 7,
        "eight", 8,
        "nine", 9
    );
    public static void main(String[] args) throws IOException {
        
        Path path = Paths.get("inputs/1.txt");
        Stream<String> lines = Files.newBufferedReader(path).lines();
        Integer part1 = lines.map(Day1::firstLastDigit).mapToInt(l -> l.getFirst()*10+l.getLast()).sum();
        System.out.println(part1);

        lines = Files.newBufferedReader(path).lines();
        Integer part2 = lines.map(Day1::firstLastDigitOrWord).mapToInt(l -> l.getFirst()*10+l.getLast()).sum();
        System.out.println(part2);
    }

    private static List<Integer> firstLastDigit(String line) {
        List<Integer> result = new ArrayList<>();
        for (int i=0; i < line.length(); ++i) {
            char c = line.charAt(i);
            if ('1' <= c && c <= '9') {
                result.add(c - '0');
            }
        }
        return result;
    }

    private static List<Integer> firstLastDigitOrWord(String line) {
        List<Integer> result = new ArrayList<>();
        for (int i=0; i < line.length(); ++i) {
            char c = line.charAt(i);
            if ('1' <= c && c <= '9') {
                result.add(c - '0');
            }
            for (int j = i; j <= i+6; j++){
                if (j <= line.length() && DIGIT_NAMES.containsKey(line.substring(i,j))) {
                    result.add(DIGIT_NAMES.get(line.substring(i,j)));
                }

            }
        }
        return result;
    }

}