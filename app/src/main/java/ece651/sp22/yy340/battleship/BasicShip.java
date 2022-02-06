package ece651.sp22.yy340.battleship;

import java.util.HashMap;

public abstract class BasicShip<T> implements Ship<T> {

  /*
   if myPieces.get(c)  is null, c is not part of this Ship
   if myPieces.get(c)  is false, c is part of this ship and has not been hit
   if myPieces.get(c)  is true, c is part of this ship and has been hit
  */
  protected HashMap<Coordinate, Boolean> myPieces; 
  protected ShipDisplayInfo<T> myDisplayInfo;
  protected ShipDisplayInfo<T> enemyDisplayInfo;

  /*
   * Constructor
   */
  public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> eneDisplayInfo) {
    this.myPieces = new HashMap<Coordinate, Boolean>();
    this.myDisplayInfo = myDisplayInfo;
    this.enemyDisplayInfo = eneDisplayInfo;
    for (Coordinate c : where) {
      this.myPieces.put(c, false);
    }
  }

  protected void checkCoordinateInThisShip(Coordinate c) {
    if (myPieces.get(c) == null) {
      throw new IllegalArgumentException("The coordinate is not on ship!\n");
    }
  }

  @Override
  public boolean occupiesCoordinates(Coordinate where) {

    if (this.myPieces.get(where) == null) {
      return false;
    } else {
      return true;
    }

  }
  /*
   *false = not sunk
   *true = sunk
*/
  @Override
  public boolean isSunk() {
    // TODO Auto-generated method stub
    for (boolean part : myPieces.values()) {
      if (part == false) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void recordHitAt(Coordinate where) {
    // TODO Auto-generated method stub
    checkCoordinateInThisShip(where);
    myPieces.put(where, true);
  }

  @Override
  public boolean wasHitAt(Coordinate where) {
    // TODO Auto-generated method stub
    checkCoordinateInThisShip(where);
    return myPieces.get(where);

  }

  @Override
  public T getDisplayInfoAt(Coordinate where, boolean myShip) {
    // TODO Auto-generated method stub
    // look up the hit status of this coordinate
    checkCoordinateInThisShip(where);
    if (myShip) {
      return myDisplayInfo.getInfo(where, myPieces.get(where));
    } else {
      return enemyDisplayInfo.getInfo(where, myPieces.get(where));
    }

  }

  @Override
  public Iterable<Coordinate> getCoordinates() {
    return myPieces.keySet();
  }
  @Override
  public HashMap<Coordinate, Boolean> getmyPieces(){
    return myPieces;
  }
}
