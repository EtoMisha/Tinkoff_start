import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task04 {

    private static Scanner scanner;

    public static void main(String[] args) {
        try {
            scanner = new Scanner(new File("resources/config.conf"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        parseBlock(new HashMap<>());
        scanner.close();
    }

    private static void parseBlock(Map<String, Integer> previousVars) {
        Map<String, Integer> vars = new HashMap<>(previousVars);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            if (line.equals("}")) {
                return;
            } else if (line.equals("{")) {
                parseBlock(vars);
            } else {
                String[] pairKeyValue = line.split("=");
                String key = pairKeyValue[0];
                String value = pairKeyValue[1];

                if (isNumeric(value)) {
                    vars.put(key, Integer.parseInt(value));
                } else {
                    vars.put(key, Optional.ofNullable(vars.get(value)).orElse(0));
                    System.out.println(vars.get(key));
                }
            }
        }
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
