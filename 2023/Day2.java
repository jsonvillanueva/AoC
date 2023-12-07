import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

class Day2 {
    public static void main(String[] args) throws IOException{
        Path path = Paths.get("inputs/2.txt");
        Stream<String> lines = Files.newBufferedReader(path).lines();
        Integer part1 = lines.map(Day2::partOne).mapToInt(l -> l).sum();
        System.out.println(part1);

    }
    private static Integer partOne(String line){
        Pattern patternGame = Pattern.compile("Game (\\d+): (.+)");
        Matcher matcherGame = patternGame.matcher(line);
        Integer gameID = Integer.valueOf(0);
        HashMap<String, Integer> cubeCounts = new HashMap<>();
        cubeCounts.put("red", 0);
        cubeCounts.put("green", 0);
        cubeCounts.put("blue", 0);

        if (matcherGame.find()) {
            gameID = Integer.valueOf(matcherGame.group(1)); 
            Pattern patternSet = Pattern.compile("(\\d+) (\\w+)");
            Matcher matcherSet = patternSet.matcher(line);
            while (matcherSet.find()){
                Integer amount = Integer.valueOf(matcherSet.group(1));
                String color = matcherSet.group(2);
                cubeCounts.put(color, Math.max(amount, cubeCounts.get(color)));
            }

        };

        HashMap<String, Integer> maxCubes = new HashMap<>();
        maxCubes.put("red", 12);
        maxCubes.put("green", 13);
        maxCubes.put("blue", 14);
        for (String key : maxCubes.keySet()) {
            if (cubeCounts.get(key) > maxCubes.get(key)) {
                return Integer.valueOf(0);
            }
        }
        return gameID;
    }
}