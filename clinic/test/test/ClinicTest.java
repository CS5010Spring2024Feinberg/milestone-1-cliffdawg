package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import clinic.Clinic;
import org.junit.Test;

/**
 * A JUnit test class for the Clinic class.
 */
public class ClinicTest {

  @Test(expected = IllegalStateException.class)
  public void testClinicDescription() {
    
    Clinic clinic = new Clinic();
    clinic.readFile("res/testClinicDescription.txt");
      
  }
  
  @Test(expected = IllegalStateException.class)
  public void testRoomDescription() {
    
    Clinic clinic = new Clinic();
    clinic.readFile("res/testRoomDescription.txt");
      
  }
  
  @Test(expected = IllegalStateException.class)
  public void testStaffDescription() {
    
    Clinic clinic = new Clinic();
    clinic.readFile("res/testStaffDescription.txt");
      
  }
  
  @Test(expected = IllegalStateException.class)
  public void testPatientDescription() {
    
    Clinic clinic = new Clinic();
    clinic.readFile("res/testClinicDescription.txt");
      
  }

}
