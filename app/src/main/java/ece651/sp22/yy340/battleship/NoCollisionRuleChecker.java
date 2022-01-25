package ece651.sp22.yy340.battleship;

public class NoCollisionRuleChecker<T> extends PlacementRuleChecker<T> {
  public NoCollisionRuleChecker(PlacementRuleChecker<T> next){
    super(next);
  }
  
  @Override
  protected boolean checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    for (Coordinate coordinate: theShip.getCoordinates()){
      if(theBoard.whatIsAt(coordinate) != null){
        return false;
      }
    }
    return true;
  }

}
