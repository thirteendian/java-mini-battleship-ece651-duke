package ece651.sp22.yy340.battleship;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class V2ShipFactoryTest extends V1ShipFactoryTest {
  @Test
  public void test_v2shipfactory() {
    Placement v2_1 = new Placement(new Coordinate(3, 3), 'U');
    Placement v2_2 = new Placement(new Coordinate(3, 3), 'R');
    Placement v2_3 = new Placement(new Coordinate(3, 3), 'D');
    Placement v2_4 = new Placement(new Coordinate(3, 3), 'L');
    Placement v2_err = new Placement(new Coordinate(3, 3), 'H');

    AbstractShipFactory<Character> f = new V2ShipFactory();
    assertThrows(IllegalArgumentException.class, () -> f.makeBattleship(v2_err));
    assertThrows(IllegalArgumentException.class, () -> f.makeCarrier(v2_err));
    Ship<Character> bst1 = f.makeBattleship(v2_1);
    Ship<Character> bst2 = f.makeBattleship(v2_2);
    Ship<Character> bst3 = f.makeBattleship(v2_3);
    Ship<Character> bst4 = f.makeBattleship(v2_4);
    checkShip(bst1, "Battleship", 'b', new Coordinate(3, 4), new Coordinate(4, 3), new Coordinate(4, 4),
        new Coordinate(4, 5));
    checkShip(bst2, "Battleship", 'b', new Coordinate(3, 3), new Coordinate(4, 3), new Coordinate(5, 3),
        new Coordinate(4, 4));
    checkShip(bst3, "Battleship", 'b', new Coordinate(3, 3), new Coordinate(3, 4), new Coordinate(3, 5),
        new Coordinate(4, 4));
    checkShip(bst4, "Battleship", 'b', new Coordinate(3, 4), new Coordinate(4, 3), new Coordinate(4, 4),
        new Coordinate(5, 4));

    Ship<Character> cst1 = f.makeCarrier(v2_1);
    Ship<Character> cst2 = f.makeCarrier(v2_2);
    Ship<Character> cst3 = f.makeCarrier(v2_3);
    Ship<Character> cst4 = f.makeCarrier(v2_4);
    checkShip(cst1, "Carrier", 'c', new Coordinate(3, 3), new Coordinate(4, 3), new Coordinate(5, 3),
        new Coordinate(6, 3), new Coordinate(5, 4), new Coordinate(6, 4), new Coordinate(7, 4));
    checkShip(cst2, "Carrier", 'c', new Coordinate(3, 4), new Coordinate(3, 5), new Coordinate(3, 6),
        new Coordinate(3, 7), new Coordinate(4, 3), new Coordinate(4, 4), new Coordinate(4, 5));
    checkShip(cst3, "Carrier", 'c', new Coordinate(3, 3), new Coordinate(4, 3), new Coordinate(5, 3),
        new Coordinate(4, 4), new Coordinate(5, 4), new Coordinate(6, 4), new Coordinate(7, 4));
    checkShip(cst4, "Carrier", 'c', new Coordinate(3, 5), new Coordinate(3, 6), new Coordinate(3, 7),
        new Coordinate(4, 3), new Coordinate(4, 4), new Coordinate(4, 5), new Coordinate(4, 6));
  }

}
