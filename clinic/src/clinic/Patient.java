package clinic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents a patient. The patient has a
 * room number, a first name, a last name, a date of birth,
 * an array of assigned staff, and a registration status.
 */
public class Patient implements Person {

  private int roomNumber;
  private String firstName;
  private String lastName;
  private String dob;
  private Person[] assignedStaff;
  private VisitRecord[] visits;
  private boolean registered;
  
  /**
   * Constructs a patient object and initializes
   * it to the given information.
   *
   * @param roomNumber The patient's room number
   * @param firstName   The patient's first name
   * @param lastName    The patient's last name
   * @param dob         The patient's date of birth
   */
  public Patient(int roomNumber, String firstName, 
      String lastName, String dob) {
    
    if (firstName.isEmpty() || lastName.isEmpty() || dob.isEmpty()) {
      throw new IllegalArgumentException("Do not provide blank patient information.");
    }
    
    if (roomNumber < 0) {
      throw new IllegalArgumentException("Use valid room number for patient");
    }
    
    this.roomNumber = roomNumber;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dob = dob;
    this.registered = true;
    
  }
  
  /**
   * Make a separate register function for patients because
   * patients can be sent home while remaining in system.
   * 
   * @param status    The registration status to set for the patient
   */
  public void register(boolean status) {
    this.registered = status;
  }
  
  /**
   * Return patient's registrations status.
   * 
   * @return boolean   The registration status
   */
  public boolean isRegistered() {
    return this.registered;
  }
  
  /**
   * Assign this patient to the given room.
   * 
   * @param room      The room to assign the patient to
   */
  public void assignRoom(int room) {
    
    if (room < 0) {
      throw new IllegalArgumentException("Use valid room number "
          + "for patient assignment");
    }
    
    this.roomNumber = room;
    
  }
  
  /**
   * Record a clinical staff member as assigned to this patient.
   * 
   * @param staff     The clinical staff member to assign to this patient
   */
  public void assignStaff(ClinicalStaff staff) {
    
    if (staff == null) {
      throw new IllegalArgumentException("Do not attempt to "
          + "assign a null clinical staff to patient.");
    }
    
    // Make a new larger array of staff and add the new one
    int newLength = this.assignedStaff != null ? this.assignedStaff.length + 1 : 1;
    Person[] tempStaff = new Person[newLength];
    
    for (int i = 0; i < newLength - 1; i++) {
      tempStaff[i] = this.assignedStaff[i];
    }
    
    tempStaff[newLength - 1] = staff;
    this.assignedStaff = tempStaff;
    
  }
  
  /** 
   * Unassigns a clinical staff member from this patient. 
   * 
   * @param staff     The clinical staff member to unassign from this patient
   */
  public void unassignStaff(ClinicalStaff staff) {
    
    if (staff == null) {
      throw new IllegalArgumentException("Do not provide null "
          + "staff to unassign from patient.");
    }
    
    // Make a new smaller array of Staff and remove this one
    int newLength = this.assignedStaff != null ? this.assignedStaff.length - 1 : 0;
    Person[] tempStaff = new Person[newLength];
    
    int i = 0;
    
    for (int j = 0; j < this.assignedStaff.length; j++) {
      
      // Skip over the Staff to remove
      if (!this.assignedStaff[j].checkName(staff.firstName, staff.lastName)) {
        tempStaff[i] = this.assignedStaff[j];
        i++;
      }
      
    }
    
    this.assignedStaff = tempStaff;
    
  }
  
  /**
   * Display the patient information.
   * This serves as a patient's toString() method.
   * 
   * @return String   The string consisting of patient's information
   */
  public String display() {
    
    // First format the patient's information, then their visit record,
    // then do the same for their clinicians
    String patientDisplay = String.format("Patient name: %s %s, date of birth: %s, "
        + "room number: %d, registration status: %b\n", 
        this.firstName, this.lastName, this.dob, this.roomNumber, this.registered);
    
    if (this.visits != null && this.visits.length != 0) {
      
      // Add the visit record information
      String pattern = "MM/dd/yyyy HH:mm:ss";
      DateFormat df = new SimpleDateFormat(pattern);
      
      patientDisplay += String.format("Visit date: %s, chief complaint: %s, "
          + "body temperature: %.2f\n", 
          df.format(this.visits[this.visits.length - 1].getDate()), 
          this.visits[this.visits.length - 1].getComplaint(), 
          this.visits[this.visits.length - 1].getbodyTemp());
    
    }
 
    if (this.assignedStaff != null) {
      for (int i = 0; i < this.assignedStaff.length; i++) {
        
        if (this.assignedStaff[i].getClass().equals(ClinicalStaff.class) 
            && ((ClinicalStaff) assignedStaff[i]).isActive()) {
          
          patientDisplay += ((ClinicalStaff) assignedStaff[i]).display();
          
        }
        
      }
    }
    
    return patientDisplay;
    
  }
  
  /**
   * Display the simple patient information.
   * 
   * @return String   The string consisting of patient's simple information
   */
  public String simpleDisplay() {
    
    // First format the patient's information, then their visit record,
    // then do the same for their clinicians
    return String.format("Patient name: %s %s, date of birth: %s, "
        + "room number: %d, registration status: %b\n", 
        this.firstName, this.lastName, this.dob, this.roomNumber, this.registered);
    
  }
  
  /**
   * Check the given full name with this patient's.
   * 
   * @param first      The given first name
   * @param last       The given last name
   * 
   * @return boolean   Whether or not the given name matches
   */
  public boolean checkName(String first, String last) {
    
    if (first.isEmpty() || last.isEmpty()) {
      throw new IllegalArgumentException("Do not provide "
          + "blank check name information.");
    }
    
    return (this.firstName.equals(first) && this.lastName.equals(last));
    
  }
  
  /**
   * Return patient's first name.
   * 
   * @return String   The first name
   */
  public String getFirst() {
    
    return this.firstName;
    
  }
  
  /**
   * Return patient's last name.
   * 
   * @return String   The last name
   */
  public String getLast() {
    
    return this.lastName;
    
  }
  
  /**
   * Return patient's room.
   * 
   * @return int   The room number
   */
  public int getRoom() {
    
    return this.roomNumber;
    
  }
  
  /**
   * Add a new visit record for this patient.
   * 
   * @param date            The given date
   * @param complaint       The given complaint
   * @param temperature     The given temperature
   */
  public void registerVisitRecord(Date date, String complaint, double temperature) {
    
    if (date == null) {
      throw new IllegalArgumentException("Do not provide null date "
          + "in visit record.");
    }
    
    if (complaint.isEmpty()) {
      throw new IllegalArgumentException("Do not provide blank complaint "
          + "in visit record.");
    }
    
    if (temperature < 0.0) {
      throw new IllegalArgumentException("Do not provide invalid "
          + "temperature in visit record.");
    }
    
    VisitRecord visit = new VisitRecord(date, complaint, temperature);
    
    // Make a new larger array of visits and add the new one
    int newLength = this.visits != null ? this.visits.length + 1 : 1;
    VisitRecord[] tempVisits = new VisitRecord[newLength];
    
    for (int i = 0; i < newLength - 1; i++) {
      tempVisits[i] = this.visits[i];
    }
    
    tempVisits[newLength - 1] = visit;
    this.visits = tempVisits;
    
  }
  
  /**
   * Return if patient has been absent from clinic
   * for over a year.
   * 
   * @return boolean   The status of whether the patient is absent
   */
  public boolean absentForYear() {
    
    if (this.visits == null || this.visits.length == 0) {
      return false;
    }
    
    Date mostRecent = this.visits[this.visits.length - 1].getDate();
    Date yearBefore = new Date(System.currentTimeMillis() - 365L * 24 * 60 * 60 * 1000);
    return mostRecent.before(yearBefore);
    
  }
  
  /**
   * Return if patient has had two or more visits
   * in the last year.
   * 
   * @return boolean   The status of whether the patient has 2 or more visits in last year
   */
  public boolean twoInYear() {
    
    if (this.visits == null || this.visits.length < 2) {
      return false;
    }
    
    Date secondMostRecent = this.visits[this.visits.length - 2].getDate();
    Date yearBefore = new Date(System.currentTimeMillis() - 365L * 24 * 60 * 60 * 1000);
    return secondMostRecent.after(yearBefore);
    
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Patient)) {
      return false;
    }
    return this.roomNumber == ((Patient) o).roomNumber
        && this.firstName.equals(((Patient) o).firstName)
        && this.lastName.equals(((Patient) o).lastName)
        && this.dob.equals(((Patient) o).dob);
  }
  
  @Override
  public int hashCode() {
    // Objects that are equal need to return the same hash code
    return Long.hashCode((long) 
        (this.firstName + this.lastName + this.dob).hashCode());
  }
  
}
