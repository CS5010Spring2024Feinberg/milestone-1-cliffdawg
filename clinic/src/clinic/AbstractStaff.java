package clinic;

/**
 * This class represents a staff member. The staff member
 * has members containing their job, first name, last name,
 * and education level.
 */
public abstract class AbstractStaff implements Person {

  protected String job;
  protected String firstName;
  protected String lastName;
  protected EducationLevel education;
  
  /**
  * Sets the information for the staff member.
  * 
  * @param job         The staff member's job
  * @param firstName   The staff member's first Name
  * @param lastName    The staff member's last name
  * @param education   The staff member's education level
  */
  public void setStaffInfo(String job, String firstName, 
      String lastName, String education) {
    
    this.job = job;
    this.firstName = firstName;
    this.lastName = lastName;
    
    if ("doctoral".equals(education)) {
      this.education = EducationLevel.DOCTORAL;
    } else if ("masters".equals(education)) {
      this.education = EducationLevel.MASTERS;
    } else if ("allied".equals(education)) {
      this.education = EducationLevel.ALLIED;
    }
    
  }

}
