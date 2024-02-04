package clinic;

public class ClinicDriver {

  public static void main(String[] args) {
    
    String fileName = args[0];
    
    Clinic clinic = new Clinic();
    
    clinic.readFile(fileName);
    
   
    
    // TODO Auto-generated method stub
    /*TicTacToe game = // instantiate concrete implementation
    System.out.println("Example of X winning:");
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(1, 1);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(3, 3);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    try {
      game.move(1, 1);
    } catch (IllegalStateException ex) {
      System.out.println("\tIllegal move");
    }
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(3, 1);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(2, 1);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(1, 3);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(2, 2);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(1, 2);
    if (game.isGameOver()) {
      System.out.println("Game Over: " + game.getWinner() + " wins");
    } else {
      System.out.println("Game should have been over but wasn't");
    }
    System.out.println("At the end to the game, the board was:");
    printBoard(game);
    

    System.out.println();
    System.out.println("Example of tied game:");
    game = // instantiate concrete implementation
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(1, 1);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(2, 2);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(2, 1);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(3, 1);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(1, 3);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(1, 2);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(3, 2);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(3, 3);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(2, 3);
    if (game.isGameOver()) {
      System.out.println("Game Over: " + game.getWinner() + " wins");
    } else {
      System.out.println("Game should have been over but wasn't");
    }
    System.out.println("At the end to the game, the board was:");
    printBoard(game);*/
  }

}
