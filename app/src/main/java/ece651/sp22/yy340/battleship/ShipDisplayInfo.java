package ece651.sp22.yy340.battleship;

public interface ShipDisplayInfo<T> {
  public T getInfo(Coordinate where, boolean hit);
}
