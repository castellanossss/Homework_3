package presenter;

public class Constants {
    public static final String ASK_FOR_ROOM_ID = "Enter Room ID: ";
    public static final String ASK_FOR_FLOOR_NUMBER = "Enter Floor Number: ";
    public static final String ASK_FOR_ROOM_NUMBER = "Enter Room Number: ";
    public static final String ASK_FOR_NUMBER_OF_BEDS = "Enter Number of Beds: ";
    public static final String ASK_FOR_ROOM_PATIENTS_HISTORY = "Enter Room ID to see patients history: ";
    public static final String DISPLAY_ROOM_REGISTRATION_SUCCESS = "Room registered successfully!";
    public static final String DISPLAY_INPUT_TYPE_ERROR = "Error: Only numbers are allowed! Please try again.";
    public static final String DISPLAY_FLOOR_NUMBER_ERROR = "Error: Floor number must be between 1 and 10. Please try again.";
    public static final String DISPLAY_BED_NUMBER_ERROR = "Error: Number of beds must be between 1 and 5. Please try again.";

    public static final String ASK_FOR_PATIENT_ROOM_ID = "Enter Patient Room ID: ";
    public static final String ASK_FOR_PATIENT_FIRST_NAME = "Enter Patient First Name: ";
    public static final String ASK_FOR_PATIENT_LAST_NAME = "Enter Patient Last Name: ";
    public static final String ASK_FOR_PATIENT_PHONE_NUMBER = "Enter Patient Phone Number: ";
    public static final String DISPLAY_PATIENT_REGISTRATION_SUCCESS = "Patient registration successful!";
    public static final String DISPLAY_PATIENT_STATUS_CHANGE_SUCCESS = "Patient status changed successfully!";
    public static final String DISPLAY_ROOM_NOT_FOUND_MESSAGE = "Error: Room not found. Please make sure the room is created.";
    public static final String DISPLAY_EMPTY_ROOM_LIST_MESSAGE = "Error: No rooms created. Please create a room first to add a patient.";
    public static final String DISPLAY_EMPTY_PATIENTS_LIST_MESSAGE = "Error: No patients created. Please create a patient first.";

    public static final String OVERCROWED_M1 = "Seems like there are no spaces available in this room. Change a patient status to INACTIVE to add a new one.\n";
    public static final String OVERCROWED_M2 = "Patients currently active in this room:\n";

    public static final String SEPARATOR = "....................................................";

    public static final String PATH = "data/rooms.xml";
}
