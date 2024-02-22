package clinic;

import java.io.InputStreamReader;

import clinic.Clinic;
import clinic.Patient;
import clinic.ClinicalStaff;

/**
 * This class is the driver class for testing the clinic object.
 * The output will show whether the clinic object and its methods
 * are operating correctly.
 */
public class ClinicDriver {

  /** 
   * Takes one argument, which is the path to the input file.
   * 
   * @param args    The first argument will be the name and file path of the input file
   */
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
    clinic.registerNewPatient("Joe", "Schmoe", "5/6/1995", null);
    clinic.displayRoom(1);
    
    // Test assignPatientToRoom and assign them to room 3
    Patient patient = new Patient(3, "Tom", "Smith", "1/9/1987");
    clinic.registerNewPatient("Tom", "Smith", "1/9/1987", null);
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
    
    
    
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    ClinicController controller = new ClinicController(input, output);
    controller.runCommands(clinic);

    
    
    
  }

}
