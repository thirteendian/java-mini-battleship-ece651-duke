package ece651.sp22.yy340.battleship;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  @Test
  public void test_width_and_height() {
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20);
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());
  }

  @Test
  public void test_invalid_dimensions() {
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, 0));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(0, 20));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, -5));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(-8, 20));
  }

  private <T> void checkWhatIsAtBoard(BattleShipBoard<T> b, T[][] expected) {
    for (int i = 0; i < b.getHeight(); i++) {
      for (int j = 0; j < b.getWidth(); j++) {
        assertEquals(b.whatIsAt(new Coordinate(i, j)), expected[i][j]);
      }
    }
  }

  @Test
  public void test_Ship_Array() {
    BattleShipBoard<Character> b = new BattleShipBoard<>(10, 20);
    Character[][] board = new Character[20][10];
    checkWhatIsAtBoard(b, board);

    assertEquals("That placement is invalid: the ship goes off the right of the board.", b.tryAddShip(new RectangleShip<Character>("Submarine", new Coordinate(3, 5), 10,10,'s','*')));
    //board[3][5] = 's';
    //checkWhatIsAtBoard(b, board);

    b.tryAddShip(new RectangleShip<Character>(new Coordinate(1, 1), 's', '*'));
    board[1][1] = 's';
    checkWhatIsAtBoard(b, board);
    
  }

}
