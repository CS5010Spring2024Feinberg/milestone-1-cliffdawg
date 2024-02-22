package clinic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

/**
 * This class represents a clinic. The clinic has a primary
 * waiting room, an array of rooms, an array of patients, and
 * an array of staff members.
 */
public class Clinic implements Organization {
  
  private Room primaryRoom;
  private Room[] rooms;
  private Patient[] patients;
  private AbstractStaff[] staff;
  
  public Clinic() {
    // Would read file through constructor, but assignment
    // description states it needs the ability to read text file
    // into model, like it should be possible for the model to
    // do at any time. Placed it in a separate readFile method instead.
  }
  
  /** 
   * Reads the input file specified by its file name and path.
   * Generates objects based on the information presented for
   * the clinic. 
   * 
   * @param fileName     The name and path of the input file
   */
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
      AbstractStaff[] tempStaff = new AbstractStaff[staffCount];
        
      // Create each staff member and add them to the clinic
      for (int i = 0; i < staffCount; i++) {
        line = reader.readLine();
        String[] data = line.split("\\s+");
        AbstractStaff newStaff;
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
        
    } catch (IOException e) {
      
      // Alert user to missing file issue
      System.out.println("File not found!");
        
    }
      
  }
  
  /** 
   * Registers a new patient to the clinic using
   * their given information. 
   * 
   * @param firstName   The patient's first name
   * @param lastName    The patient's last name
   * @param dob         The patient's date of birth
   * @param Object      The information of a visit record
   */
  public void registerNewPatient(String firstName, 
      String lastName, String dob, Object... record) {
    
    Patient patient = new Patient(1, firstName, 
        lastName, dob);
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
    
    if (record != null) {
      patient.registerVisitRecord((Date) record[0], 
          (String) record[1], (double) record[2]);
    }
    
  }
  
  /** 
   * Registers an existing patient in the clinic using
   * their given information. 
   * 
   * @param first   The patient's first name
   * @param last    The patient's last name
   * @param Object      The information of a visit record
   */
  public void registerExistingPatient(String first, 
      String last, Object... record) {
    
    for (int i = 0; i < this.patients.length; i++) {
      
      if (this.patients[i].checkName(first, last)) {
        
        this.patients[i].register(true);
        
        if (record != null) {
          this.patients[i].registerVisitRecord((Date) record[0], 
              (String) record[1], (double) record[2]);
        }
        
      }
    
    }
    
  }
  
  /** 
   * Registers a new clinical staff member to the clinic using
   * their given information. 
   * 
   * @param job         The staff member's job
   * @param firstName   The staff member's first name
   * @param lastName    The staff member's last name
   * @param education   The staff member's education level
   * @param npi         The staff member's NPI number
   */
  public void registerNewClinicalStaff(String job, String firstName, 
      String lastName, String education, String npi) {
    
    ClinicalStaff clinicalStaff = new ClinicalStaff(job, firstName, 
        lastName, education, npi);
    clinicalStaff.activate(true);
    
    // Make a new larger array of staff and add the new one
    int newLength = this.staff != null ? this.staff.length + 1 : 1;
    AbstractStaff[] tempStaff = new AbstractStaff[newLength];
    
    for (int i = 0; i < newLength - 1; i++) {
      tempStaff[i] = this.staff[i];
    }
    
    tempStaff[newLength - 1] = clinicalStaff;
    this.staff = tempStaff;
    
  }
  
  /** 
   * Send a patient home and deregister them with
   * approval from a clinical staff member. 
   * 
   * @param patient      The patient to send home
   */
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
  
  /** 
   * Send a patient home and deregister them with
   * approval from a clinical staff member using
   * a user's given name for a patient. 
   * 
   * @param first      The patient's first name
   * @param last       The patient's last name
   */
  public void sendPatientHome(String first, String last) {
    
    for (int i = 0; i < this.patients.length; i++) {
      
      if (this.patients[i].checkName(first, last)) {
        this.sendPatientHome(this.patients[i]);
      }
    
    }
    
  }
  
  public void deactivateClinicalStaff(ClinicalStaff staff) {
    staff.activate(false);
  }
  
  /** 
   * Assign a specified patient to a specified room in the clinic. 
   * 
   * @param patient     The patient to assign to a room
   * @param room        The room to assign the patient to
   */
  public void assignPatientToRoom(Patient patient, int room) {
    
    // Assign a patient to this room if there are no other
    // patients or it is a waiting room
    if (this.rooms[room - 1].checkOccupied() == false 
        || this.rooms[room - 1].getType().equals(RoomType.WAITING)) {
      patient.assignRoom(room);
      this.rooms[room - 1].assignPatient(patient);
    
    }
    
  }
  
  /** 
   * Assign a specified patient to a specified room in the clinic
   * using a user's given name for the patient. 
   * 
   * @param first     The first name of the patient
   * @param last      The last name of the patient
   * @param room      The room to assign the patient to
   */
  public void assignPatientToRoom(String first, String last, int room) {
    
    for (int i = 0; i < this.patients.length; i++) {
      
      if (this.patients[i].checkName(first, last)) {
        this.assignPatientToRoom(this.patients[i], room);
      }
    
    }
    
  }
  
  /** 
   * Assign a specified clinical staff member to a specified
   * patient in the clinic. 
   * 
   * @param staff      The clinical staff member to assign to the patient
   * @param patient    The patient who will have the clinician assigned to them
   */
  public void assignStaffToPatient(ClinicalStaff staff, Patient patient) {
    
    if (staff != null && patient != null && staff.isActive() && patient.isRegistered()) {
      staff.assignPatient(patient);
      patient.assignStaff(staff);
    }
    
  }
  
  /** 
   * Assign a specified clinical staff member to a specified
   * patient in the clinic using a user's given name for the patient
   * and for the clinician. 
   * 
   * @param patientFirst      The first name of the patient
   * @param patientLast       The last name of the patient
   * @param staffFirst        The first name of the clinical staff member
   * @param staffLast         The last name of the clinical staff member
   */
  public void assignStaffToPatient(String patientFirst, String patientLast, 
      String staffFirst, String staffLast) {
    
    Patient assignPatient = null;
    AbstractStaff assignStaff = null;
    
    for (int i = 0; i < this.patients.length; i++) {
      
      if (this.patients[i].checkName(patientFirst, patientLast)) {
        assignPatient = this.patients[i];
      }
    
    }
    
    for (int i = 0; i < this.staff.length; i++) {
      
      if (this.staff[i].checkName(staffFirst, staffLast) && (this.staff[i].getPrefix() == "Dr." || this.staff[i].getPrefix() == "Nurse")) {
        assignStaff = this.staff[i];
      }
    
    }
    
    this.assignStaffToPatient((ClinicalStaff) assignStaff, assignPatient);
    
  }
  
  /** 
   * Display the specified room's information. 
   * 
   * @param room   The room number of the specific room to display
   */
  public void displayRoom(int room) {
    
    String displayString;
    Room selectedRoom = this.rooms[room - 1];
    displayString = selectedRoom.display();
    displayString += "\n";
    System.out.print(displayString);
    
  }
  
  /** 
   * Display the specified patient's information. 
   * 
   * @param first   The first name of the patient to display
   * @param last    The last name of the patient to display
   */
  public void displayPatient(String first, String last) {
    
    String displayString = "No patient record matching name\n";
    
    for (int i = 0; i < this.patients.length; i++) {
      
      if (this.patients[i].checkName(first, last)) {
        displayString = this.patients[i].display();
        displayString += "\n";
        System.out.print(displayString);
      }
    
    }
    
  }
  
  /** 
   * Display the seating chart of the clinic which includes
   * the rooms, their patients, and those patients' assigned
   * clinicians. 
   */
  public void seatingChart() {
    
    String displayString = "";
    
    // Utilize the displayRoom function written above
    if (this.rooms != null) {
      for (int i = 0; i < this.rooms.length; i++) {
        displayString += rooms[i].display();
      }
    }
    
    displayString += "\n";
    System.out.print(displayString);
    
  }

}
