package ece651.sp22.yy340.battleship;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class CoordinateTest {
  @Test
  public void test_equals() {
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(1, 2);
    Coordinate c3 = new Coordinate(1, 3);
    Coordinate c4 = new Coordinate(3, 2);
    assertEquals(c1, c1); // equals should be reflexsive
    assertEquals(c1, c2); // different objects bu same contents
    assertNotEquals(c1, c3); // different contents
    assertNotEquals(c1, c4);
    assertNotEquals(c3, c4);
    assertNotEquals(c1, "(1, 2)"); // different types
  }

  @Test
  public void test_toString() {
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(1, 2);
    Coordinate c3 = new Coordinate(1, 3);
    Coordinate c4 = new Coordinate(3, 2);
    assertEquals("(1, 2)", c1.toString());
    assertEquals("(1, 2)", c2.toString());
    assertEquals(c1.toString(), c2.toString());
    assertNotEquals(c3.toString(),c4.toString());
    assertNotEquals(c2.toString(), c3.toString());
  }

  @Test
  public void test_hashCode() {
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(1, 2);
    Coordinate c3 = new Coordinate(0, 3);
    Coordinate c4 = new Coordinate(2, 1);
    assertEquals(c1.hashCode(), c2.hashCode());
    assertNotEquals(c1.hashCode(), c3.hashCode());
    assertNotEquals(c1.hashCode(), c4.hashCode());
  }

  @Test
  void test_string_constructor_valid_cases() {
    Coordinate c1 = new Coordinate("B3");
    assertEquals(1, c1.getRow());
    assertEquals(3, c1.getColumn());
     Coordinate c2 = new Coordinate("b3");
    assertEquals(1, c2.getRow());
    assertEquals(3, c2.getColumn());
    Coordinate c3 = new Coordinate("D5");
    assertEquals(3, c3.getRow());
    assertEquals(5, c3.getColumn());
    Coordinate c4 = new Coordinate("d5");
    assertEquals(3, c4.getRow());
    assertEquals(5, c4.getColumn());
    Coordinate c5 = new Coordinate("A9");
    assertEquals(0, c5.getRow());
    assertEquals(9, c5.getColumn());
    Coordinate c6 = new Coordinate("Z0");
    assertEquals(25, c6.getRow());
    assertEquals(0, c6.getColumn());

  }

  @Test
  public void test_string_constructor_error_cases() {
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("00"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("AA"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("@0"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("[0"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("A/"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("A:"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("A"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("A12"));
  }
}
