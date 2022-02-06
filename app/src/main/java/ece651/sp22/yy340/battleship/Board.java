package ece651.sp22.yy340.battleship;

public interface Board<T> {

  public int getWidth();

  public int getHeight();

  public String tryAddShip(Ship<T> toAdd);

  // public T whatIsAt(Coordinate where, boolean isSelf);

  public T whatIsAtForSelf(Coordinate where);

  public Ship<T> fireAt(Coordinate c);

  public T whatIsAtForEnemy(Coordinate where);

  public boolean CheckingLost();
}
