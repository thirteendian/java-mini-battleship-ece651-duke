package ece651.sp22.yy340.battleship;

public class V1ShipFactory implements AbstractShipFactory<Character> {

  protected Ship<Character> createShip(Placement where, int w, int h, char letter, String name) {
    //If horizontal then switch the width and height
    if (where.getOrientation() == 'H') {
      int temp = w;
      w = h;
      h = temp;
    }
    return new RectangleShip<Character>(name, where.getWhere(), w, h, letter, '*');
  }

  @Override
  public Ship<Character> makeSubmarine(Placement where) {

    return createShip(where, 1, 2, 's', "Submarine");
  }

  @Override
  public Ship<Character> makeBattleship(Placement where) {

    return createShip(where, 1, 4, 'b', "Battleship");

  }

  @Override
  public Ship<Character> makeCarrier(Placement where) {
    
    return createShip(where, 1, 6, 'c', "Carrier");

  }

  @Override
  public Ship<Character> makeDestroyer(Placement where) {
    // TODO Auto-generated method stub
    return createShip(where, 1, 3, 'd', "Destroyer");

  }

}
