package ece651.sp22.yy340.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class RectangleShipTest {
  @Test
  public void test_make_coords() {
    Coordinate c1 = new Coordinate(3, 5);
    HashSet<Coordinate> h1 = RectangleShip.makeCoords(c1, 2, 3);
    assertEquals(true, h1.contains(c1));
    assertEquals(true, h1.contains(new Coordinate(3, 6)));
    assertEquals(true, h1.contains(new Coordinate(3, 7)));
    assertEquals(true, h1.contains(new Coordinate(4, 6)));
    assertEquals(true, h1.contains(new Coordinate(4, 7)));
    assertNotEquals(true, h1.contains(new Coordinate(3, 8)));
    assertNotEquals(true, h1.contains(new Coordinate(5, 5)));
    assertEquals(6, h1.size());

  }

}
