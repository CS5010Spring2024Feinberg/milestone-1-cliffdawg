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
    
    if (date == null) {
      throw new IllegalArgumentException("Do not provide null date "
          + "in visit record.");
    }
    
    if (complaint.isEmpty()) {
      throw new IllegalArgumentException("Do not provide blank complaint "
          + "in visit record.");
    }
    
    if (temperature < 0.0) {
      throw new IllegalArgumentException("Do not provide invalid "
          + "temperature in visit record.");
    }
    
    this.dateAndTime = date;
    this.complaint = complaint;
    this.bodyTemp = temperature;
    
  }
  
  public Date getDate() {
    return this.dateAndTime;
  }
  
  public String getComplaint() {
    return this.complaint;
  }
  
  public double getbodyTemp() {
    return this.bodyTemp;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof VisitRecord)) {
      return false;
    }
    return this.dateAndTime.equals(((VisitRecord) o).dateAndTime)
        && this.complaint.equals(((VisitRecord) o).complaint)
        && this.bodyTemp == ((VisitRecord) o).bodyTemp;
  }
  
  @Override
  public int hashCode() {
    // Objects that are equal need to return the same hash code
    return Long.hashCode((long) 
        (this.complaint).hashCode());
  }
  
}
