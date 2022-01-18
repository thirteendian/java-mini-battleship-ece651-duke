package ece651.sp22.yy340.battleship;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  @Test
  public void test_width_and_height() {
    Board <Character> b1 = new BattleShipBoard <Character>(10,20);
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());
  }

  @Test
  public void test_invalid_dimensions(){
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard <Character>(10, 0));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard <Character>(0, 20));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard <Character>(10, -5));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard <Character>(-8, 20));
  }

  private <T> void checkWhatIsAtBoard(BattleShipBoard<T> b, T[][] expected){
    for(int i = 0; i<b.getWidth();i++){
      for(int k = 0; k<b.getHeight();k++){
        assertEquals(expected[k][i], b.whatIsAt(new Coordinate(k,i)));
      }
    }
  }

  @Test
  public void test_Ship_Array(){
    BattleShipBoard<Character> b = new BattleShipBoard<>(30, 20);
    Character[][] board = new Character[20][30];
    checkWhatIsAtBoard(b, board);
    b.tryAddShip(new BasicShip(new Coordinate(4,6)));
    board[4][6]='s';
    checkWhatIsAtBoard(b, board);
    b.tryAddShip(new BasicShip(new Coordinate(10,20)));
    board[10][20] = 's';
    checkWhatIsAtBoard(b, board);
  }

}
