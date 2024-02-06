package clinic;

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
  
  public void assignRoom(int room) {
    this.roomNumber = room;
  }
  
  /**
   * Record a clinical staff member as assigned to this patient.
   * 
   * @param staff     The clinical staff member to assign to this patient
   */
  public void assignStaff(ClinicalStaff staff) {
    
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
  
}
