package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {

    static boolean checkIfNumberIsEven(long i) {
        return (i % 2) == 0;
    }

    static boolean checkIfNumberIsOdd(long i) {
        return (i % 2) != 0;
    }

    static boolean checkIfNumberIsBuzz(long i) {
        return (i % 7 == 0 || i % 10 == 7);
    }

    static boolean checkIfNumberIsDuck(long i) {
        return String.valueOf(i).substring(1).contains("0");
    }

    static boolean checkIfNumberIsPalindromic(long i) {
        return new StringBuffer(String.valueOf(i)).reverse().toString().equals(String.valueOf(i));
    }

    static boolean checkIfNumberIsGapful(long number) {
        String[] digitsAsString = String.valueOf(number).split("");
        if (digitsAsString.length < 3) return false;
        int concatenationOfFirstAndLastDigit = Integer.parseInt(digitsAsString[0] + digitsAsString[digitsAsString.length - 1]);
        return number % concatenationOfFirstAndLastDigit == 0;
    }

    static boolean checkIfNumberIsSpy(long number) {
        int[] digits = convertNumberToArrayOfInts(number);

        long sum = 0;
        long product = 1;

        for (int i = 0; i < digits.length; i++) {
            sum = sum + digits[i];
            product = product * digits[i];
        }

        return sum == product;
    }

    static boolean checkIfNumberIsSunny(long number) {
        return checkIfNumberIsPerfectSquare(number + 1);
    }

    static boolean checkIfNumberIsPerfectSquare(long number) {
        return Math.sqrt(number) % 1 == 0;
    }

    static boolean checkIfNumberIsJumping(long number) {
        int[] digits = convertNumberToArrayOfInts(number);
        for (int i = 1; i < digits.length; i++) {
            if (Math.abs(digits[i - 1] - digits[i]) != 1) return false;
        }
        return true;
    }

    private static int[] convertNumberToArrayOfInts(long number) {
        String[] digitsAsString = String.valueOf(number).split("");
        int[] digits = Arrays.stream(digitsAsString).map(Integer::parseInt).mapToInt(a -> a).toArray();
        return digits;
    }

    static boolean checkIfNumberIsHappy(long number) {
        List<Long> results = new ArrayList<>();
        long result;
        do {
            result = getReduce(convertNumberToArrayOfInts(number));
            if (results.contains(result)) return false;
            results.add(number = result);

        }
        while (result != 1);
        return true;
    }

    private static int getReduce(int[] digits) {
        return Arrays.stream(digits).map(a -> a * a).sum();
    }

    static boolean checkIfNumberIsSad(long number) {
        return Boolean.logicalXor(checkIfNumberIsHappy(number), true);
    }
}