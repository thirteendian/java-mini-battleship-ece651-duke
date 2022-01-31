package ece651.sp22.yy340.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class RectangleShipTest {
 
  @Test
  public void test_make_coords() {
    Coordinate c1 = new Coordinate(3, 5);
    HashSet<Coordinate> h1 = RectangleShip.makeCoords(c1, 2, 3);
    assertEquals(true, h1.contains(c1));
    assertEquals(true, h1.contains(new Coordinate(3, 6)));
    assertEquals(false, h1.contains(new Coordinate(3, 7)));
    assertEquals(true, h1.contains(new Coordinate(4, 6)));
    assertEquals(false, h1.contains(new Coordinate(4, 7)));
    assertEquals(false, h1.contains(new Coordinate(3, 8)));
    assertEquals(true, h1.contains(new Coordinate(5, 5)));
    assertEquals(6, h1.size());
  }

  @Test
  public void test_constructor() {
    Coordinate c1 = new Coordinate(5, 6);
    Coordinate c2 = new Coordinate(6, 7);
    Coordinate c3 = new Coordinate(7, 9);
    ShipDisplayInfo<Character> info = new SimpleShipDisplayInfo<Character>('t', '*');

    RectangleShip<Character> s1 = new RectangleShip<Character>(c1, 's', '*');
    assertEquals(true, s1.occupiesCoordinates(c1));

    RectangleShip<Character> s2 = new RectangleShip<Character>("submarine",c1, 2, 3, info);
    assertEquals("submarine", s2.getName());
    assertEquals(true, s2.occupiesCoordinates(c2));
    assertEquals(false, s2.occupiesCoordinates(c3));

    RectangleShip<Character> s3 = new RectangleShip<Character>("submarine",c1, 2, 3, 's', '*');
    assertEquals(true, s3.occupiesCoordinates(c2));
    assertEquals(false, s3.occupiesCoordinates(c3));

  }
  
  
  @Test
    public void test_coordinate_not_in_ship() {
      Coordinate c1 = new Coordinate(8, 4);
      Coordinate c2 = new Coordinate(7, 5);
      RectangleShip<Character> s = new RectangleShip<Character>("submarine",c1, 2, 3, 's', '*');
      assertThrows(IllegalArgumentException.class, () -> s.checkCoordinateInThisShip(c2));
    }
  
    @Test
    public void test_hit() {
      Coordinate c1 = new Coordinate(8, 4);
      Coordinate c2 = new Coordinate(9, 5);
      RectangleShip<Character> s = new RectangleShip<Character>("submarine",c1, 2, 3, 's', '*');
      s.recordHitAt(c2);
      assertEquals(true, s.wasHitAt(c2));
      assertNotEquals(true, s.wasHitAt(c1));
    }
  
    @Test
    public void test_sunk() {
      Coordinate c1 = new Coordinate(2, 3);
      Coordinate c2 = new Coordinate(2, 4);
      RectangleShip<Character> s = new RectangleShip<Character>("submarine",c1, 2, 1, 's', '*');
      //assertThrows(IllegalArgumentException.class, () -> s.recordHitAt(c2));
      s.recordHitAt(c2);
      assertEquals(true, s.isSunk());
      s.recordHitAt(c1);
      assertEquals(true, s.isSunk());
    }
  
    @Test
    public void test_occupiesCoordinates() {
      HashSet<Coordinate> h1 = new HashSet<Coordinate>();
      h1.add(new Coordinate(3, 3));
      h1.add(new Coordinate(3, 4));
      h1.add(new Coordinate(4, 3));
      h1.add(new Coordinate(4, 4));
      HashSet<Coordinate> h2 = new HashSet<Coordinate>();
      h2.add(new Coordinate(3, 3));
      h2.add(new Coordinate(3, 4));
      HashSet<Coordinate> h3 = new HashSet<Coordinate>();
      h3.add(new Coordinate(3, 3));
      h3.add(new Coordinate(3, 7));
      RectangleShip<Character> s = new RectangleShip<Character>("submarine",new Coordinate(3, 3), 2, 2, 's', '*');
      Coordinate c1 = new Coordinate(3, 3);
      Coordinate c2 = new Coordinate(3, 4);
      Coordinate c3 = new Coordinate(4, 4);
      assertEquals(true, s.occupiesCoordinates(c1));
      assertEquals(true, s.occupiesCoordinates(c2));
      assertEquals(true, s.occupiesCoordinates(c3));
    } 


}
