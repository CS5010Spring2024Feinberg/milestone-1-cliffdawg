package clinic;

public class ClinicalStaff extends AbstractStaff {

  private String NPI;
  private Patient[] assignedPatients;
  private boolean activated;
  
  public ClinicalStaff(String job, String firstName, String lastName,
      String education, String NPI) {
    
    this.setStaffInfo(job, firstName, lastName, education);
    
    this.NPI = NPI;
    this.activated = true;
    
  }
  
  // Make a separate activate function for clinical staff because
  // clinical staff can be deactivated while remaining in system.
  // Move a common registration function to the Person interface
  // in the future if NonclinicalStaff also ends up needing one
  public void activate(boolean status) {
    this.activated = status;
  }
  
  public boolean isActive() {
    return this.activated;
  }
  
  public void approvePatientHome(Patient patient) {
    patient.register(false);
  }
  
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
  
  public String display() {
    
    // Format the clinician's information
    String clinicianDisplay = String.format("Clinician name: %s %s, job: %s, "
        + "education level: %s, NPI: %s\n", 
        this.firstName, this.lastName, this.job, this.education.toString().toLowerCase(), this.NPI);
  
    return clinicianDisplay;
    
  }
  
}
