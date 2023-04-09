package tools;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.Patient;
import model.Room;
import model.Status;

public class Read_XML {
    private ArrayList<Room> rooms;

    public Read_XML() {
        rooms = new ArrayList<>();
    }

    public void read_XML_File(String fileName) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(fileName));
            doc.getDocumentElement().normalize();

            NodeList roomList = doc.getElementsByTagName("room");

            for (int i = 0; i < roomList.getLength(); i++) {
                Node roomNode = roomList.item(i);

                if (roomNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element roomElement = (Element) roomNode;

                    int id = Integer.parseInt(roomElement.getAttribute("id"));
                    int floor = Integer.parseInt(roomElement.getAttribute("floor"));

                    NodeList roomInfoList = roomElement.getChildNodes();
                    int roomNumber = 0;
                    int numberOfBeds = 0;
                    ArrayList<Patient> activePatients = new ArrayList<>();
                    ArrayList<Patient> inactivePatients = new ArrayList<>();

                    for (int j = 0; j < roomInfoList.getLength(); j++) {
                        Node roomInfoNode = roomInfoList.item(j);

                        if (roomInfoNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element roomInfoElement = (Element) roomInfoNode;

                            switch (roomInfoElement.getNodeName()) {
                                case "roomNumber":
                                    roomNumber = Integer.parseInt(roomInfoElement.getTextContent());
                                    break;
                                case "numberOfBeds":
                                    numberOfBeds = Integer.parseInt(roomInfoElement.getTextContent());
                                    break;
                                case "activePatients":
                                    NodeList activePatientList = roomInfoElement.getChildNodes();
                                    
                                    for (int k = 0; k < activePatientList.getLength(); k++) {
                                        Node activePatientNode = activePatientList.item(k);
                                        
                                        if (activePatientNode.getNodeType() == Node.ELEMENT_NODE) {
                                            Element activePatientElement = (Element) activePatientNode;
                                            
                                            int roomId = Integer.parseInt(activePatientElement.getAttribute("roomId"));
                                            String firstName = activePatientElement.getElementsByTagName("firstName").item(0).getTextContent();
                                            String lastName = activePatientElement.getElementsByTagName("lastName").item(0).getTextContent();
                                            String phoneNumber = activePatientElement.getElementsByTagName("phoneNumber").item(0).getTextContent();
                                            String status = activePatientElement.getElementsByTagName("status").item(0).getTextContent();
                                            Patient patient = new Patient(roomId, firstName, lastName, phoneNumber, Status.valueOf(status));
                                            
                                            activePatients.add(patient);
                                        }
                                    }
                                    break;
                                case "inactivePatients":
                                    NodeList inactivePatientList = roomInfoElement.getChildNodes();
                                    
                                    for (int k = 0; k < inactivePatientList.getLength(); k++) {
                                        Node inactivePatientNode = inactivePatientList.item(k);
                                        
                                        if (inactivePatientNode.getNodeType() == Node.ELEMENT_NODE) {
                                            Element inactivePatientElement = (Element) inactivePatientNode;
                                            
                                            int roomId = Integer.parseInt(inactivePatientElement.getAttribute("roomId"));
                                            String firstName = inactivePatientElement.getElementsByTagName("firstName").item(0).getTextContent();
                                            String lastName = inactivePatientElement.getElementsByTagName("lastName").item(0).getTextContent();
                                            String phoneNumber = inactivePatientElement.getElementsByTagName("phoneNumber").item(0).getTextContent();
                                            String status = inactivePatientElement.getElementsByTagName("status").item(0).getTextContent();
                                            Patient patient = new Patient(roomId, firstName, lastName, phoneNumber, Status.valueOf(status));
                                            
                                            inactivePatients.add(patient);
                                            }
                                        }
                                        break;
                                default:
                                    break;
                            }
                        }
                    }
                    Room room = new Room(id, floor, roomNumber, numberOfBeds);
                    room.setActivePatients(activePatients);
                    room.setInactivePatients(inactivePatients);
                    rooms.add(room);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
    
}
