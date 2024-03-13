package clinic;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a clinical staff member. The clinical 
 * staff member has a NPI number, an array of assigned patients,
 * and an active status.
 */
public class ClinicalStaff extends AbstractStaff {

  private String npi;
  private Patient[] assignedPatients;
  private boolean activated;
  private Set<String> allTimePatients;
  
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
    this.allTimePatients = new HashSet<String>();
    
    if ("physician".equals(job)) {
      this.prefix = "Dr."; 
    }
    
    if ("nurse".equals(job)) {
      this.prefix = "Nurse";
    }
    
  }
  
  /** 
   * Make a separate activate function for clinical staff because
   * clinical staff can be deactivated while remaining in system.
   * Move a common registration function to the Person interface
   * in the future if NonclinicalStaff also ends up needing one.  
   * 
   * @param status   The status to set activation attribute
   */
  public void activate(boolean status) {
    this.activated = status;
  }
  
  /** 
   * Check whether this clinician is active.
   * 
   * @return boolean   Whether this clinician is active
   */
  public boolean isActive() {
    return this.activated;
  }
  
  /** 
   * Get the count of all the patients clinician has ever had.
   * 
   * @return int   The count of all-time patients
   */
  public int getMaxPatients() {
    return this.allTimePatients.size();
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
    
    this.allTimePatients.add(patient.getFirst() + patient.getLast());
    
  }
  
  /** 
   * Unassigns a patient regarding this clinical staff member. 
   * 
   * @param patient   The patient to unassign regarding this clinical staff member
   */
  public void unassignPatient(Patient patient) {
    
    if (patient == null) {
      throw new IllegalArgumentException("Do not provide null "
          + "patient to unassign from clinical staff.");
    }
    
    // Make a new smaller array of Patients and remove this one
    int newLength = this.assignedPatients != null ? this.assignedPatients.length - 1 : 0;
    Patient[] tempPatients = new Patient[newLength];
    
    int i = 0;
    
    for (int j = 0; j < this.assignedPatients.length; j++) {
      
      // Skip over the Patient to remove
      if (!this.assignedPatients[j].checkName(patient.getFirst(), patient.getLast())) {
        tempPatients[i] = this.assignedPatients[j];
        i++;
      }
      
    }
    
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
  
  /** 
   * Display the clinical staff member's patients. 
   * 
   * @return String   The string consisting of the clinical staff member's patients' information
   */
  public String displayPatients() {
    
    String patientsDisplay = "";
    
    if (this.hasPatients()) {
      
      for (int i = 0; i < this.assignedPatients.length; i++) {
        patientsDisplay += this.assignedPatients[i].simpleDisplay();
      }
    }
    
    return patientsDisplay;
    
  }
  
  /** 
   * Check if clinical staff member has patients. 
   * 
   * @return boolean       Whether the clinical staff member has assigned patients
   */
  public boolean hasPatients() {
    if (this.assignedPatients != null && this.assignedPatients.length != 0) {
      return true;
    } else {
      return false;
    }
  }
  
  /** 
   * Check if clinical staff member has a specific patient. 
   * 
   * @param first     The patient's first name
   * @param last      The patient's last name
   * 
   * @return boolean       Whether the clinical staff member has a specific patient
   */
  public boolean hasPatient(String first, String last) {
    
    if (first.isEmpty() || last.isEmpty()) {
      throw new IllegalArgumentException("Do not provide blank checking patient information.");
    }
    
    if (this.assignedPatients == null) {
      return false;
    }
    
    for (int i = 0; i < this.assignedPatients.length; i++) {
      
      if (first.equals(this.assignedPatients[i].getFirst()) 
          && last.equals(this.assignedPatients[i].getLast())) {
        return true;
      }
    
    }
    
    return false;
    
  }
  
  /** 
   * Check if clinical staff member doesn't have a specific patient. 
   * 
   * @param first     The patient's first name
   * @param last      The patient's last name
   * 
   * @return boolean       Whether the clinical staff member doesn't have a specific patient
   */
  public boolean hasPatientNot(String first, String last) {
    
    if (first.isEmpty() || last.isEmpty()) {
      throw new IllegalArgumentException("Do not provide blank checking no patient information.");
    }
    
    if (this.assignedPatients == null) {
      return true;
    }
    
    for (int i = 0; i < this.assignedPatients.length; i++) {
      
      if (first.equals(this.assignedPatients[i].getFirst()) 
          && last.equals(this.assignedPatients[i].getLast())) {
        return false;
      }
    
    }
    
    return true;
    
  }
  
}
