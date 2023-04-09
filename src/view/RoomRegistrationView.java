package view;

import java.util.Scanner;

public class RoomRegistrationView {
    private Scanner scanner;

    public RoomRegistrationView() {
        scanner = new Scanner(System.in);
    }

    public void printBorderedTitleMessage() {
        String message = "Room Registration";
        int length = message.length();
        String border = "╔" + "═".repeat(length + 2) + "╗";
        System.out.println(border);
        System.out.println("║ " + message + " ║");
        border = "╚" + "═".repeat(length + 2) + "╝";
        System.out.println(border);
    }

    public String showMessageData(String message) {
        System.out.print(message);
        return scanner.next();
    }

    public void printBorderedMessage(String message) {
        int messageLength = message.length();
        String border = "+" + "-".repeat(messageLength + 2) + "+";
        System.out.println(border);
        System.out.println("| " + message + " |");
        System.out.println(border);
    }

    public void displayRoomIdAlreadyExistsError(int roomId) {
        String message = "Error: Room ID " + roomId + " already exists! Please try again.";
        int messageLength = message.length();
        String border = "+" + "-".repeat(messageLength + 2) + "+";
        System.out.println(border);
        System.out.println("| " + message + " |");
        System.out.println(border);
    }

    public void displayRoomNumberAlreadyExistsError(int roomNumber, int floorNumber) {
        String message = "Error: Room number " + roomNumber + " already exists on floor " + floorNumber + "!" + " Please try again.";
        int messageLength = message.length();
        String border = "+" + "-".repeat(messageLength + 2) + "+";
        System.out.println(border);
        System.out.println("| " + message + " |");
        System.out.println(border);
    }

}
