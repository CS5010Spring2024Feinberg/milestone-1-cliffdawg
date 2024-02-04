package clinic;

public class Patient {

  private int roomNumber;
  private String firstName;
  private String lastName;
  private String DOB;
  Staff[] assignedStaff;
  private boolean registered;
  
  public Patient(int roomNumber, String firstName, 
      String lastName, String DOB) {
    
    this.roomNumber = roomNumber;
    this.firstName = firstName;
    this.lastName = lastName;
    this.DOB = DOB;
    this.registered = true;
    
  }
  
}
