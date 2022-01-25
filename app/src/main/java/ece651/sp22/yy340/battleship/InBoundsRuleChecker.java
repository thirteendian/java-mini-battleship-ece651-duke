package ece651.sp22.yy340.battleship;

public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {

  public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }

  @Override
  protected boolean checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    // TODO Auto-generated method stub
    int h = theBoard.getHeight();
    int w = theBoard.getWidth();
    for(Coordinate c : theShip.getCoordinates()){
      if(c.getRow()>=0 && c.getRow()<h && c.getColumn() >= 0 && c.getColumn() < w){
        return true;
      }
    }
    return false;
  }

}
