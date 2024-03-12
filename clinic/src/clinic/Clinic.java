package clinic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
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
      
    if (fileName.isEmpty()) {
      throw new IllegalArgumentException("Provide an"
          + " input file.");
    }
    
    BufferedReader reader;
      
    try {
       
      char[] array = new char[100];
        
      // Creates a reader to read text file line by line
      reader = new BufferedReader(new FileReader(fileName));
        
      String line = reader.readLine();
        
      String clinicName = line;
        
      line = reader.readLine();
        
      int roomCount = Integer.parseInt(line);
      
      // Handle invalid clinic description
      if (roomCount < 0) {
        throw new IllegalStateException("Invalid room count.");
      }
      
      Room[] tempRooms = new Room[roomCount];
        
      // Create each room and add it to the clinic
      for (int i = 0; i < roomCount; i++) {
        
        line = reader.readLine();
        String[] data = line.split("\\s+");
        // Join the split strings of name back together
        String[] toJoin = Arrays.copyOfRange(data, 5, data.length);
        String name = String.join(" ", toJoin);
        
        // Handle invalid room description
        if (Integer.parseInt(data[0]) < 0 || Integer.parseInt(data[2]) < 0
            || Integer.parseInt(data[2]) < 0 || Integer.parseInt(data[3]) < 0) {
          throw new IllegalStateException("Invalid room coordinates.");
        }
        
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
        
        // Handle invalid staff description
        if (data.length < 5) {
          throw new IllegalStateException("Missing staff information.");
        }
        
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
        
        // Handle invalid patient description
        if (data.length < 4) {
          throw new IllegalStateException("Missing patient information.");
        }
        
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
   * @param record      The information of a visit record
   */
  public void registerNewPatient(String firstName, 
      String lastName, String dob, Object... record) {
    
    if (firstName.isEmpty() || lastName.isEmpty() || dob.isEmpty()) {
      throw new IllegalArgumentException("Do not provide blank new patient information.");
    }
    
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
   * @param record      The information of a visit record
   */
  public void registerExistingPatient(String first, 
      String last, Object... record) {
    
    if (first.isEmpty() || last.isEmpty()) {
      throw new IllegalArgumentException("Do not provide blank existing patient information.");
    }
    
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
    
    if (job.isEmpty() || firstName.isEmpty() || lastName.isEmpty() 
        || education.isEmpty() || npi.isEmpty()) {
      throw new IllegalArgumentException("Do not provide blank new clinical staff information.");
    }
    
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
    
    if (patient == null) {
      throw new IllegalArgumentException("Do not attempt to send a null patient home.");
    }
    
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
    
    if (first.isEmpty() || last.isEmpty()) {
      throw new IllegalArgumentException("Do not send home blank patient information.");
    }
    
    for (int i = 0; i < this.patients.length; i++) {
      
      if (this.patients[i].checkName(first, last)) {
        this.sendPatientHome(this.patients[i]);
      }
    
    }
    
  }
  
  /** 
   * Deactivate a clinical staff. 
   * 
   * @param staff     The staff to deactivate
   */
  public void deactivateClinicalStaff(ClinicalStaff staff) {
    
    if (staff == null) {
      throw new IllegalArgumentException("Do not attempt to deactivate a null staff.");
    }
    
    for (int i = 0; i < this.staff.length; i++) {
      
      if (this.staff[i].checkName(staff.firstName, staff.lastName) 
          && ("Dr.".equals(this.staff[i].getPrefix()) 
              || "Nurse".equals(this.staff[i].getPrefix()))) {
        ((ClinicalStaff) this.staff[i]).activate(false);;
      }
    
    }
    
    staff.activate(false);
    
  }
  
  /** 
   * Deactivate a clinical staff using a user's given name for the clinician. 
   * 
   * @param first     The staff to deactivate's first name
   * @param last      The staff to deactivate's last name
   */
  public void deactivateClinicalStaff(String first, String last) {
    
    if (first.isEmpty() || last.isEmpty()) {
      throw new IllegalArgumentException("Do not deactivate blank clinician information.");
    }
    
    for (int i = 0; i < this.staff.length; i++) {
      
      if (this.staff[i].checkName(first, last) 
          && ("Dr.".equals(this.staff[i].getPrefix()) 
              || "Nurse".equals(this.staff[i].getPrefix()))) {
        ((ClinicalStaff) this.staff[i]).activate(false);;
      }
    
    }
    
  }
  
  /** 
   * Assign a specified patient to a specified room in the clinic. 
   * 
   * @param patient     The patient to assign to a room
   * @param room        The room to assign the patient to
   */
  public void assignPatientToRoom(Patient patient, int room) {
    
    if (patient == null) {
      throw new IllegalArgumentException("Do not attempt to assign a null patient to room.");
    }
    
    if (room < 0) {
      throw new IllegalArgumentException("Use valid room number "
          + "for assigning patient.");
    }
    
    // Assign a patient to this room if there are no other
    // patients or it is a waiting room
    if (!this.rooms[room - 1].checkOccupied()
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
    
    if (first.isEmpty() || last.isEmpty()) {
      throw new IllegalArgumentException("Do not assign "
          + "blank patient information to room.");
    }
    
    if (room < 0) {
      throw new IllegalArgumentException("Use valid room number "
          + "for assigning patient.");
    }
    
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
    
    if (staff == null || patient == null) {
      throw new IllegalArgumentException("Do not assign "
          + "null staff or null patient together.");
    }
    
    if (staff != null && patient != null && staff.isActive() && patient.isRegistered()) {
      staff.assignPatient(patient);
      patient.assignStaff(staff);
    }
    
    for (int i = 0; i < this.staff.length; i++) {
      
      if (this.staff[i].checkName(staff.firstName, staff.lastName)) {
        
        for (int j = 0; j < this.patients.length; j++) {
          
          if (this.patients[j].checkName(patient.getFirst(), patient.getLast())) {
            
            if (!((ClinicalStaff) this.staff[i]).hasPatient(
                patient.getFirst(), patient.getLast())) {
              
              ((ClinicalStaff) this.staff[i]).assignPatient(this.patients[j]);
              this.patients[j].assignStaff((ClinicalStaff) this.staff[i]);
              
            }
            
          }
        
        }
      
      }
    
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
    
    if (patientFirst.isEmpty() || patientLast.isEmpty()) {
      throw new IllegalArgumentException("Do not provide "
          + "blank patient information to be assigned to.");
    }
    
    if (staffFirst.isEmpty() || staffLast.isEmpty()) {
      throw new IllegalArgumentException("Do not provide "
          + "blank staff information to assign to patient.");
    }
    
    Patient assignPatient = null;
    AbstractStaff assignStaff = null;
    
    for (int i = 0; i < this.patients.length; i++) {
      
      if (this.patients[i].checkName(patientFirst, patientLast)) {
        assignPatient = this.patients[i];
      }
    
    }
    
    for (int i = 0; i < this.staff.length; i++) {
      
      if (this.staff[i].checkName(staffFirst, staffLast) 
          && ("Dr.".equals(this.staff[i].getPrefix()) 
              || "Nurse".equals(this.staff[i].getPrefix()))) {
        assignStaff = this.staff[i];
      }
    
    }
    
    this.assignStaffToPatient((ClinicalStaff) assignStaff, assignPatient);
    
  }
  
  /** 
   * Unassign a specified clinical staff member from a specified
   * patient in the clinic. 
   * 
   * @param staff      The clinical staff member to unassign from the patient
   * @param patient    The patient who will have the clinician unassigned from them
   */
  public void unassignStaffFromPatient(ClinicalStaff staff, Patient patient) {
    
    if (staff == null || patient == null) {
      throw new IllegalArgumentException("Do not unassign "
          + "null staff or null patient.");
    }
    
    staff.unassignPatient(patient);
    patient.unassignStaff(staff);
    
    for (int i = 0; i < this.staff.length; i++) {
      
      if (this.staff[i].checkName(staff.firstName, staff.lastName)) {
        
        for (int j = 0; j < this.patients.length; j++) {
          
          if (this.patients[j].checkName(patient.getFirst(), patient.getLast())) {
            
            if (!((ClinicalStaff) this.staff[i]).hasPatientNot(
                patient.getFirst(), patient.getLast())) {
              
              ((ClinicalStaff) this.staff[i]).unassignPatient(this.patients[j]);
              this.patients[j].unassignStaff((ClinicalStaff) this.staff[i]);
              
            }
            
          }
        
        }
      
      }
    
    }
    
  }
  
  /** 
   * Unassign a specified clinical staff member from a specified
   * patient in the clinic using a user's given name for the patient
   * and for the clinician. 
   * 
   * @param patientFirst      The first name of the patient
   * @param patientLast       The last name of the patient
   * @param staffFirst        The first name of the clinical staff member
   * @param staffLast         The last name of the clinical staff member
   */
  public void unassignStaffFromPatient(String patientFirst, String patientLast, 
      String staffFirst, String staffLast) {
    
    if (patientFirst.isEmpty() || patientLast.isEmpty()) {
      throw new IllegalArgumentException("Do not provide "
          + "blank patient information to be unassigned from.");
    }
    
    if (staffFirst.isEmpty() || staffLast.isEmpty()) {
      throw new IllegalArgumentException("Do not provide "
          + "blank staff information to be unassigned from patient.");
    }
    
    Patient unassignPatient = null;
    AbstractStaff unassignStaff = null;
    
    for (int i = 0; i < this.patients.length; i++) {
      
      if (this.patients[i].checkName(patientFirst, patientLast)) {
        unassignPatient = this.patients[i];
      }
    
    }
    
    for (int i = 0; i < this.staff.length; i++) {
      
      if (this.staff[i].checkName(staffFirst, staffLast) 
          && ("Dr.".equals(this.staff[i].getPrefix()) 
              || "Nurse".equals(this.staff[i].getPrefix()))) {
        unassignStaff = this.staff[i];
      }
    
    }
    
    this.unassignStaffFromPatient((ClinicalStaff) unassignStaff, unassignPatient);
    
  }
  
  /** 
   * Display the specified room's information. 
   * This serves as a room's toString() method.
   * 
   * @param room   The room number of the specific room to display
   */
  public void displayRoom(int room) {
    
    if (room < 0) {
      throw new IllegalArgumentException("Use valid room number for displaying.");
    }
    
    String displayString;
    Room selectedRoom = this.rooms[room - 1];
    displayString = selectedRoom.display();
    displayString += "\n";
    System.out.print(displayString);
    
  }
  
  /** 
   * Display the specified patient's information. 
   * This serves as a patient's toString() method.
   * 
   * @param first   The first name of the patient to display
   * @param last    The last name of the patient to display
   */
  public void displayPatient(String first, String last) {
    
    if (first.isEmpty() || last.isEmpty()) {
      throw new IllegalArgumentException("Do not provide "
          + "blank patient information to display patient.");
    }
    
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
   * clinicians. This serves as a clinic's toString() method.
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
  
  /** 
   * List the clinical staff members who have an assigned patient
   * that has not been sent home. For each clinical staff member, 
   * list the currently assigned patients.
   */
  public void listStaffWithPatients() {
    
    String displayPatients = "";
    
    for (int i = 0; i < this.staff.length; i++) {
      if ("Dr.".equals(this.staff[i].getPrefix()) || "Nurse".equals(this.staff[i].getPrefix())) {
        if (((ClinicalStaff) this.staff[i]).hasPatients()) {
          displayPatients += ((ClinicalStaff) this.staff[i]).display();
          displayPatients += ((ClinicalStaff) this.staff[i]).displayPatients();
        }
      }
    
    }
    
    displayPatients += "\n";
    System.out.print(displayPatients);
    
  }
  
  /** 
   * List the patients who have been in the clinic before 
   * but have not been in the clinic for more than a year.
   */
  public void listPatientsAbsentYear() {
    
    String displayPatients = "";
    
    for (int i = 0; i < this.patients.length; i++) {
      
      if (this.patients[i].absentForYear()) {
        displayPatients += this.patients[i].display();
      }
    
    }
    
    displayPatients += "\n";
    System.out.print(displayPatients);
    
  }
  
  
  /*
   * Create a patient that has been absent for two years.
   * */
  public void createAbsentTwoYears() {
    
    Date yearsBefore = new Date(System.currentTimeMillis() - 700L*24*60*60*1000);
    
    Patient patient = new Patient(1, "Walter", 
        "Bond", "1/19/1991");
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
    
    patient.registerVisitRecord(yearsBefore, 
        "Flu", 37.1);
    
  }
  
  /** 
   * List the patients who have who have had two or 
   * more visits in the last year.
   */
  public void listPatientsTwoInYear() {
    
    String displayPatients = "";
    
    for (int i = 0; i < this.patients.length; i++) {
      
      if (this.patients[i].twoInYear()) {
        displayPatients += this.patients[i].display();
      }
    
    }
    
    displayPatients += "\n";
    System.out.print(displayPatients);
    
  }
  
  /** 
   * List all the clinical staff members, and for each clinician, 
   * list the number of patients that they have ever been assigned to.
   */
  public void listStaffPatientNumber() {
    
    String displayStaff = "";
    
    for (int i = 0; i < this.staff.length; i++) {
      if ("Dr.".equals(this.staff[i].getPrefix()) || "Nurse".equals(this.staff[i].getPrefix())) {
        displayStaff += ((ClinicalStaff) this.staff[i]).display();
        displayStaff += String.format("Total number of patients this clinician was ever assigned: %d\n", ((ClinicalStaff) this.staff[i]).getMaxPatients());
      }
    }
    
    displayStaff += "\n";
    System.out.print(displayStaff);
    
  }
  
  /** 
   * Create a map of the clinic with the names of
   * the patients in each room (or "empty"). 
   */
  public void createMap() {
    
    JFrame frame = new JFrame();
    frame.getContentPane().add(new ClinicMap(this.rooms, this.patients));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 1000);
    frame.setVisible(true);
    
  }

}
