package clinic;

public class Patient implements Person {

  private int roomNumber;
  private String firstName;
  private String lastName;
  private String DOB;
  private Person[] assignedStaff;
  private boolean registered;
  
  public Patient(int roomNumber, String firstName, 
      String lastName, String DOB) {
    
    this.roomNumber = roomNumber;
    this.firstName = firstName;
    this.lastName = lastName;
    this.DOB = DOB;
    this.registered = true;
    
  }
  
  // Make a separate register function for patients because
  // patients can be sent home while remaining in system
  public void register(boolean status) {
    this.registered = status;
  }
  
  public boolean isRegistered() {
    return this.registered;
  }
  
  public void assignRoom(int room) {
    this.roomNumber = room;
  }
  
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
  
  public String display() {
    
    // First format the patient's information, then do the same for their clinicians
    String patientDisplay = String.format("Patient name: %s %s, date of birth: %s, "
        + "room number: %d, registration status: %b\n", 
        this.firstName, this.lastName, this.DOB, this.roomNumber, this.registered);
    
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
