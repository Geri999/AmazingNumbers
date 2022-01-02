package numbers;

import java.util.Scanner;

public class UserInterface {
    static String[] askUserForArguments() {
        System.out.print("\n\nEnter a request: ");
        return new Scanner(System.in).nextLine().toUpperCase().split(" ");
    }
}