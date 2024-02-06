package clinic;

/**
 * This class represents a nonclinical staff member. The nonclinical 
 * staff member has the members of the AbstractStaff superclass
 * and a CPR level.
 */
public class NonclinicalStaff extends AbstractStaff {

  private String cpr;
  
  /**
   * Constructs a nonclinical staff member object and initializes
   * it to the given information.
   *
   * @param job         The staff member's job
   * @param firstName   The staff member's first Name
   * @param lastName    The staff member's last name
   * @param education   The staff member's education level
   * @param cpr         The staff member's CPR level
   */
  public NonclinicalStaff(String job, String firstName, String lastName,
      String education, String cpr) {
    
    // Will receive more information about nonclinical staff
    // in future milestones
    
    this.setStaffInfo(job, firstName, lastName, education);
    
    this.cpr = cpr;
    
  }
  
}
