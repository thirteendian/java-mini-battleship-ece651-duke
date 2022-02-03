package ece651.sp22.yy340.battleship;

public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {

  public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }

  @Override
  protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    // TODO Auto-generated method stub
    int h = theBoard.getHeight();
    int w = theBoard.getWidth();
    for(Coordinate c : theShip.getCoordinates()){
      if(c.getRow()> h-1){
        return "That placement is invalid: the ship goes off the bottom of the board.";
      }
      if(c.getRow()< 0){
        return "That placement is invalid: the ship goes off the top of the board.";
      }
      if(c.getColumn() > (w-1)){
        return "That placement is invalid: the ship goes off the right of the board.";
      }
      if(c.getColumn() < 0){
        return "That placement is invalid: the ship goes off the left of the board.";
      }
      
      /*
      if(c.getRow()>=0 && c.getRow()<h && c.getColumn() >= 0 && c.getColumn() < w){
        return true;
        } */
      
    }
    return null;
  }

}
