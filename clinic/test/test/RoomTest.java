package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import clinic.Room;
import clinic.RoomType;
import clinic.Patient;
import org.junit.Test;

/**
 * A JUnit test class for the Room class.
 */
public class RoomTest {
  
  private Room room;
  
  @Before
  public void setup() {
    this.room = new Room(26, 5, 36, 10, 7, "exam", "Examination Room");
  }

  @Test
  public void testAssignPatientAndOccupied() {
    
    // Assign a patient to the room, which should display
    // the patient's information under the room's with display()
    Patient patient = new Patient(5, "Kelly", "George", "4/3/1971");
    this.room.assignPatient(patient);
    assertEquals(this.room.display(), "Room name: Examination Room, room number: 7, "
        + "room type: exam, room coordinates: (26, 5) to (36, 10) \n"
        + "Patient name: Kelly George, date of birth: 4/3/1971, "
        + "room number: 5, registration status: true\n");
    
    // The room should now return that it is occupied
    assertEquals(this.room.checkOccupied(), true);
    
  }
  
  @Test
  public void testGetType() {
    assertEquals(this.room.getType(), RoomType.EXAM);
  }

}
