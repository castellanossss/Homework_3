package presenter;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import model.Patient;
import model.Room;
import model.Status;
import view.Menus;
import view.PatientRegistrationView;
import view.RoomRegistrationView;

public class Presenter {
    private Scanner io;
    private RoomRegistrationView roomView;
    private ArrayList<Room> rooms;
    private PatientRegistrationView patientView;
    private Menus menu;

    public Presenter() {
        io = new Scanner(System.in);
        roomView = new RoomRegistrationView();
        rooms = new ArrayList<>();
        patientView = new PatientRegistrationView();
        menu = new Menus();
    }

    public boolean validateRoomId(int roomId) {
        for (Room room : rooms) {
            if (room.getId() == roomId) {
                roomView.displayRoomIdAlreadyExistsError(roomId);
                return true;
            }
        }
        return false;
    }

    public boolean validateRoomNumber(int roomNumber, int floorNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.getFloorNumber() == floorNumber) {
                roomView.displayRoomNumberAlreadyExistsError(roomNumber, floorNumber);
                return true;
            }
        }
        return false;
    }

    public boolean validateRoomFloorNumber(int floorNumber) {
        boolean isValid = true;
        if (floorNumber < 1 || floorNumber > 30) {
            roomView.printBorderedMessage(Constants.DISPLAY_FLOOR_NUMBER_ERROR);
            isValid = false;
        }
        return isValid;
    }

    public boolean validateRoomBedNumber(int bedNumber) {
        boolean isValid = true;
        if (bedNumber < 1 || bedNumber > 5) {
            roomView.printBorderedMessage(Constants.DISPLAY_BED_NUMBER_ERROR);
            isValid = false;
        }
        return isValid;
    }

    public int checkId() {
        boolean flag = true;
        int id = 0;
        do {
            try {
                String input = roomView.showMessageData(Constants.ASK_FOR_ROOM_ID);
                id = Integer.parseInt(input);
                flag = false;
                while (validateRoomId(id)) {
                    input = roomView.showMessageData(Constants.ASK_FOR_ROOM_ID);
                    id = Integer.parseInt(input);
                }
            } catch (NumberFormatException e) {
                roomView.printBorderedMessage(Constants.DISPLAY_INPUT_TYPE_ERROR);
                flag = true;
            }
        } while (flag);

        return id;
    }

    public int checkRoomNumber(int floorNumber) {
        boolean flag = true;
        int roomNumber = 0;
        do {
            try {
                String input = roomView.showMessageData(Constants.ASK_FOR_ROOM_NUMBER);
                roomNumber = Integer.parseInt(input);
                flag = false;
                while (validateRoomNumber(roomNumber, floorNumber)) {
                    input = roomView.showMessageData(Constants.ASK_FOR_ROOM_NUMBER);
                    roomNumber = Integer.parseInt(input);
                }
            } catch (NumberFormatException e) {
                roomView.printBorderedMessage(Constants.DISPLAY_INPUT_TYPE_ERROR);
                flag = true;
            }
        } while (flag);

        return roomNumber;
    }

    public int checkFloorNumber() {
        boolean flag = true;
        int floorNumber = 0;
        do {
            try {
                String input = roomView.showMessageData(Constants.ASK_FOR_FLOOR_NUMBER);
                floorNumber = Integer.parseInt(input);
                flag = false;
                while (!validateRoomFloorNumber(floorNumber)) {
                    input = roomView.showMessageData(Constants.ASK_FOR_FLOOR_NUMBER);
                    floorNumber = Integer.parseInt(input);
                }
            } catch (NumberFormatException e) {
                roomView.printBorderedMessage(Constants.DISPLAY_INPUT_TYPE_ERROR);
                flag = true;
            }
        } while (flag);

        return floorNumber;
    }


    public int checkBedsNumber() {
        boolean flag = true;
        int beds = 0;
        do {
            try {
                String input = roomView.showMessageData(Constants.ASK_FOR_NUMBER_OF_BEDS);
                beds = Integer.parseInt(input);
                flag = false;
                while (!validateRoomBedNumber(beds)) {
                    input = roomView.showMessageData(Constants.ASK_FOR_NUMBER_OF_BEDS);
                    beds = Integer.parseInt(input);
                }
            } catch (NumberFormatException e) {
                roomView.printBorderedMessage(Constants.DISPLAY_INPUT_TYPE_ERROR);
                flag = true;
            }
        } while (flag == true);

        return beds;
    }

    public Room createRoom() {
        roomView.printBorderedTitleMessage(); 
        int id = checkId();
        int floorNumber = checkFloorNumber();
        int roomNumber = checkRoomNumber(floorNumber);
        int beds = checkBedsNumber();

        Room room = new Room(id, floorNumber, roomNumber, beds);
        rooms.add(room);
        roomView.printBorderedMessage(Constants.DISPLAY_ROOM_REGISTRATION_SUCCESS);
        return room;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
