package clinic;

import java.io.*;
import java.io.FileReader;
import java.io.BufferedReader;

public class Clinic {
  
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
        
        for (int i = 0; i < roomCount; i++) {
          line = reader.readLine();
          // Create a room and add it to the clinic
          String[] data = line.split("\\s+");
        }
        
        line = reader.readLine();
        
        int staffCount = Integer.parseInt(line);
        
        for (int i = 0; i < staffCount; i++) {
          line = reader.readLine();
          // Create a staff member and add them to the clinic
          String[] data = line.split("\\s+");
        }
        
        line = reader.readLine();
        
        int patientCount = Integer.parseInt(line);
        
        for (int i = 0; i < patientCount; i++) {
          line = reader.readLine();
          // Create a patient and add them to the clinic
          String[] data = line.split("\\s+");
        }
            
        // Close the reader
        reader.close();
        
      } catch(IOException e) {
        System.out.println("File not found!");
        
      }
      
    }

}
