package clinic;

import java.io.IOException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
  
  
  public void takeCommand(Clinic clinic) {
    
    if (clinic == null) {
      throw new IllegalArgumentException("Clinic cannot be null");
    }
    
    boolean play = true;
    
    while (play) {
      
      // Prompt user for what they want to do
      try {
        out.append("Available commands: \n Display_Patient, Display_Room, Display_All_Rooms, "
            + "Register_New_Patient, Register_Clinician, Register_Existing_Patient, "
            + "Send_Patient_Home, Assign_Patient_to_Room, Assign_Clinician_to_Patient. \n Enter command: \n ");
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
          
          clinic.registerNewPatient(newFirstName, newLastName, dob, date, complaint, roundedTemperature);
          
          break;
          
        case "Register_Clinician": 
          
          // Prompt user for clinician's information
          try {
            out.append("Enter clinician job, first name, \n"
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
          
          clinic.registerExistingPatient(exFirstName, exLastName, exDate, exComplaint, exRoundedTemperature);
          
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
          
        case "Assign_Patient_to_Room":
          
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
          
        case "Assign_Clinician_to_Patient":
          
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
          
          clinic.assignStaffToPatient(clinicAssignFirstName, clinicAssignLastName, clinicianAssignFirstName, clinicianAssignLastName);
          
          break;
          
        default:
          break;
          
      }
      
    }
    
    
  }
  
}
