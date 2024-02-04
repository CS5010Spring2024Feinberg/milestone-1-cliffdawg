package clinic;

import java.io.*;
import java.io.FileReader;
import java.util.Arrays;
import java.io.BufferedReader;

public class Clinic {
  
  Room primaryRoom;
  Room[] rooms;
  Patient[] patients;
  Staff[] staff;
  
  public Clinic() {
      
  }
  
  public void readFile(String fileName) {
      
    BufferedReader reader;
      
    try {
       
      char[] array = new char[100];
        
      // Creates a reader to read text file line by line
      reader = new BufferedReader(new FileReader(fileName));
        
      String line = reader.readLine();
        
      String clinicName = line;
        
      line = reader.readLine();
        
      int roomCount = Integer.parseInt(line);
      Room[] tempRooms = new Room[roomCount];
        
      // Create each room and add it to the clinic
      for (int i = 0; i < roomCount; i++) {
        line = reader.readLine();
        String[] data = line.split("\\s+");
        // Join the split strings of name back together
        String[] toJoin = Arrays.copyOfRange(data, 5, data.length);
        String name = String.join(" ", toJoin);
        Room newRoom = new Room(Integer.parseInt(data[0]), Integer.parseInt(data[1]), 
            Integer.parseInt(data[2]), Integer.parseInt(data[3]), data[4], name);
        tempRooms[i] = newRoom;
        if (i == 0) {
          this.primaryRoom = newRoom;
        }
      }
      
      this.rooms = tempRooms;
        
      line = reader.readLine();
        
      int staffCount = Integer.parseInt(line);
      Staff[] tempStaff = new Staff[staffCount];
        
      // Create each staff member and add them to the clinic
      for (int i = 0; i < staffCount; i++) {
        line = reader.readLine();
        String[] data = line.split("\\s+");
        Staff newStaff;
        if (data[4].matches("[a-zA-Z]+")) {
          newStaff = new NonclinicalStaff(data[0], data[1], data[2], data[3], data[4]);
        } else {
          newStaff = new ClinicalStaff(data[0], data[1], data[2], data[3], data[4]);
        }
        tempStaff[i] = newStaff;
      }
      
      this.staff = tempStaff;
        
      line = reader.readLine();
        
      int patientCount = Integer.parseInt(line);
      Patient[] tempPatients = new Patient[patientCount];
        
      // Create each patient and add them to the clinic
      for (int i = 0; i < patientCount; i++) {
        line = reader.readLine();
        String[] data = line.split("\\s+");
        Patient newPatient = new Patient(Integer.parseInt(data[0]), data[1], 
            data[2], data[3]);
        tempPatients[i] = newPatient;
      }
      
      this.patients = tempPatients;
            
      // Close the reader
      reader.close();
        
    } catch(IOException e) {
      System.out.println("File not found!");
        
    }
      
  }

}
