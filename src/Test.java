import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String inputFilePath = "city1";
        String outputFilePath = "city2";

        List<String> inputStrings = readStringsFromFile(inputFilePath);
        List<String> reversedStrings = reverseStringsHorizontally(inputStrings);
        writeStringsToFile(outputFilePath, reversedStrings);
    }

    private static List<String> readStringsFromFile(String filePath) {
        List<String> strings = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return strings;
    }

    private static List<String> reverseStringsHorizontally(List<String> strings) {
        List<String> reversedStrings = new ArrayList<>();

        for (String original : strings) {
            String[] parts = original.split(",");
            StringBuilder reversed = new StringBuilder();
            for (int i = parts.length - 1; i >= 0; i--) {
                reversed.append(parts[i]);
                if (i > 0) {
                    reversed.append(",");
                }
            }
            reversedStrings.add(reversed.toString());
        }

        return reversedStrings;
    }

    private static void writeStringsToFile(String filePath, List<String> strings) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String reversed : strings) {
                writer.write(reversed);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
