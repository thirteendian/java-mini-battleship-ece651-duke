package ece651.sp22.yy340.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlacementTest {
  @Test
  public void test_equals() {
    Coordinate c1 = new Coordinate("A1");
    Coordinate c2 = new Coordinate("A1");
    Coordinate c3 = new Coordinate("C2");
    Placement p1 = new Placement(c1, 'V');
    Placement p2 = new Placement(c2, 'v');
    Placement p3 = new Placement(c2, 'h');
    Placement p4 = new Placement(c3, 'v');
    assertEquals(p1, p2);
    assertEquals(p2, p2);
    assertNotEquals(p1, p3);
    assertNotEquals(p1, p4);
    assertNotEquals(p2, p3);
    assertEquals(false, p1.equals(p4));
    assertNotEquals(p1, "A0V");
  }

  @Test
  void test_hashCode() {
    Coordinate c1 = new Coordinate("A1");
    Coordinate c2 = new Coordinate("A1");
    Coordinate c3 = new Coordinate("C2");
    Placement p1 = new Placement(c1, 'V');
    Placement p2 = new Placement(c2, 'v');
    Placement p3 = new Placement(c2, 'h');
    Placement p4 = new Placement(c3, 'v');
    assertEquals(p1.hashCode(), p2.hashCode());
    assertNotEquals(p1.hashCode(), p3.hashCode());
    assertNotEquals(p1.hashCode(), p4.hashCode());
  }

  @Test
  void test_getter() {
    Coordinate c1 = new Coordinate("A1");
    Coordinate c2 = new Coordinate("B2");
    Coordinate c3 = new Coordinate("C3");
    Placement p1 = new Placement(c1, 'V');
    Placement p2 = new Placement(c2, 'v');
    Placement p3 = new Placement(c3, 'H');
    assertEquals('V', p1.getOrientation());
    assertEquals(c1, p1.getWhere());

    assertEquals('V', p2.getOrientation());
    assertEquals(c2, p2.getWhere());

    assertEquals('H', p3.getOrientation());
    assertEquals(c3, p3.getWhere());
  }

  @Test
  void test_string_constructor_valid_cases() {
    Placement p1 = new Placement("B2v");
    assertEquals(p1.getWhere(), new Coordinate(1, 2));
    assertEquals(p1.getOrientation(), 'V');
    Placement p2 = new Placement("C3H");
    assertEquals(p2.getWhere(), new Coordinate(2, 3));
    assertEquals(p2.getOrientation(), 'H');
    Placement p3 = new Placement("A0h");
    assertEquals(p3.getWhere(), new Coordinate(0, 0));
    assertEquals(p3.getOrientation(), 'H');
  }

  @Test
  void test_string_constructor_error_cases() {
    assertThrows(IllegalArgumentException.class, () -> new Placement("B12H"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("BBH"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("A"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("A0J"));
  }

  @Test
  void test_wrong_input() {
    assertThrows(IllegalArgumentException.class, () -> new Placement(new Coordinate("A0"), 'S'));
  }

}
