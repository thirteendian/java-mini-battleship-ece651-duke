package ece651.sp22.yy340.battleship;

public class BasicShip implements Ship <Character> {

  private final Coordinate myLocation;
  BasicShip(Coordinate where){
    myLocation = where;
  }
  
  @Override
  public boolean occupiesCoordinates(Coordinate where) {
    
    return where.equals(myLocation);
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
  public Character getDisplayInfoAt(Coordinate where) {
    // TODO Auto-generated method stub
    return 's';
  }

}
