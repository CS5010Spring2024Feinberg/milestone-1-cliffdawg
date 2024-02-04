package clinic;

public class NonclinicalStaff extends AbstractStaff {

  private String CPR;
  
  public NonclinicalStaff(String job, String firstName, String lastName,
      String education, String CPR) {
    
    this.setStaffInfo(job, firstName, lastName, education);
    
    this.CPR = CPR;
    
  }
  
}
