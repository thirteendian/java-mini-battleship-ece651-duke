package ece651.sp22.yy340.battleship;

import java.util.ArrayList;

public interface Board<T> {

  public int getWidth();

  public int getHeight();

  public String tryAddShip(Ship<T> toAdd);

  public String tryAddShipbyIndex(Ship<T> toAdd, int index);
  // public T whatIsAt(Coordinate where, boolean isSelf);

  public T whatIsAtForSelf(Coordinate where);

  public Ship<T> fireAt(Coordinate c);

  public T whatIsAtForEnemy(Coordinate where);

  public boolean CheckingLost();

  public ArrayList<Ship<T>> getShipList();

  public void moveShip(Ship<T> oldShip, Placement newPlacement);
}
