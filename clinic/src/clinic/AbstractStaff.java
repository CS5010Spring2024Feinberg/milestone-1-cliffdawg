package clinic;

public abstract class AbstractStaff implements Staff {

  private String job;
  private String firstName;
  private String lastName;
  private EducationLevel education;
  
  public void setStaffInfo(String job, String firstName, 
      String lastName, String education) {
    
    this.job = job;
    this.firstName = firstName;
    this.lastName = lastName;
    
    if (education == "doctoral") {
      this.education = EducationLevel.DOCTORAL;
    } else if (education == "masters") {
      this.education = EducationLevel.MASTERS;
    } else if (education == "allied") {
      this.education = EducationLevel.ALLIED;
    }
    
  }

}
