package clinic;

/**
 * This interface represents a person. It will contain
 * all the methods required for implementations of a person
 * to implement. 
 */
public interface Person {

  /**
   * Check the given full name with this person's.
   * 
   * @param first      The given first name
   * @param last       The given last name
   * 
   * @return boolean   Whether or not the given name matches
   */
  public boolean checkName(String first, String last);
  
}
