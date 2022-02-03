package ece651.sp22.yy340.battleship;

public class NoCollisionRuleChecker<T> extends PlacementRuleChecker<T> {
  public NoCollisionRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }

  @Override
  protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    for (Coordinate coordinate : theShip.getCoordinates()) {
      if (theBoard.whatIsAt(coordinate) != null) {
        return "That placement is invalide: Ship Overlaps !";
      }
    }
    return null;
  }
}
