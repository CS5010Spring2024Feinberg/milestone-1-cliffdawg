package clinic;

import java.util.Date;

/**
 * This class represents a visit record. The record has a
 * date and time, chief complaint, and body temperature in
 * Celsius.
 */
public class VisitRecord {
  
  private Date dateAndTime;
  private String complaint;
  private double bodyTemp;

  /**
   * Constructs a visit record object and initializes
   * it to the given information.
   *
   * @param date    The date and time of the registration
   * @param complaint   The patient's chief complaint
   * @param temperature    The patient's body temperature in Celsius
   */
  public VisitRecord(Date date, String complaint, double temperature) {
    
    this.dateAndTime = date;
    this.complaint = complaint;
    this.bodyTemp = temperature;
    
  }
  
}
