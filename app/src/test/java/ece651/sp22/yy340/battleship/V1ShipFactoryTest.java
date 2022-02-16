package ece651.sp22.yy340.battleship;

import static org.junit.jupiter.api.Assertions.*;

import javax.management.remote.SubjectDelegationPermission;

import org.junit.jupiter.api.Test;

public class V1ShipFactoryTest {

  protected void checkShip(Ship<Character> testShip, String expectedName, char expectedLetter,
      Coordinate... expectedLocs) {
    assertEquals(expectedName, testShip.getName());
    for (Coordinate coordinate_t : expectedLocs) {
      assertEquals(expectedLetter, testShip.getDisplayInfoAt(coordinate_t, true));
      assertEquals(true, testShip.occupiesCoordinates(coordinate_t));
    }

  }

  @Test
  public void test_v1shipfactory() {
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'v');
    AbstractShipFactory<Character> f = new V1ShipFactory();
    Ship<Character> dst = f.makeDestroyer(v1_2);
    checkShip(dst,"Destroyer",'d',new Coordinate(1, 2));

    Ship<Character> car = f.makeCarrier(v1_2);
    checkShip(car,"Carrier",'c',new Coordinate(1, 2));

        Ship<Character> sub = f.makeSubmarine(v1_2);
    checkShip(sub,"Submarine",'s',new Coordinate(1, 2));

        Ship<Character> bat = f.makeBattleship(v1_2);
    checkShip(bat,"Battleship",'b',new Coordinate(1, 2));
  }
}
