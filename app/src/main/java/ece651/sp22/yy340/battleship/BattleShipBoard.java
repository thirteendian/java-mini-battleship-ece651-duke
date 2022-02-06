package ece651.sp22.yy340.battleship;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Constructs a BattleShipBoard with the specified width and height
 * 
 * @param w is the width of the newly constructed board.
 * @param h is the height of the newly constructed board.
 * @throws IllegalArgumentException if the width or height are less than or
 *                                  equal to zero.
 */

public class BattleShipBoard<T> implements Board<T> {
  private final int width;
  private final int height;
  private final ArrayList<Ship<T>> myShips;
  private final PlacementRuleChecker<T> placementChecker;
  private final HashSet<Coordinate> enemyMisses;
  final T missInfo;

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public String tryAddShip(Ship<T> toAdd) {
    String err = placementChecker.checkPlacement(toAdd, this);
    if (err == null) {
      myShips.add(toAdd);
      return null;
    }
    return err;
  }

  public BattleShipBoard(int w, int h, T missInfo) {
    this(w, h, new InBoundsRuleChecker<>(new NoCollisionRuleChecker<>(null)), missInfo);
  }

  public BattleShipBoard(int w, int h, PlacementRuleChecker<T> checker, T missInfo) {

    if (w <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + w);
    }
    if (h <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
    }
    this.width = w;
    this.height = h;
    this.myShips = new ArrayList<>();
    this.placementChecker = checker;
    this.enemyMisses = new HashSet<>();
    this.missInfo = missInfo;
  }

  protected T whatIsAt(Coordinate where, boolean isSelf) {
    for (Ship<T> s : myShips) {
      if (s.occupiesCoordinates(where)) {
        return s.getDisplayInfoAt(where, isSelf);
      }
    }
    return null;
  }

  public T whatIsAtForSelf(Coordinate where) {
    return whatIsAt(where, true);
  }

  public T whatIsAtForEnemy(Coordinate where) {
    return whatIsAt(where, false);
  }

  /*
   * Search ship in Coordinate c if found then: RecordHit return ship if not found
   * then: record in enemyMisses
   */
  public Ship<T> fireAt(Coordinate c) {
    for (Ship<T> s : myShips) {
      if (s.occupiesCoordinates(c)) {
        s.recordHitAt(c);
        return s;
      }
    }
    enemyMisses.add(c);
    return null;
  }

  /*
   * if there exist one is not sink then is not lost
   */
  public boolean CheckingLost() {
    for (Ship<T> myShip : this.myShips) {
      if (myShip.isSunk() == false) {
        return false;
      }
    }
    return true;

  }

}
