package view;

import java.util.Scanner;

public class Menus {
    private Scanner scanner; 

    public Menus() {
        scanner = new Scanner(System.in);
    }

    public String showMainMenu() {
        System.out.println("+---------------------------------+");
        System.out.println("|            WELCOME TO           |");
        System.out.println("|            COLSANITAS           |");
        System.out.println("+---------------------------------+");
        System.out.println("|        AVAILABLE OPTIONS        |");
        System.out.println("+---------------------------------+");
        System.out.println("| 1. Create a room                |");
        System.out.println("| 2. Create a patient             |");
        System.out.println("| 3. Show patient history by room |");
        System.out.println("| 4. Generate XML                 |");
        System.out.println("| 5. Exit                         |");
        System.out.println("+---------------------------------+");
        System.out.print("Enter your option: ");
        return scanner.next();
    }

    public String showProcessedMenu() {
        System.out.println("+---------------------------------+");
        System.out.println("|        FINISHED PROCESSES       |");
        System.out.println("+---------------------------------+");
        System.out.println("|        AVAILABLE OPTIONS        |");
        System.out.println("+---------------------------------+");
        System.out.println("| 1. Back to main menu            |");
        System.out.println("| 2. Exit                         |");
        System.out.println("+---------------------------------+");
        System.out.print("Enter your option: ");
        return scanner.next();
    }

    public void printExitMessage() {
        String message = "Thank you for using our system!";
        int messageLength = message.length();
        String border = "+" + "-".repeat(messageLength + 2) + "+";
        System.out.println(border);
        System.out.println("| " + message + " |");
        System.out.println(border);
    }

    public void printINOptionMessage() {
        String message = "Not a valid option. Please try again.";
        int messageLength = message.length();
        String border = "+" + "-".repeat(messageLength + 2) + "+";
        System.out.println(border);
        System.out.println("| " + message + " |");
        System.out.println(border);
    }

    public void closeScanner() {
        scanner.close();
    }

}
