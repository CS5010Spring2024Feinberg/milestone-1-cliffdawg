package clinic;

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
   * Display the patient information.
   * This serves as a patient's toString() method.
   * 
   * @return String   The string consisting of patient's information
   */
  public String display() {
    
    // First format the patient's information, then do the same for their clinicians
    String patientDisplay = String.format("Patient name: %s %s, date of birth: %s, "
        + "room number: %d, registration status: %b\n", 
        this.firstName, this.lastName, this.dob, this.roomNumber, this.registered);
    
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
