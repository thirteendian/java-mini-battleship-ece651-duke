package ece651.sp22.yy340.battleship;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;

public class TextPlayer {
  final Board<Character> theBoard;
  final BoardTextView view;
  final BufferedReader inputReader;
  final PrintStream out;
  final AbstractShipFactory<Character> shipFactory;
  final String name;
  final ArrayList<String> shipsToPlace;
  final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;
  protected int moveCounter;
  protected int sonarCounter;

  public TextPlayer(String name, Board<Character> theBoard, BufferedReader inputReader, PrintStream out,
      AbstractShipFactory<Character> shipFactory) {
    this.theBoard = theBoard;
    this.view = new BoardTextView(theBoard);
    this.inputReader = inputReader;
    this.out = out;
    this.name = name;
    this.shipFactory = shipFactory;
    shipsToPlace = new ArrayList<>();
    shipCreationFns = new HashMap<>();
    setupShipCreationMap();
    setupShipCreationList();
    this.moveCounter = 2;
    this.sonarCounter = 1;
  }

  public Placement readPlacement(String prompt) throws IOException {
    out.println(prompt);
    String s = inputReader.readLine();
    if (s == null) {
      throw new EOFException();
    }
    return new Placement(s);
  }

  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
    Placement pos = readPlacement("Player " + this.name + " where would you like to place a " + shipName + "?");
    Ship<Character> s = createFn.apply(pos);
    theBoard.tryAddShip(s);
    out.print(view.displayMyOwnBoard());
  }

  public void doPlacementPhase() throws IOException {
    view.displayMyOwnBoard();
    StringBuilder prompt = new StringBuilder("");
    out.print("--------------------------------------------------------------------------------\n");

    prompt.append("Player " + this.name + ":");
    prompt.append(
        " you are going to place the following ships (which are all rectangular). For each ship, type the coordinate of the upper left side of the ship, followed by either H (for horizontal) or V (for vertical).  For example M4H would place a ship horizontally starting at M4 and going to the right.  You have\n\n"
            + "2 \"Submarines\" ships that are 1x2\n" + "3 \"Destroyers\" that are 1x3\n"
            + "3 \"Battleships\" that are 1x4\n" + "2 \"Carriers\" that are 1x6\n");
    out.print(prompt.toString());
    out.print("--------------------------------------------------------------------------------\n");
    for (String ship : this.shipsToPlace) {
      doOnePlacement(ship, this.shipCreationFns.get(ship));
    }
  }

  protected void setupShipCreationMap() {
    shipCreationFns.put("Submarine", (p) -> shipFactory.makeSubmarine(p));
    shipCreationFns.put("Destroyer", (p) -> shipFactory.makeDestroyer(p));
    shipCreationFns.put("Battleship", (p) -> shipFactory.makeBattleship(p));
    shipCreationFns.put("Carrier", (p) -> shipFactory.makeCarrier(p));
  }

  protected void setupShipCreationList() {
    shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));
    shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));
    shipsToPlace.addAll(Collections.nCopies(3, "Battleship"));
    shipsToPlace.addAll(Collections.nCopies(2, "Carrier"));
  }

  public void playOneTurn(Board<Character> enemyBoard, BoardTextView enemyView) throws IOException {
    String myHeader = "Your Views:";
    String enemyHeader = "Enemy Views:";
    out.print("     This is Player " + this.name + " 's Turn: Please move: \n");
    out.print("---------------------------------------------------------------------------\n");
    out.print("Possible actions for Player " + this.name + ": \n");
    out.print("  F Fire at a square\n");
    out.print("  M Move a ship to another square (" + moveCounter + " remaining)\n");
    out.print("  S Sonar scan (" + sonarCounter + " remaining)\n");
    out.print("---------------------------------------------------------------------------\n");
    out.print(view.displayMyBoardWithEnemyNextToIt(enemyView, myHeader, enemyHeader));
    Character s = null;
    Coordinate c = null;

    while (true) {
      String promt = inputReader.readLine().toUpperCase();
      if (!promt.equals("F") && !promt.equals("M") && !promt.equals("S")) {
        out.println("Please type a valid choice! Please move:");
        continue;// back to beginning
      }
      ///////////////////////////////////////
      if ((promt.equals("M") && moveCounter <= 0) || (promt.equals("S") && sonarCounter <= 0)) {
        out.println("You do not have any of that remaining! Please move:");
        continue;
      }
      ///////////////////////////////////////
      if (promt.equals("F")) {
        out.println("Please choose a position to Open Fire!:");
        String promt1 = inputReader.readLine();
        try {
          c = new Coordinate(promt1);
          if (c.getColumn() > 0 && c.getColumn() <= (enemyBoard.getWidth() - 1) && c.getRow() > 0
              && c.getRow() <= (enemyBoard.getHeight() - 1)) {
            s = enemyBoard.whatIsAtForSelf(c);
            Ship<Character> e_s = enemyBoard.fireAt(c);
            if (s == null) {
              out.println("Missed!");
            } else if (s == '*') {
              out.println("Already Hit that Place!");
            } else if (s == 's') {
              out.println("Hit Submarine!");
            } else if (s == 'b') {
              out.println("Hit Battleship!");
            } else if (s == 'c') {
              out.println("Hit Carrier!");
            } else if (s == 'd') {
              out.println("Hit Destroyer!!");
            } else {
              out.println("Nothing Happened!");
            }
            break;
          } else {
            throw new IllegalArgumentException("Please input an valid position!\n");
          }
        } catch (IllegalArgumentException err) {
          out.print(err);
          continue;
        }
      }
      ///////////////////////////////////////////
      else if (promt.equals("M")) {
        StringBuilder shiplist = new StringBuilder("");
        int shiplistcounter = 1;
        int choice = 0;
        for (Ship<Character> ship : theBoard.getShipList()) {
          shiplist.append("Choice" + Integer.toString(shiplistcounter) + ":" + ship.getName() + "\n");
          shiplistcounter++;
        }
        out.println("Please choose one of the following ship: \n" + shiplist);
        String promt2 = inputReader.readLine();
        try {
          choice = Integer.parseInt(promt2);
          if (choice <= 0 || choice > theBoard.getShipList().size()) {
            out.println("Please enter an valid choice! Please try again:");
            continue;
          }
        } catch (IllegalArgumentException err) {
          out.print(err);
          continue;
        }

        Ship<Character> originalShip = theBoard.getShipList().get(choice - 1);
        if (originalShip.isSunk()) {
          out.print("That ship is sunk you can't move!, try again:\n");
          continue;
        }

        Placement p;
        try {
          p = readPlacement("New Location:");
        } catch (IllegalArgumentException e) {
          out.print(e);
          continue;
        }

        try {
          theBoard.moveShip(theBoard.getShipList().get(choice), p);
        } catch (IllegalArgumentException e) {
          out.print(e);
          theBoard.tryAddShipbyIndex(originalShip, choice);
          continue;
        }
        moveCounter--;
        break;
      }
      ////////////////////////////////////////////
      else if (promt.equals("S")) {
        Coordinate c_s = null;
        out.print("Entering scanning center:");
        String promt_s = inputReader.readLine();
        try {
          c_s = new Coordinate(promt_s);
          if (c_s.getColumn() < 0 || c_s.getColumn() >= theBoard.getWidth() || c_s.getRow() < 0
              || c_s.getRow() >= theBoard.getHeight()) {
            out.println("Please entering an valid position! Retry:");
            continue;
          }
        } catch (IllegalArgumentException e) {
          out.println("Please retry!");
          continue;
        }
        int ship_number[] = { 0, 0, 0, 0 };

        for (int i = c_s.getRow() - 3; i <= c_s.getRow() + 3; i++) {
          for (int j = c_s.getColumn() - 3; j <= c_s.getColumn() + 3; j++) {
            if (Math.abs(i - c_s.getRow()) + Math.abs(j - c_s.getColumn()) <= 3) {
              if (i >= 0 && i < enemyBoard.getHeight() && j >= 0 && j < enemyBoard.getWidth()) {
                // we will not consider i j outside of the board, even they exist there
                for (Ship<Character> ship_s : theBoard.getShipList()) {
                  if (ship_s.occupiesCoordinates(new Coordinate(i, j))) {
                    String shipname = ship_s.getName();
                    if (shipname == "Submarine") {
                      ship_number[0] += 1;
                    } else if (shipname == "Destroyer") {
                      ship_number[1] += 1;
                    } else if (shipname == "Battleship") {
                      ship_number[2] += 1;
                    } else if (shipname == "Carrier") {
                      ship_number[3] += 1;
                    }
                  }
                }
              }
            }
          }
        }
        out.println("Submarines occupy " + ship_number[0] + " square(s)");
        out.println("Destroyer occupy " + ship_number[1] + " square(s)");
        out.println("Battleships occupy " + ship_number[2] + " square(s)");
        out.println("Carriers occupy " + ship_number[3] + " square(s)");
        sonarCounter--;
        break;
      }

    }
    //////////////////////////////////////////////

  }

  public void textPlayer_print(String enemyName) {
    out.println("  Player " + this.name + " Win!\n");
    out.println("  Player " + enemyName + " Lost!\n");
  }
}
