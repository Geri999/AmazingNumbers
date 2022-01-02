package numbers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Print {

    static void printIntro() {

        String intro = """
                Welcome to Amazing Numbers!
                                
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be processed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.""";

        System.out.print(intro);
    }

    static void printResultVerticalLayout(long number, Map<String, Boolean> db) {
        System.out.println("\nProperties of " + number);
        for (Map.Entry<String, Boolean> stringBooleanEntry : db.entrySet()) {
            String s = String.format("%12s: %1s\n", stringBooleanEntry.getKey(), stringBooleanEntry.getValue()).replaceAll("\\[|\\]", "");
            System.out.print(s);
        }
    }

    static void printResultHorizontalLayout(long number, Map<String, Boolean> db) {
        System.out.printf("\n%12d is ", number);
        List<String> collect = db.entrySet().stream().filter(s -> s.getValue() == true).map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.print((collect.toString()).replaceAll("\\[|\\]", ""));

    }
}