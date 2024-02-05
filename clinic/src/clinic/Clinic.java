package clinic;

import java.io.*;
import java.io.FileReader;
import java.util.Arrays;
import java.io.BufferedReader;

public class Clinic {
  
  Room primaryRoom;
  Room[] rooms;
  Patient[] patients;
  Person[] staff;
  
  public Clinic() {
      
  }
  
  public void readFile(String fileName) {
      
    BufferedReader reader;
      
    try {
       
      char[] array = new char[100];
        
      // Creates a reader to read text file line by line
      reader = new BufferedReader(new FileReader(fileName));
        
      String line = reader.readLine();
        
      String clinicName = line;
        
      line = reader.readLine();
        
      int roomCount = Integer.parseInt(line);
      Room[] tempRooms = new Room[roomCount];
        
      // Create each room and add it to the clinic
      for (int i = 0; i < roomCount; i++) {
        line = reader.readLine();
        String[] data = line.split("\\s+");
        // Join the split strings of name back together
        String[] toJoin = Arrays.copyOfRange(data, 5, data.length);
        String name = String.join(" ", toJoin);
        Room newRoom = new Room(Integer.parseInt(data[0]), Integer.parseInt(data[1]), 
            Integer.parseInt(data[2]), Integer.parseInt(data[3]), i + 1, data[4], name);
        tempRooms[i] = newRoom;
        if (i == 0) {
          this.primaryRoom = newRoom;
        }
      }
      
      this.rooms = tempRooms;
        
      line = reader.readLine();
        
      int staffCount = Integer.parseInt(line);
      Person[] tempStaff = new Person[staffCount];
        
      // Create each staff member and add them to the clinic
      for (int i = 0; i < staffCount; i++) {
        line = reader.readLine();
        String[] data = line.split("\\s+");
        Person newStaff;
        if (data[4].matches("[a-zA-Z]+")) {
          newStaff = new NonclinicalStaff(data[0], data[1], data[2], data[3], data[4]);
        } else {
          newStaff = new ClinicalStaff(data[0], data[1], data[2], data[3], data[4]);
        }
        tempStaff[i] = newStaff;
      }
      
      this.staff = tempStaff;
        
      line = reader.readLine();
        
      int patientCount = Integer.parseInt(line);
      Patient[] tempPatients = new Patient[patientCount];
        
      // Create each patient and add them to the clinic
      for (int i = 0; i < patientCount; i++) {
        line = reader.readLine();
        String[] data = line.split("\\s+");
        Patient newPatient = new Patient(Integer.parseInt(data[0]), data[1], 
            data[2], data[3]);
        tempPatients[i] = newPatient;
      }
      
      this.patients = tempPatients;
            
      // Close the reader
      reader.close();
        
    } catch(IOException e) {
      System.out.println("File not found!");
        
    }
      
  }
  
  public void registerNewPatient(String firstName, 
      String lastName, String DOB) {
    
    Patient patient = new Patient(1, firstName, 
        lastName, DOB);
    patient.register(true);
    this.assignPatientToRoom(patient, 1);
    
    // Make a new larger array of Patients and add the new one
    int newLength = this.patients != null ? this.patients.length + 1 : 1;
    Patient[] tempPatients = new Patient[newLength];
    
    for (int i = 0; i < newLength - 1; i++) {
      tempPatients[i] = this.patients[i];
    }
    
    tempPatients[newLength - 1] = patient;
    this.patients = tempPatients;
    
    
  }
  
  public void registerNewClinicalStaff(String job, String firstName, 
      String lastName, String education, String NPI) {
    
    ClinicalStaff clinicalStaff = new ClinicalStaff(job, firstName, 
        lastName, education, NPI);
    clinicalStaff.activate(true);
    
    // Make a new larger array of staff and add the new one
    int newLength = this.staff != null ? this.staff.length + 1 : 1;
    Person[] tempStaff = new Person[newLength];
    
    for (int i = 0; i < newLength - 1; i++) {
      tempStaff[i] = this.staff[i];
    }
    
    tempStaff[newLength - 1] = clinicalStaff;
    this.staff = tempStaff;
    
  }
  
  public void sendPatientHome(Patient patient) {
    
    // If there is no active clinical staff member for approval,
    // the patient cannot be approved to go home
    
    if (this.staff != null) {
      for (int i = 0; i < this.staff.length; i++) {
        if (staff[i].getClass().equals(ClinicalStaff.class) 
            && ((ClinicalStaff) staff[i]).isActive()) {
          
          ((ClinicalStaff) staff[i]).approvePatientHome(patient);
          break;
          
        }
      }
    }
    
  }
  
  public void deactivateClinicalStaff(ClinicalStaff staff) {
    staff.activate(false);
  }
  
  public void assignPatientToRoom(Patient patient, int room) {
    
    // Assign a patient to this room if there are no other
    // patients or it is a waiting room
    if (this.rooms[room - 1].checkOccupied() == false 
        || this.rooms[room - 1].getType().equals(RoomType.WAITING)) {
      patient.assignRoom(room);
      this.rooms[room - 1].assignPatient(patient);
    
    }
    
  }
  
  public void assignStaffToPatient(ClinicalStaff staff, Patient patient) {
    
    if (staff.isActive() && patient.isRegistered()) {
      staff.assignPatient(patient);
      patient.assignStaff(staff);
    }
    
  }
  
  public String displayRoom(int room) {
    
    String displayString;
    Room selectedRoom = this.rooms[room - 1];
    displayString = selectedRoom.display();
    displayString += "\n";
    System.out.print(displayString);
    return displayString;
    
  }
  
  public String seatingChart() {
    
    String displayString = "";
    // Utilize the displayRoom function written above
    
    if (this.rooms != null) {
      for (int i = 0; i < this.rooms.length; i++) {
        displayString += rooms[i].display();
      }
    }
    
    displayString += "\n";
    System.out.print(displayString);
    return displayString;
    
  }

}
