package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import clinic.ClinicalStaff;
import clinic.Patient;
import org.junit.Test;

/**
 * A JUnit test class for the Patient class.
 */
public class PatientTest {
  
  private Patient patient;
  
  @Before
  public void setup() {
    this.patient = new Patient(5, "Kelly", "George", "4/3/1971");
  }

  @Test
  public void testRegister() {
    
    this.patient.register(false);
    assertEquals(this.patient.isRegistered(), false);
    
  }
  
  @Test
  public void testAssignRoom() {
    
    this.patient.assignRoom(4);
    assertEquals(this.patient.display(), 
        "Patient name: Kelly George, date of birth: 4/3/1971, "
        + "room number: 4, registration status: true\n");
    
  }
  
  @Test
  public void testAssignStaff() {
    
    // Assign a clinician to the patient, which should display
    // the clinician's information under the patient's with display()
    ClinicalStaff staff = new ClinicalStaff("nurse", "Jake", 
        "James", "doctoral", "8394123485");
    this.patient.assignStaff(staff);
    assertEquals(this.patient.display(), "Patient name: Kelly George, date of birth: 4/3/1971, "
        + "room number: 5, registration status: true\n"
        + "Clinician name: Jake James, job: nurse, education level: doctoral, NPI: 8394123485\n");
    
  }

}
