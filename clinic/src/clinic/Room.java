package clinic;

public class Room {

  private int lowerLeftX;
  private int lowerLeftY;
  private int upperRightX;
  private int upperRightY;
  private int number;
  private RoomType type;
  private String name;
  private Patient[] patients;
  
  public Room(int lowerLeftX, int lowerLeftY, int upperRightX, 
      int upperRightY, int number, String type, String name) {
    
    this.lowerLeftX = lowerLeftX;
    this.lowerLeftY = lowerLeftY;
    this.upperRightX = upperRightX;
    this.upperRightY = upperRightY;
    this.number = number;
    
    if (type.equals("exam")) {
      this.type = RoomType.EXAM;
    } else if (type.equals("procedure")) {
      this.type = RoomType.PROCEDURE;
    } else if (type.equals("waiting")) {
      this.type = RoomType.WAITING;
    }
    
    this.name = name;
    
  }
  
  public void assignPatient(Patient patient) {
    
    // Make a new larger array of Patients and add the new one.
    // Consider a static method for adding patients in next milestone
    int newLength = this.patients != null ? this.patients.length + 1 : 1;
    Patient[] tempPatients = new Patient[newLength];
    
    for (int i = 0; i < newLength - 1; i++) {
      tempPatients[i] = this.patients[i];
    }
    
    tempPatients[newLength - 1] = patient;
    this.patients = tempPatients;
    
  }
  
  public boolean checkOccupied() {
    return this.patients != null;
  }
  
  public RoomType getType() {
    return this.type;
  }
  
  public String display() {
    
 // First format the room's information, then do the same for its patients
    String roomDisplay = String.format("Room name: %s, room number: %d, "
        + "room type: %s, room coordinates: (%d, %d) to (%d, %d) \n", 
        this.name, this.number, this.type.toString().toLowerCase(), 
        lowerLeftX, lowerLeftY, upperRightX, upperRightY);
    
    if (this.patients != null) {
      for (int i  = 0; i < this.patients.length; i++) {
        if (this.patients[i].isRegistered()) {
          roomDisplay += this.patients[i].display();
        }
      }
    }
    
    return roomDisplay;
    
  }
  
}
