package ece651.sp22.yy340.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class TextPlayer {
  final Board<Character> theBoard;
  final BoardTextView view;
  final BufferedReader inputReader;
  final PrintStream out;
  final AbstractShipFactory<Character> shipFactory;
  final String name;

  public TextPlayer(String name, Board<Character> theBoard, BufferedReader inputReader, PrintStream out,
      AbstractShipFactory<Character> shipFactory) {
    this.theBoard = theBoard;
    this.view = new BoardTextView(theBoard);
    this.inputReader = inputReader;
    this.out = out;
    this.name = name;
    this.shipFactory = shipFactory;
  }
  
  public Placement readPlacement(String prompt) throws IOException {
    out.println(prompt);
    String s = inputReader.readLine();
    return new Placement(s);
  }

  public void doOnePlacement() throws IOException {
    Placement pos = readPlacement("Player "+ this.name +" where would you like to put your ship?");
    Ship<Character> s = shipFactory.makeDestroyer(pos);
    theBoard.tryAddShip(s);
    out.print(view.displayMyOwnBoard());
  }
  public void doPlacementPhase() throws IOException {
        view.displayMyOwnBoard();
        StringBuilder prompt = new StringBuilder("");
        out.print("--------------------------------------------------------------------------------\n");
        
        prompt.append("Player " + this.name + ":");
        prompt.append(" you are going to place the following ships (which are all rectangular). For each ship, type the coordinate of the upper left side of the ship, followed by either H (for horizontal) or V (for vertical).  For example M4H would place a ship horizontally starting at M4 and going to the right.  You have\n\n"
        + "2 \"Submarines\" ships that are 1x2\n" + "3 \"Destroyers\" that are 1x3\n"
        + "3 \"Battleships\" that are 1x4\n" + "2 \"Carriers\" that are 1x6\n");
        out.print(prompt.toString());
        out.print("--------------------------------------------------------------------------------\n");
        doOnePlacement();
    }



}
