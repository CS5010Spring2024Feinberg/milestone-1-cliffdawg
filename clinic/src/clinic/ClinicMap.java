package clinic;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClinicMap extends JPanel {
 
  private Room[] rooms;
  private Patient[] patients;
  
  public ClinicMap(Room[] rooms, Patient[] patients) {
    this.rooms = rooms;
    this.patients = patients;
  }
  
  public void paint(Graphics g) {
    Image img = generateMap();
    g.drawImage(img, 20,20,this);
  }

  private Image generateMap() {
    
    BufferedImage bufferedImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
    Graphics g = bufferedImage.getGraphics();
  
    
    // Draw the rooms
    for (int i = 0; i < this.rooms.length; i++) {
      g.drawRect((int) (this.rooms[i].getX()/40.0 * 4000 - 2550), 
          (int) (20 + this.rooms[i].getY()/40.0 * 500), 150, 150);
    }
    
    // Draw the patient names in the rooms
    for (int i = 0; i < this.rooms.length; i++) {
      
      int count = 0;
      String patient = "";
      
      for (int j = 0; j < this.patients.length; j++) {
        
        if (this.patients[j].getRoom() - 1 == i && this.rooms[i].hasPatient(this.patients[j].getFirst(), this.patients[j].getLast())) {
          
          patient = String.format("%s %s", this.patients[j].getFirst(), this.patients[j].getLast());
          
          g.drawString(patient, 
              (int) (this.rooms[i].getX()/40.0 * 4000 - 2550), 
              (int) (40 + count * 15 + this.rooms[i].getY()/40.0 * 500));
          
          count++;
          
        }
        
      }
      
    }
      
    return bufferedImage;
    
  }

}
