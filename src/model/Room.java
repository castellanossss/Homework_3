package model;

import java.util.ArrayList;

public class Room {
    private int id;
    private int floorNumber;
    private int roomNumber;
    private int numberOfBeds;
    private ArrayList<Patient> activePatients;
    private ArrayList<Patient> inactivePatients;

    public Room(int id, int floorNumber, int roomNumber, int numberOfBeds) {
        this.id = id;
        this.floorNumber = floorNumber;
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
        this.activePatients = new ArrayList<>();
        this.inactivePatients = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public ArrayList<Patient> getActivePatients() {
        return activePatients;
    }

    public void setActivePatients(ArrayList<Patient> activePatients) {
        this.activePatients = activePatients;
    }

    public ArrayList<Patient> getInactivePatients() {
        return inactivePatients;
    }

    public void setInactivePatients(ArrayList<Patient> inactivePatients) {
        this.inactivePatients = inactivePatients;
    }

    public void addActivePatient(Patient patient) {
        activePatients.add(patient);
    }

    public void addInactivePatient(Patient patient) {
        inactivePatients.add(patient);
    }

    @Override
    public String toString() {
        return "Room [id=" + id + ", floorNumber=" + floorNumber + ", roomNumber=" + roomNumber + ", numberOfBeds="
                + numberOfBeds + ", activePatients=" + activePatients + ", inactivePatients=" + inactivePatients + "]";
    }

}

