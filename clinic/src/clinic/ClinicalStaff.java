package clinic;

/**
 * This class represents a clinical staff member. The clinical 
 * staff member has a NPI number, an array of assigned patients,
 * and an active status.
 */
public class ClinicalStaff extends AbstractStaff {

  private String npi;
  private Patient[] assignedPatients;
  private boolean activated;
  
  /**
   * Constructs a clinical staff member object and initializes
   * it to the given information.
   *
   * @param job         The staff member's job
   * @param firstName   The staff member's first name
   * @param lastName    The staff member's last name
   * @param education   The staff member's education level
   * @param npi         The staff member's NPI number
   */
  public ClinicalStaff(String job, String firstName, String lastName,
      String education, String npi) {
    
    this.setStaffInfo(job, firstName, lastName, education);
    
    this.npi = npi;
    this.activated = true;
    
    if (job == "physician") {
      this.prefix = "Dr."; 
    }
    
    if (job == "nurse") {
      this.prefix = "Nurse";
    }
    
  }
  
  /* 
   * Make a separate activate function for clinical staff because
   * clinical staff can be deactivated while remaining in system.
   * Move a common registration function to the Person interface
   * in the future if NonclinicalStaff also ends up needing one 
   */
  public void activate(boolean status) {
    this.activated = status;
  }
  
  public boolean isActive() {
    return this.activated;
  }
  
  public void approvePatientHome(Patient patient) {
    patient.register(false);
  }
  
  /** 
   * Assigns a patient to this clinical staff member. 
   * 
   * @param patient   The patient to assign to this clinical staff member
   */
  public void assignPatient(Patient patient) {
    
    // Make a new larger array of Patients and add the new one
    int newLength = this.assignedPatients != null ? this.assignedPatients.length + 1 : 1;
    Patient[] tempPatients = new Patient[newLength];
    
    for (int i = 0; i < newLength - 1; i++) {
      tempPatients[i] = this.assignedPatients[i];
    }
    
    tempPatients[newLength - 1] = patient;
    this.assignedPatients = tempPatients;
    
  }
  
  /** 
   * Display the clinical staff member information. 
   * 
   * @return String      The string consisting of the clinical staff member's information
   */
  public String display() {
    
    // Format the clinician's information
    String clinicianDisplay = String.format("Clinician name: %s %s, job: %s, "
        + "education level: %s, NPI: %s\n", 
        this.firstName, this.lastName, this.job, this.education.toString().toLowerCase(), this.npi);
  
    return clinicianDisplay;
    
  }
  
}
