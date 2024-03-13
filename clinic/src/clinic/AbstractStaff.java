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
  protected String prefix;
  
  /**
  * Sets the information for the staff member.
  * 
  * @param job         The staff member's job
  * @param firstName   The staff member's first name
  * @param lastName    The staff member's last name
  * @param education   The staff member's education level
  */
  public void setStaffInfo(String job, String firstName, 
      String lastName, String education) {
    
    if (job.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || education.isEmpty()) {
      throw new IllegalArgumentException("Do not provide blank staff information.");
    }
    
    this.job = job;
    this.firstName = firstName;
    this.lastName = lastName;
    this.prefix = "";
    
    if ("doctoral".equals(education)) {
      this.education = EducationLevel.DOCTORAL;
    } else if ("masters".equals(education)) {
      this.education = EducationLevel.MASTERS;
    } else if ("allied".equals(education)) {
      this.education = EducationLevel.ALLIED;
    }
    
  }
  
  /**
   * Check the given full name with this staff member's.
   * 
   * @param first      The given first name
   * @param last       The given last name
   * 
   * @return boolean   Whether or not the given name matches
   */
  public boolean checkName(String first, String last) {
    
    if (first.isEmpty() || last.isEmpty()) {
      throw new IllegalArgumentException("Do not provide blank checking name information.");
    }
    
    return (this.firstName.equals(first) && this.lastName.equals(last));
    
  }
  
  /**
   * Get the staff's prefix.
   * 
   * @return String    The prefix to return   
   */
  public String getPrefix() {
    return this.prefix;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbstractStaff)) {
      return false;
    }
    return this.job.equals(((AbstractStaff) o).job)
        && this.firstName.equals(((AbstractStaff) o).firstName)
        && this.lastName.equals(((AbstractStaff) o).lastName)
        && this.education.equals(((AbstractStaff) o).education)
        && this.prefix.equals(((AbstractStaff) o).prefix);
  }
  
  @Override
  public int hashCode() {
    // Objects that are equal need to return the same hash code
    return Long.hashCode((long) 
        (this.job + this.firstName + this.lastName + this.prefix).hashCode());
  }

}
