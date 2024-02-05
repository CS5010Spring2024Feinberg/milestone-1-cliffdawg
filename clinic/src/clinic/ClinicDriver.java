package clinic;

public class ClinicDriver {

  public static void main(String[] args) {
    
    String fileName = args[0];
    
    Clinic clinic = new Clinic();
    
    // Test readFile function, will display "File not found!" on error
    clinic.readFile(fileName);
    
    // Test displayRoom function
    clinic.displayRoom(1);
    
    // Test seating chart function
    clinic.seatingChart();
    
    // Test registerNewPatient and see if they
    // appear in the waiting room
    clinic.registerNewPatient("Joe", "Schmoe", "5/6/1995");
    clinic.displayRoom(1);
    
    // Test assignPatientToRoom and assign them to room 3
    Patient patient = new Patient(3, "Tom", "Smith", "1/9/1987");
    clinic.assignPatientToRoom(patient, 3);
    clinic.displayRoom(3);
    
    // Test assignStaffToPatient function and see if
    // the new clinician Sally Johnson appears under 
    // patient Tom Smith's information
    ClinicalStaff staff = new ClinicalStaff("nurse", "Sally", 
        "Johnson", "allied", "5234252163");
    clinic.assignStaffToPatient(staff, patient);
    clinic.displayRoom(3);
    
    // Test deactivateClinicalStaff and see if 
    // Sally Johnson is not displayed under Tom Smith's
    // information anymore
    clinic.deactivateClinicalStaff(staff);
    clinic.displayRoom(3);
    
    // Test registerNewClinicalStaff and sendPatientHome by
    // using the newly registered active clinician to send
    // Tom Smith home
    clinic.registerNewClinicalStaff("physician", "Liz", 
        "Brown", "doctoral", "8172638492");
    clinic.sendPatientHome(patient);
    clinic.displayRoom(3);
    
  }

}
