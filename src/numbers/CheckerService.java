package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static numbers.Print.printIntro;
import static numbers.Properties.values;

public class CheckerService {

    public static boolean checkUsersNumbersCorrectness(String[] userArgs) {
        if (userArgs == null || userArgs[0] == null) {
            printIntro();
            return false;
        }

        if (!checkIfFirstParameterIsNaturalOrZeroNumber(userArgs)) return false;
        if (!checkIfSecondParameterIsNaturalNumber(userArgs)) return false;

        if (userArgs.length >= 3) {
            if (!checkCorrectPropertyNames2(userArgs)) return false;
            if (!checkIfRequestContainsMutuallyExclusiveProperties(userArgs)) return false;
        }
        return true;
    }

    private static boolean checkIfRequestContainsMutuallyExclusiveProperties(String[] userArgs) {
        String[] propertyNames = Arrays.copyOfRange(userArgs, 2, userArgs.length);
        List<String> badParameters = new ArrayList<>();

        for (int i = 0; i < propertyNames.length; i++) {

            for (int j = i + 1; j < propertyNames.length; j++) {
                String firstPropertyMutuallyExclusiveCategory = Properties.valueOf(propertyNames[i].replaceAll("-", "")).mutuallyExclusiveProperties;
                String secondPropertyMutuallyExclusiveCategory = Properties.valueOf(propertyNames[j].replaceAll("-", "")).mutuallyExclusiveProperties;
                boolean isFirstPropertySign = propertyNames[i].contains("-");
                boolean isSecondPropertySign = propertyNames[j].contains("-");
                String firstPropertyName = propertyNames[i].replaceAll("-", "");
                String secondPropertyName = propertyNames[j].replaceAll("-", "");

                //e.g. -ODD +ODD
                if (firstPropertyName.equals(secondPropertyName)
                        && isFirstPropertySign != isSecondPropertySign) {
                    badParameters.add(propertyNames[i] + ", " + propertyNames[j]);
                }

                //e.g. -ODD -EVEN
                if (firstPropertyMutuallyExclusiveCategory.equals(secondPropertyMutuallyExclusiveCategory)
                        && (isFirstPropertySign == isSecondPropertySign)  // + +   or   - -
                        && Properties.valueOf(firstPropertyName).isMinusPropertyShouldBeMutuallyExclusiveProperties) {
                    badParameters.add(propertyNames[i] + ", " + propertyNames[j]);
                }

                //e.g. +SUNNY +SQUARE, +ODD +EVEN
                if (firstPropertyMutuallyExclusiveCategory.equals(secondPropertyMutuallyExclusiveCategory)
                        && (!isFirstPropertySign && !isSecondPropertySign)   //  + + only
                        && Properties.valueOf(firstPropertyName).isMinusPropertyShouldBeMutuallyExclusiveProperties == false
                        && !firstPropertyName.equals(secondPropertyName)) {
                    badParameters.add(propertyNames[i] + ", " + propertyNames[j]);
                }
            }
        }

        if (badParameters.size() > 0) {
            System.out.printf("The request contains mutually exclusive properties: ");
            for (String badParameter : badParameters) {
                System.out.print("[" + badParameter + "]");
            }
            System.out.println("\nThere are no numbers with these properties.");
            return false;
        }
        return true;
    }

    private static boolean checkIfSecondParameterIsNaturalNumber(String[] userArgs) {
        if (userArgs.length >= 2) {
            if (Long.parseLong(userArgs[1]) < 1) {
                System.out.println("The second parameter should be a natural number.");
                return false;
            }
        }
        return true;
    }

    private static boolean checkIfFirstParameterIsNaturalOrZeroNumber(String[] userArgs) {
        if (!userArgs[0].chars().allMatch(Character::isDigit) || Long.parseLong(userArgs[0]) < 0) {
            System.out.println("The first parameter should be a natural number or zero.");
            return false;
        }
        return true;
    }

    private static boolean checkCorrectPropertyNames2(String[] userArgs) {

        String[] propertyNames = Arrays.copyOfRange(userArgs, 2, userArgs.length);
        int counter = 0;
        List<String> wrongPropertyNames = new ArrayList<>();

        for (String propertyName : propertyNames) {
            if (!Arrays.stream(values()).anyMatch(s -> String.valueOf(s).equals(propertyName.replaceAll("-", "")))) {
                counter += 1;
                wrongPropertyNames.add(propertyName.toUpperCase());
            }
        }

        String singularOrPluralOfWordProperty;
        String verb;
        if (counter == 0) {
            return true;
        } else if (counter == 1) {
            singularOrPluralOfWordProperty = "y";
            verb = "is";
        } else {
            singularOrPluralOfWordProperty = "ies";
            verb = "are";
        }

        System.out.printf("The propert%s %s %s wrong.\n", singularOrPluralOfWordProperty, wrongPropertyNames, verb);
        System.out.println("Available properties: " + Arrays.toString(values()));
        return false;
    }
}