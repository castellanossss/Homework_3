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

    public boolean validateRoomsNotEmpty() {
        if (rooms.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean validateRoomIdExists(int roomId) {
        for (Room room : rooms) {
            if (room.getId() == roomId) {
                return true;
            } 
        }
        return false;
    }

    public boolean overcrowedRoom(Patient patient) {
        for (Room room : rooms) {
            if (room.getId() == patient.getRoomId()) {
                if (room.getActivePatients().size() >= room.getNumberOfBeds()) {
                    System.out.println(Constants.SEPARATOR + "..........................................................");
                    String m1 = Constants.OVERCROWED_M1;
                    String m2 = Constants.OVERCROWED_M2;
                    for (int i = 0; i < room.getActivePatients().size(); i++) {
                        if (room.getActivePatients().get(i).getStatus() == Status.ACTIVE) {
                            m2 += ("[ " + (i + 1) + " ] ");
                        }
                    }
                    System.out.print(m1 + m2 + "\nEnter the position of the patient you want to change to INACTIVE: ");
                    int position = Integer.parseInt(io.next());
    
                    room.getActivePatients().get(position - 1).setStatus(Status.INACTIVE);
                    patientView.printBorderedMessage(Constants.DISPLAY_PATIENT_STATUS_CHANGE_SUCCESS);
                    
                    boolean replaced = false;
                    for (int i = 0; i < room.getActivePatients().size(); i++) {
                        if (room.getActivePatients().get(i).getStatus() == Status.INACTIVE) {
                            room.addInactivePatient(room.getActivePatients().get(i));
                            room.getActivePatients().set(i, patient);
                            replaced = true;
                            break;
                        }
                    }
                    if (!replaced) {
                        room.getActivePatients().add(patient);
                    }
                    return true;
                } else {
                    room.getActivePatients().add(patient);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean eventualEmptyArray() {
        boolean isEmpty = validateRoomsNotEmpty();
        if (isEmpty) {
            patientView.printBorderedMessage(Constants.DISPLAY_EMPTY_ROOM_LIST_MESSAGE);
            return true;
        }
        return false;
    }

    public int eventualExistingRoom() {
        boolean flag = true;
        int roomId = 0;
        do {
            try {
                String input = patientView.showMessageData(Constants.ASK_FOR_PATIENT_ROOM_ID);
                roomId = Integer.parseInt(input);
                flag = false;
                while (!validateRoomIdExists(roomId)) {
                    patientView.printBorderedMessage(Constants.DISPLAY_ROOM_NOT_FOUND_MESSAGE);
                    input = patientView.showMessageData(Constants.ASK_FOR_PATIENT_ROOM_ID);
                    roomId = Integer.parseInt(input);
                }
            } catch (NumberFormatException e) {
                patientView.printBorderedMessage(Constants.DISPLAY_INPUT_TYPE_ERROR);
                flag = true;
            }
        } while (flag);

        return roomId;
    }

    public void createPatient() {
        int roomId = eventualExistingRoom();
        String firstName = patientView.showMessageData(Constants.ASK_FOR_PATIENT_FIRST_NAME);
        String lastName = patientView.showMessageData(Constants.ASK_FOR_PATIENT_LAST_NAME);
        String phoneNumber = patientView.showMessageData(Constants.ASK_FOR_PATIENT_PHONE_NUMBER);

        Patient patient = new Patient(roomId, firstName, lastName, phoneNumber, Status.ACTIVE);

        boolean added = overcrowedRoom(patient);

        if (added) {
            patientView.printBorderedMessage(Constants.DISPLAY_PATIENT_REGISTRATION_SUCCESS);
        } 
    }

    public boolean validateACPatientsNotEmpty(int roomId) {
        for (Room room : rooms) {
            if (room.getId() == roomId) {
                if (room.getActivePatients().isEmpty()) {
                    patientView.printBorderedMessage(Constants.DISPLAY_EMPTY_PATIENTS_LIST_MESSAGE);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validateINPatientsNotEmpty(int roomId) {
        for (Room room : rooms) {
            if (room.getId() == roomId) {
                if (room.getInactivePatients().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    public int eventualExistingIdRoom() {
        boolean flag = true;
        int roomId = 0;
        do {
            try {
                String input = roomView.showMessageData(Constants.ASK_FOR_ROOM_PATIENTS_HISTORY);
                roomId = Integer.parseInt(input);
                flag = false;
                while (!validateRoomIdExists(roomId)) {
                    patientView.printBorderedMessage(Constants.DISPLAY_ROOM_NOT_FOUND_MESSAGE);
                    input = roomView.showMessageData(Constants.ASK_FOR_ROOM_PATIENTS_HISTORY);
                    roomId = Integer.parseInt(input);
                }
            } catch (NumberFormatException e) {
                patientView.printBorderedMessage(Constants.DISPLAY_INPUT_TYPE_ERROR);
                flag = true;
            }
        } while (flag);

        return roomId;
    }

    public String showACPatientsHistory(int roomId) {
        String message = "Active patients in room " + roomId + ":\n";
        for (int i = 0; i < rooms.get(roomId - 1).getActivePatients().size(); i++) {
            message += (i + 1) + ". " + rooms.get(roomId - 1).getActivePatients().get(i).getFirstName() + " " + rooms.get(roomId - 1).getActivePatients().get(i).getLastName() + " ~~ [ " + rooms.get(roomId - 1).getActivePatients().get(i).getStatus() + " ]" + "\n";
        }
        return message;
    }

    public String showINPatientsHistory(int roomId) {
        System.out.println(Constants.SEPARATOR);
        String message = "\n" + "Inactive patients in room " + roomId + ":\n";
        for (int i = 0; i < rooms.get(roomId - 1).getInactivePatients().size(); i++) {
            message += (i + 1) + ". " + rooms.get(roomId - 1).getInactivePatients().get(i).getFirstName() + " " + rooms.get(roomId - 1).getInactivePatients().get(i).getLastName() + " ~~ [ " + rooms.get(roomId - 1).getInactivePatients().get(i).getStatus() + " ]" + "\n";
        }
        return message;
    }

    public boolean showHistory() {
        boolean flag = false;
        String message = "";
        if (!eventualEmptyArray()) {
            flag = false;
            int roomId = eventualExistingIdRoom();

            if (!validateACPatientsNotEmpty(roomId)) {
                message += showACPatientsHistory(roomId);
            } else {
                flag = true;
            }

            if (!validateINPatientsNotEmpty(roomId)) {
                message += showINPatientsHistory(roomId);
            }  else {
                message += "\n" + "*** There are no inactive patients in room " + roomId + ". ***\n";
                flag = true;
            }

            System.out.print(message);
            System.out.println(Constants.SEPARATOR);
        } else {
            flag = true;
        }
        return flag;
    }
}
