package clinic;

import clinic.Clinic;
import clinic.ClinicalStaff;
import clinic.Patient;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

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
    clinic.displayRoom(3);
    
    // Test assignStaffToPatient function and see if
    // the new clinician Sally Johnson appears under 
    // patient Tom Smith's information
    ClinicalStaff staff = new ClinicalStaff("nurse", "Sally", 
        "Johnson", "allied", "5234252163");
    clinic.registerNewClinicalStaff("nurse", "Sally", 
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
    
    // Test assigning more than one staff to one patient
    Patient patient2 = new Patient(2, "Jill", "Kim", "7/2/1969");
    clinic.registerNewPatient("Jill", "Kim", "7/2/1969", null);
    clinic.assignPatientToRoom(patient2, 2);
    ClinicalStaff staff2 = new ClinicalStaff("nurse", "Tim", 
        "Wong", "allied", "6124259163");
    clinic.registerNewClinicalStaff("nurse", "Tim", 
        "Wong", "allied", "6124259163");
    ClinicalStaff staff3 = new ClinicalStaff("physician", "Luke", 
        "James", "doctoral", "8495827421");
    clinic.registerNewClinicalStaff("physician", "Luke", 
        "James", "doctoral", "8495827421");
    clinic.assignStaffToPatient(staff2, patient2);
    clinic.assignStaffToPatient(staff3, patient2);
    clinic.displayRoom(2);
    
    // Test assigning staff to more than one patient
    Patient patient3 = new Patient(4, "Wendy", "Tom", "4/2/1981");
    clinic.registerNewPatient("Wendy", "Tom", "4/2/1981", null);
    clinic.assignPatientToRoom(patient3, 4);
    clinic.assignStaffToPatient(staff2, patient3);
    clinic.displayRoom(4);
    
    // Test no more than one patient in room
    Patient patient4 = new Patient(4, "Sam", "Hanks", "4/17/1971");
    clinic.registerNewPatient("Sam", "Hanks", "4/17/1971", null);
    clinic.displayRoom(4);
    
    // Test that staff that has been deactivated
    // cannot be assigned to patient afterwards
    clinic.assignStaffToPatient(staff, patient4);
    clinic.displayRoom(4);
    
    // Test that staff who is assigned to at least
    // one patient can be deactivated
    clinic.deactivateClinicalStaff(staff3);
    clinic.displayRoom(2);

    // Create a patient that has not been in the clinic
    // for two years to test listing patients who have
    // been absent over a year
    clinic.createAbsentTwoYears();
    
    // Readable input = new InputStreamReader(System.in);
    // Appendable output = System.out;
    Readable input = new StringReader("Register_Clinician "
        + "physician Bob Wu doctoral 1742839402 Assign_Clinician_To_Patient Jill Kim Bob Wu "
        + "Display_Patient Jill Kim Display_Room 4 Display_All_Rooms "
        + "Register_New_Patient Joe Harker 3/1/1993 Soreness 37.1 "
        + "Display_Patient Joe Harker Assign_Patient_To_Room Joe Harker 5 Display_Room 5 "
        + "Send_Patient_Home Joe Harker Display_Room 5 "
        + "Register_Existing_Patient Joe Harker Foot 37.2 Display_Room 1 "
        + "Display_All_Rooms Staff_With_Patients Deactivate_Clinician Tim Wong "
        + "Display_Room 1 Patients_Absent_Over_1_Year "
        + "Patients_With_2_Or_More_Visits_In_Last_Year "
        + "Unassign_Clinician_To_Patient Jill Kim Bob Wu Display_Patient Jill Kim "
        + "Clinicians_Lifetime_Patient_Count Show_Map Quit");
    Appendable output = new StringWriter();
    ClinicController controller = new ClinicController(input, output);
    controller.runCommands(clinic);  
    
  }

}
