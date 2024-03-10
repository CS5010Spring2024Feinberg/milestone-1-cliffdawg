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
    
    if (job.isEmpty() || firstName.isEmpty() || lastName.isEmpty() 
        || education.isEmpty() || npi.isEmpty()) {
      throw new IllegalArgumentException("Do not provide blank "
          + "clinical staff information.");
    }
    
    this.setStaffInfo(job, firstName, lastName, education);
    
    this.npi = npi;
    this.activated = true;
    
    if ("physician".equals(job)) {
      this.prefix = "Dr."; 
    }
    
    if ("nurse".equals(job)) {
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
  
  /** 
   * Approve a patient to send home. 
   * 
   * @param patient   The patient to send home
   */
  public void approvePatientHome(Patient patient) {
    
    if (patient == null) {
      throw new IllegalArgumentException("Do not attempt to "
          + "approve a null patient.");
    }
    
    patient.register(false);
  }
  
  /** 
   * Assigns a patient to this clinical staff member. 
   * 
   * @param patient   The patient to assign to this clinical staff member
   */
  public void assignPatient(Patient patient) {
    
    if (patient == null) {
      throw new IllegalArgumentException("Do not provide null "
          + "patient to assign to clinical staff.");
    }
    
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
   * This serves as a clinical staff's toString() method.
   * 
   * @return String      The string consisting of the clinical staff member's information
   */
  public String display() {
    
    // Format the clinician's information
    String clinicianDisplay = String.format("Clinician name: %s %s %s, job: %s, "
        + "education level: %s, NPI: %s\n", 
        this.getPrefix(), this.firstName, this.lastName, 
        this.job, this.education.toString().toLowerCase(), this.npi);
  
    return clinicianDisplay;
    
  }
  
}
