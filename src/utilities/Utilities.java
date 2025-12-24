package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Utilities {
    private static final Scanner SC = new Scanner(System.in);

    // Backward-compatible: default range 1..4
    public static int getIntInRange() {
        return getIntInRange(1, 4);
    }

    // Generalized range method
    public static int getIntInRange(int min, int max) {
        int number;
        while (true) {
            try {
                String line = SC.nextLine().trim();
                number = Integer.parseInt(line);
                while (number < min || number > max) {
                    System.out.print("Please enter a number between " + min + " and " + max + ": ");
                    number = Integer.parseInt(SC.nextLine().trim());
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter an integer: ");
            }
        }
    }

    public static int getInt() {
        int number;
        while (true) {
            try {
                number = Integer.parseInt(SC.nextLine().trim());
                while (number < 0) {
                    System.out.print("Please enter a non-negative integer: ");
                    number = Integer.parseInt(SC.nextLine().trim());
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter an integer: ");
            }
        }
    }

    public static double getDouble() {
        double number;
        while (true) {
            try {
                number = Double.parseDouble(SC.nextLine().trim());
                while (number < 0) {
                    System.out.print("Please enter a non-negative number: ");
                    number = Double.parseDouble(SC.nextLine().trim());
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    public static String getString() {
        String input;
        while (true) {
            input = SC.nextLine();
            if (!input.trim().isEmpty()) {
                return input.trim();
            } else {
                System.out.print("Input cannot be empty. Please enter a valid string: ");
            }
        }
    }

    public static String getCurrentTimeAndDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(date);
    }
    
    
}// end class
