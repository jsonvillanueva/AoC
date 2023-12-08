import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

class Day2 {
    private static final Map<String, Integer> maxCubes = Map.of("red", 12,"green", 13, "blue", 14);
    public static void main(String[] args) throws IOException{
        Path path = Paths.get("inputs/2.txt");
        Stream<String> lines = Files.newBufferedReader(path).lines();
        Integer part1 = lines.map(Day2::partOne).mapToInt(l -> l).sum();
        System.out.println(part1);

        lines = Files.newBufferedReader(path).lines();
        Integer part2 = lines.map(Day2::partTwo).mapToInt(l -> l).sum();
        System.out.println(part2);

    }

    private static HashMap<String, Integer> partOneAndTwoHelper(String line) {
        Pattern patternGame = Pattern.compile("Game (\\d+): (.+)");
        Matcher matcherGame = patternGame.matcher(line);
        Integer gameID = Integer.valueOf(0);
        HashMap<String, Integer> cubeCounts = new HashMap<>();
        cubeCounts.put("red", 0);
        cubeCounts.put("green", 0);
        cubeCounts.put("blue", 0);

        if (matcherGame.find()) {
            gameID = Integer.valueOf(matcherGame.group(1)); 
            cubeCounts.put("game", gameID);
            Pattern patternSet = Pattern.compile("(\\d+) (\\w+)");
            Matcher matcherSet = patternSet.matcher(line);
            while (matcherSet.find()){
                Integer amount = Integer.valueOf(matcherSet.group(1));
                String color = matcherSet.group(2);
                cubeCounts.put(color, Math.max(amount, cubeCounts.get(color)));
            }
        };
        return cubeCounts;
    }

    private static Integer partOne(String line){
        HashMap<String, Integer> cubeCounts= partOneAndTwoHelper(line);
        
        for (String key : maxCubes.keySet()) {
            if (cubeCounts.get(key) > maxCubes.get(key)) {
                return Integer.valueOf(0);
            }
        }
        return cubeCounts.get("game");
    }

    private static Integer partTwo(String line){
        HashMap<String, Integer> cubeCounts= partOneAndTwoHelper(line);
        return cubeCounts.get("blue") * cubeCounts.get("red") * cubeCounts.get("green");
    }
}