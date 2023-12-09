import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import matrix.Coordinate;

public class Day3 {
    public static void main(String args[]) {
        List<String> input = readLines();
        // List<String> input = List.of("467..114..", "...*......", "..35..633.", "......#...", "617*......", ".....+.58.", "..592.....", "......755.", "...$.*....", ".664.598..");
        input.forEach(System.out::println);
        Integer solution = partOne(input);
        System.out.println(solution);
    }

    private static Integer partOne(List<String> lines) {
        Integer sum = 0;
        for (int y = 0; y < lines.size(); ++y){
            for (int x = 0; x < lines.getFirst().length(); ++x) {
                Character charAtYX = lines.get(y).charAt(x);
                if (charIsSymbol(charAtYX)) {
                    Coordinate coord = new Coordinate(y, x);
                    List<Coordinate> surroundingChars = coord.surrounding(y, x);
                    Set<Coordinate> validNumberStarts = surroundingChars.stream()
                        .filter(c -> c.isInBounds(lines) && Character.isDigit(lines.get(c.getRow()).charAt(c.getCol())))
                        .map(c -> c.getStartOfNumber(lines))
                        .collect(Collectors.toCollection(HashSet::new));
                    System.out.println("Valid starts:" + validNumberStarts.size());
                    sum += validNumberStarts.stream().mapToInt(c -> c.readCoordinateNumber(lines)).sum();
                }
            }
        }
        return Integer.valueOf(sum);
    }


    private static boolean charIsSymbol(Character c){
        if (c == '.' || Character.isDigit(c)) {
            return false;
        }
        return true;
    }

    private static List<String> readLines() {
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/3.txt"))){
            return br.lines().toList();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
