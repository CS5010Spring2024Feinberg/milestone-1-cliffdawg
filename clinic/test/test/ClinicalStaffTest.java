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
 * A JUnit test class for the ClinicalStaff class.
 */
public class ClinicalStaffTest {

  private ClinicalStaff staff;
  
  @Before
  public void setup() {
    this.staff = new ClinicalStaff("nurse", "Jake", 
        "James", "doctoral", "8394123485");
  }
  
  @Test
  public void testActivate() {
    
    this.staff.activate(false);
    assertEquals(this.staff.isActive(), false);
    
  }
  
  @Test
  public void testApprovePatientHome() {
    
    // Use this clinical staff member to send a patient home
    // which deregisters them
    Patient patient = new Patient(5, "Kelly", "George", "4/3/1971");
    assertEquals(patient.isRegistered(), true);
    this.staff.approvePatientHome(patient);
    assertEquals(patient.isRegistered(), false);
    
  }

}
