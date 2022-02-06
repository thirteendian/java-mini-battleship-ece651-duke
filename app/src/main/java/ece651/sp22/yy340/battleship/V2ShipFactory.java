package ece651.sp22.yy340.battleship;

public class V2ShipFactory extends V1ShipFactory {

  protected Ship<Character> createTShip(Placement where, char orientation, char letter, String name) {
    if (orientation != 'U' && orientation != 'u' && orientation != 'D' && orientation != 'd' && orientation != 'L'
        && orientation != 'l' && orientation != 'R' && orientation != 'r') {
      throw new IllegalArgumentException("Wrong format !");
    }
    // Using second Constructor with T data and T onHit type
    return new TshapeShip<Character>(name, where.getWhere(), orientation, letter, '*');
  }

  protected Ship<Character> createZShip(Placement where, char orientation, char letter, String name){
    if (orientation != 'U' && orientation != 'u' && orientation != 'D' && orientation != 'd' && orientation != 'L'
        && orientation != 'l' && orientation != 'R' && orientation != 'r') {
      throw new IllegalArgumentException("Wrong format !");
    }
    return new ZshapeShip<Character>(name, where.getWhere(), orientation,letter, '*');
  }

  @Override
  public Ship<Character> makeBattleship(Placement where){
    return createTShip(where, where.getOrientation(), 'b',"Battleship");
  }
  @Override
  public Ship<Character> makeCarrier(Placement where){
    return createZShip(where, where.getOrientation(),'c',"Carrier");
  }
}
