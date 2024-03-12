package clinic;

/**
 * This class represents a clinic room. The clinic room 
 * has lower left corner coordinates, upper right corner
 * coordinates, a room number, a room type, and a name.
 */
public class Room implements BuildingSpace {

  private int lowerLeftX;
  private int lowerLeftY;
  private int upperRightX;
  private int upperRightY;
  private int number;
  private RoomType type;
  private String name;
  private Patient[] patients;
  
  /**
   * Constructs a room object and initializes
   * it to the given information.
   *
   * @param lowerLeftX     The X coordinate of the room's lower left corner
   * @param lowerLeftY     The Y coordinate of the room's lower left corner
   * @param upperRightX    The X coordinate of the room's upper right corner
   * @param upperRightY    The Y coordinate of the room's upper right corner
   * @param number         The room number
   * @param type           The room type
   * @param name           The room name
   */
  public Room(int lowerLeftX, int lowerLeftY, int upperRightX, 
      int upperRightY, int number, String type, String name) {
    
    if (lowerLeftX < 0 || lowerLeftY < 0 || upperRightX < 0 || upperRightY < 0) {
      throw new IllegalArgumentException("Do not provide invalid room coordinates.");
    }
    
    if (number < 0) {
      throw new IllegalArgumentException("Do not provide invalid room number.");
    }
    
    if (type.isEmpty() || name.isEmpty()) {
      throw new IllegalArgumentException("Do not provide blank room information.");
    }
    
    this.lowerLeftX = lowerLeftX;
    this.lowerLeftY = lowerLeftY;
    this.upperRightX = upperRightX;
    this.upperRightY = upperRightY;
    this.number = number;
    
    if ("exam".equals(type)) {
      this.type = RoomType.EXAM;
    } else if ("procedure".equals(type)) {
      this.type = RoomType.PROCEDURE;
    } else if ("waiting".equals(type)) {
      this.type = RoomType.WAITING;
    }
    
    this.name = name;
    
  }
  
  /**
   * Assigns a patient to this room.
   * 
   * @param patient    The patient to be assigned
   */
  public void assignPatient(Patient patient) {
    
    if (patient == null) {
      throw new IllegalArgumentException("Do not attempt "
          + "to assign a null patient to room.");
    }
    
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
  
  /**
   * Check if this room is occupied.
   * 
   * @return boolean    Status of whether the room is occupied or not   
   */
  public boolean checkOccupied() {
    
    if (this.patients != null) {
      if (this.patients.length != 0) {
        return true;
      }
    }
     
    return false;
    
  }
  
  public RoomType getType() {
    return this.type;
  }
  
  public int getX() {
    return this.lowerLeftX;
  }
  
  public int getY() {
    return this.upperRightY;
  }
  
  /**
   * Display the room information.
   * This serves as a room's toString() method.
   * 
   * @return String     String of the room information
   */
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
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Room)) {
      return false;
    }
    return this.lowerLeftX == ((Room) o).lowerLeftX
        && this.lowerLeftY == ((Room) o).lowerLeftY
        && this.upperRightX == ((Room) o).upperRightX
        && this.upperRightY == ((Room) o).upperRightY
        && this.number == ((Room) o).number
        && this.type.equals(((Room) o).type)
        && this.name.equals(((Room) o).name);
  }
  
  @Override
  public int hashCode() {
    // Objects that are equal need to return the same hash code
    return Long.hashCode((long) 
        (this.name).hashCode());
  }
  
}
