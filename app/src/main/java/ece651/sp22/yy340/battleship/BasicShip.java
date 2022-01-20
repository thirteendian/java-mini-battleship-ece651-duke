package ece651.sp22.yy340.battleship;

import java.util.HashMap;

public abstract class BasicShip<T> implements Ship<T> {

  protected HashMap<Coordinate, Boolean> myPieces;
  protected ShipDisplayInfo<T> myDisplayInfo;

  public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo) {
    this.myPieces = new HashMap<Coordinate, Boolean>();
    this.myDisplayInfo = myDisplayInfo;
    for (Coordinate c : where) {
      this.myPieces.put(c, false);
    }
  }

  @Override
  public boolean occupiesCoordinates(Coordinate where) {

    if (this.myPieces.get(where) != null) {
      return true;
    } else {
      return false;
    }

  }

  @Override
  public boolean isSunk() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void recordHitAt(Coordinate where) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean wasHitAt(Coordinate where) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public T getDisplayInfoAt(Coordinate where) {
    // TODO Auto-generated method stub
    // look up the hit status of this coordinate
    return myDisplayInfo.getInfo(where, false);
  }

}
