package clinic;

public abstract class AbstractStaff implements Person {

  protected String job;
  protected String firstName;
  protected String lastName;
  protected EducationLevel education;
  
  public void setStaffInfo(String job, String firstName, 
      String lastName, String education) {
    
    this.job = job;
    this.firstName = firstName;
    this.lastName = lastName;
    
    if (education.equals("doctoral")) {
      this.education = EducationLevel.DOCTORAL;
    } else if (education.equals("masters")) {
      this.education = EducationLevel.MASTERS;
    } else if (education.equals("allied")) {
      this.education = EducationLevel.ALLIED;
    }
    
  }

}
