package numbers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static numbers.Print.*;
import static numbers.Properties.valueOf;
import static numbers.Properties.values;
import static numbers.UserInterface.askUserForArguments;

public class Main {
    public static void main(String[] args) {
        printIntro();

        for (; ; ) {
            String[] userArgs = askUserForArguments();
            if (!CheckerService.checkUsersNumbersCorrectness(userArgs)) continue;
            long userNumber = Long.parseLong(userArgs[0]);
            if (userNumber == 0) break;

            switch (userArgs.length) {
                case 1: {
                    calculateAndPrint(userNumber);
                    break;
                }
                case 2: {
                    long numberOfConsecutiveNumbers = Long.parseLong(userArgs[1]);

                    calculateAndPrint(userNumber, numberOfConsecutiveNumbers);
                    break;
                }
                default: {
                    long numberOfConsecutiveNumbers = Long.parseLong(userArgs[1]);
                    String[] properties = Arrays.copyOfRange(userArgs, 2, userArgs.length);
                    calculateAndPrint(userNumber, numberOfConsecutiveNumbers, properties);
                }
            }
        }
        System.out.println("Goodbye!");
    }

    private static void calculateAndPrint(long userNumber) {
        Map<String, Boolean> db = createDB(userNumber);
        printResultVerticalLayout(userNumber, db);
    }

    private static void calculateAndPrint(long userNumber, long numberOfConsecutiveNumbers) {
        for (long i = 0; i < numberOfConsecutiveNumbers; i++) {
            Map<String, Boolean> db = createDB(userNumber + i);
            printResultHorizontalLayout(userNumber + i, db);
        }
    }

    private static void calculateAndPrint(long userNumber, long numberOfConsecutiveNumbers, String[] properties) {
        int counter = 0;
        long[] arrayOfNumbersFound = new long[(int) numberOfConsecutiveNumbers];
        while (counter < numberOfConsecutiveNumbers) {
            boolean numberChecking = true;

            for (String property : properties) {
                boolean resultForProperty = valueOf(property.replaceAll("-", "")).calculate(userNumber);
                numberChecking = numberChecking && (property.contains("-") ? Boolean.logicalXor(true, resultForProperty) : resultForProperty);
            }

            if (numberChecking) {
                arrayOfNumbersFound[counter++] = userNumber;
            }
            userNumber++;
        }

        for (int i = 0; i < arrayOfNumbersFound.length; i++) {
            calculateAndPrint(arrayOfNumbersFound[i], 1);
        }
    }

    private static Map<String, Boolean> createDB(long number) {
        Map<String, Boolean> hashMap = new HashMap<>();

        for (Properties value : values()) {
            hashMap.put(value.nameLowerCase(), value.calculate(number));
        }
        return hashMap;
    }
}