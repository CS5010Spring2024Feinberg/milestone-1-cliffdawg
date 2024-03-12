package clinic;

import java.io.IOException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class represents a clinic controller. The 
 * controller has an input and output Readable.
 */
public class ClinicController {
  
  private final Appendable out;
  private final Scanner scan;
  
  /**
   * Constructor for the controller.
   * 
   * @param in  the source to read from
   * @param out the target to print to
   */
  public ClinicController(Readable in, Appendable out) {
    
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    
    this.out = out;
    scan = new Scanner(in);
    
  }
  
  /** 
   * Run the user commands on the clinic. 
   * 
   * @param clinic  The clinic to run commands in
   */
  public void runCommands(Clinic clinic) {
    
    if (clinic == null) {
      throw new IllegalArgumentException("Clinic cannot be null");
    }
    
    boolean play = true;
    
    while (play) {
      
      // Prompt user for what they want to do
      try {
        out.append("Available commands: \nDisplay_Patient, Display_Room, "
            + "Display_All_Rooms, Register_New_Patient, "
            + "Register_Clinician, Register_Existing_Patient, "
            + "Send_Patient_Home, Assign_Patient_To_Room, "
            + "Assign_Clinician_To_Patient, Staff_With_Patients, "
            + "Deactivate_Clinician, Patients_Absent_Over_1_Year, "
            + "Patients_With_2_Or_More_Visits_In_Last_Year, "
            + "Unassign_Clinician_To_Patient, "
            + "Clinicians_Lifetime_Patient_Count, Show_Map, Quit. \nEnter command: \n");
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed", ioe);
      }
      
      // Read user's command
      
      String command = scan.next();
      
      switch (command) {
        
        case "Display_Patient":
          
          // Prompt user for patient's full name
          try {
            out.append("Enter patient first and last name: \n");
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
          
          String firstName = scan.next();
          String lastName = scan.next();
          
          clinic.displayPatient(firstName, lastName);
          
          break;
          
        case "Display_Room":
          
          // Prompt user for room number
          try {
            out.append("Enter room number: \n");
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
          
          String roomNumber = scan.next();
          int room = Integer.parseInt(roomNumber);
          
          clinic.displayRoom(room);
          
          break;
          
        case "Display_All_Rooms":
          
          clinic.seatingChart();
          
          break;
          
        case "Register_New_Patient": 
          
          // Prompt user for patient's information
          try {
            out.append("Enter patient first name, last name,"
                + "and date of birth: \n");
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
          
          String newFirstName = scan.next();
          String newLastName = scan.next();
          String dob = scan.next();
          
          // Prompt user for visit record information
          try {
            out.append("Enter chief complaint and body temperature (C): \n");
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
          
          Date date = new Date();
          String complaint = scan.next();
          String stringTemp = scan.next();
          Double temperature = Double.parseDouble(stringTemp);
          Double roundedTemperature = (double) Math.round(temperature 
              * (int) Math.pow(10, 1)) / (int) Math.pow(10, 1);
          
          clinic.registerNewPatient(newFirstName, newLastName, 
              dob, date, complaint, roundedTemperature);
          
          break;
          
        case "Register_Clinician": 
          
          // Prompt user for clinician's information
          try {
            out.append("Enter clinician job, first name, "
                + "last name, education, and npi:\n");
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
          
          String job = scan.next();
          String clinicianFirst = scan.next();
          String clinicianLast = scan.next();
          String education = scan.next();
          String npi = scan.next();
          
          clinic.registerNewClinicalStaff(job, clinicianFirst, clinicianLast, education, npi);
          
          break;
          
        case "Register_Existing_Patient":
          
          // Prompt user for patient's information
          try {
            out.append("Enter patient first name and last name: \n");
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
          
          String exFirstName = scan.next();
          String exLastName = scan.next();
          
          // Prompt user for visit record information
          try {
            out.append("Enter chief complaint and body temperature (C):\n");
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
          
          Date exDate = new Date();
          String exComplaint = scan.next();
          String exStringTemp = scan.next();
          Double exTemperature = Double.parseDouble(exStringTemp);
          Double exRoundedTemperature = (double) Math.round(exTemperature 
              * (int) Math.pow(10, 1)) / (int) Math.pow(10, 1);
          
          clinic.registerExistingPatient(exFirstName, exLastName, 
              exDate, exComplaint, exRoundedTemperature);
          
          break;
          
        case "Send_Patient_Home": 
          
          // Prompt user for patient's full name
          try {
            out.append("Enter patient first and last name: \n");
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
          
          String homeFirstName = scan.next();
          String homeLastName = scan.next();
          
          clinic.sendPatientHome(homeFirstName, homeLastName);
          
          break;
          
        case "Assign_Patient_To_Room":
          
          // Prompt user for patient's full name
          try {
            out.append("Enter patient first and last name: \n");
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
          
          String assignFirstName = scan.next();
          String assignLastName = scan.next();
          
          // Prompt user for room number
          try {
            out.append("Enter room number: \n");
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
          
          String assignRoom = scan.next();
          int assignRoomNumber = Integer.parseInt(assignRoom);
          
          clinic.assignPatientToRoom(assignFirstName, assignLastName, assignRoomNumber);
          
          break;
          
        case "Assign_Clinician_To_Patient":
          
          // Prompt user for patient's information
          try {
            out.append("Enter patient first name and last name: \n");
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
          
          String clinicAssignFirstName = scan.next();
          String clinicAssignLastName = scan.next();
          
          // Prompt user for clinician's information
          try {
            out.append("Enter clinician first name and last name: \n");
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
          
          String clinicianAssignFirstName = scan.next();
          String clinicianAssignLastName = scan.next();
          
          clinic.assignStaffToPatient(clinicAssignFirstName, clinicAssignLastName, 
              clinicianAssignFirstName, clinicianAssignLastName);
          
          break;
          
        case "Staff_With_Patients":
          
          clinic.listStaffWithPatients();
          
          break;
          
        case "Deactivate_Clinician":
          
          // Prompt user for clinician's information
          try {
            out.append("Enter clinician first name and last name: \n");
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
          
          String clinicianDeactivateFirstName = scan.next();
          String clinicianDeactivateLastName = scan.next();
          
          clinic.deactivateClinicalStaff(clinicianDeactivateFirstName, 
              clinicianDeactivateLastName);
          
          break;
          
        case "Patients_Absent_Over_1_Year":
          
          clinic.listPatientsAbsentYear();
          
          break;
          
        case "Patients_With_2_Or_More_Visits_In_Last_Year":
          
          clinic.listPatientsTwoInYear();
          
          break;
          
        case "Unassign_Clinician_To_Patient":
          
          // Prompt user for patient's information
          try {
            out.append("Enter patient first name and last name: \n");
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
          
          String clinicUnassignFirstName = scan.next();
          String clinicUnassignLastName = scan.next();
          
          // Prompt user for clinician's information
          try {
            out.append("Enter clinician first name and last name: \n");
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
          
          String clinicianUnassignFirstName = scan.next();
          String clinicianUnassignLastName = scan.next();
          
          clinic.unassignStaffFromPatient(clinicUnassignFirstName, clinicUnassignLastName, 
              clinicianUnassignFirstName, clinicianUnassignLastName);
          
          break;
        
        case "Clinicians_Lifetime_Patient_Count":
          
          clinic.listStaffPatientNumber();
          
          break;
          
        case "Show_Map":
          
          clinic.createMap();
          
          break;
          
        case "Quit":
          
          play = false;
          
          break;  
          
        default:
          break;
          
      }
      
    }
    
    
  }
  
}
