package tools;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import model.Patient;
import model.Room;

public class Write_XML {
    private Document doc;

    public void createXMLStructure() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("hospital");
            doc.appendChild(rootElement);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void addInformation(ArrayList<Room> rooms) {
        Element rootElement = (Element) doc.getElementsByTagName("hospital").item(0);

        for (Room room : rooms) {
            Element roomElement = doc.createElement("room");
            roomElement.setAttribute("id", Integer.toString(room.getId()));
            roomElement.setAttribute("floor", Integer.toString(room.getFloorNumber()));

            Element roomNumber = doc.createElement("roomNumber");
            roomNumber.setTextContent(Integer.toString(room.getRoomNumber()));
            roomElement.appendChild(roomNumber);

            Element numberOfBeds = doc.createElement("numberOfBeds");
            numberOfBeds.setTextContent(Integer.toString(room.getNumberOfBeds()));
            roomElement.appendChild(numberOfBeds);

            Element activePatientsElement = doc.createElement("activePatients");
            for (Patient patient : room.getActivePatients()) {
                Element patientElement = doc.createElement("patient");
                patientElement.setAttribute("roomId", Integer.toString(patient.getRoomId()));

                Element firstNameElement = doc.createElement("firstName");
                firstNameElement.setTextContent(patient.getFirstName());
                patientElement.appendChild(firstNameElement);

                Element lastNameElement = doc.createElement("lastName");
                lastNameElement.setTextContent(patient.getLastName());
                patientElement.appendChild(lastNameElement);

                Element phoneNumberElement = doc.createElement("phoneNumber");
                phoneNumberElement.setTextContent(patient.getPhoneNumber());
                patientElement.appendChild(phoneNumberElement);

                Element statusElement = doc.createElement("status");
                statusElement.setTextContent(patient.getStatus().name());
                patientElement.appendChild(statusElement);

                activePatientsElement.appendChild(patientElement);
            }
            roomElement.appendChild(activePatientsElement);

            Element inactivePatientsElement = doc.createElement("inactivePatients");
            for (Patient patient : room.getInactivePatients()) {
                Element patientElement = doc.createElement("patient");
                patientElement.setAttribute("roomId", Integer.toString(patient.getRoomId()));

                Element firstNameElement = doc.createElement("firstName");
                firstNameElement.setTextContent(patient.getFirstName());
                patientElement.appendChild(firstNameElement);

                Element lastNameElement = doc.createElement("lastName");
                lastNameElement.setTextContent(patient.getLastName());
                patientElement.appendChild(lastNameElement);

                Element phoneNumberElement = doc.createElement("phoneNumber");
                phoneNumberElement.setTextContent(patient.getPhoneNumber());
                patientElement.appendChild(phoneNumberElement);

                Element statusElement = doc.createElement("status");
                statusElement.setTextContent(patient.getStatus().name());
                patientElement.appendChild(statusElement);

                inactivePatientsElement.appendChild(patientElement);
            }
        roomElement.appendChild(inactivePatientsElement);
        rootElement.appendChild(roomElement);
        }
    }

    public void createXMLFile(String filePath) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
