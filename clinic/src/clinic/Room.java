package clinic;

public class Room {

  private int lowerLeftX;
  private int lowerLeftY;
  private int upperRightX;
  private int upperRightY;
  private RoomType type;
  private String name;
  
  public Room(int lowerLeftX, int lowerLeftY, int upperRightX, 
      int upperRightY, String type, String name) {
    
    this.lowerLeftX = lowerLeftX;
    this.lowerLeftY = lowerLeftY;
    this.upperRightX = upperRightX;
    this.upperRightY = upperRightY;
    
    if (type == "exam") {
      this.type = RoomType.EXAM;
    } else if (type == "procedure") {
      this.type = RoomType.PROCEDURE;
    } else if (type == "waiting") {
      this.type = RoomType.WAITING;
    }
    
    this.name = name;
    
  }
  
}
