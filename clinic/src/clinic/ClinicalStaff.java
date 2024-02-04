package clinic;

public class ClinicalStaff extends AbstractStaff {

  private String NPI;
  private Patient[] assignedPatients;
  private boolean registered;
  
  public ClinicalStaff(String job, String firstName, String lastName,
      String education, String NPI) {
    
    this.setStaffInfo(job, firstName, lastName, education);
    
    this.NPI = NPI;
    
    this.registered = true;
    
  }
  
}
