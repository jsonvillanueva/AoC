import java.io.*;
import java.util.*;


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
    public static void main(String[] args) {
        var lines1 = readLines();
        var part1 = lines1.stream().map(Day1::firstLastDigit).mapToInt(l -> l.getFirst()*10+l.getLast()).sum();
        System.out.println(part1);
        var lines2 = readLines();
        var part2 = lines2.stream().map(Day1::firstLastDigitOrWord).mapToInt(l -> l.getFirst()*10+l.getLast()).sum();
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
        System.out.println(line + Arrays.toString(result.toArray()));
        return result;
    }

    private static List<String> readLines() {
        try (BufferedReader br = new BufferedReader(
                new FileReader(new File("inputs/1-1.txt")))) {
            return br.lines().toList(); 
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}