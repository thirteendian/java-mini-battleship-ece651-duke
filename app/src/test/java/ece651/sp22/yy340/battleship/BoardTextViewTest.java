package ece651.sp22.yy340.battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

public class BoardTextViewTest {
  @Test
  public void test_display_empty_2by2() {
    Board<Character> b1 = new BattleShipBoard<Character>(2, 2,'X');
    BoardTextView view = new BoardTextView(b1);
    String expectedHeader = "  0|1\n";
    assertEquals(expectedHeader, view.makeHeader());
    String expected = expectedHeader + "A  |  A\n" + "B  |  B\n" + expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
  }

  @Test
  public void test_display_non_empty_4by3() {
    LinkedList<Ship<Character>> shipList = new LinkedList<>();
    Board<Character> b2 = new BattleShipBoard<Character>(4, 3,'X');
    RectangleShip<Character> s1 = new RectangleShip<Character>(new Coordinate(0, 3), 's', '*');
    RectangleShip<Character> s2 = new RectangleShip<Character>(new Coordinate(2, 1), 's', '*');

    b2.tryAddShip(s1);
    b2.tryAddShip(s2);

    String expectedHeader = "  0|1|2|3\n";
    BoardTextView view = new BoardTextView(b2);
    assertEquals(expectedHeader, view.makeHeader());
    String expected = expectedHeader + "A  | | |s A\n" + "B  | | |  B\n" + "C  |s| |  C\n" + expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
  }

  @Test
  public void test_invalid_board_size() {
    Board<Character> wideBoard = new BattleShipBoard<Character>(11, 20,'X');
    Board<Character> tallBoard = new BattleShipBoard<Character>(10, 27,'X');
    // you should write two assertThrows here
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(wideBoard));
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(tallBoard));
  }

  @Test
  public void test_two_boardview(){
    Board<Character> b1 = new BattleShipBoard<Character>(4, 3, 'X');
    Board<Character> b2 = new BattleShipBoard<Character>(4, 3, 'X');
    BoardTextView v1 = new BoardTextView(b1);
    BoardTextView v2 = new BoardTextView(b2);
    String myview = "     my_board                              enemy_board\n"+
      "  0|1|2|3                               0|1|2|3\n"+
      "A  | | |  A                           A  | | |  A\n"+
      "B  | | |  B                           B  | | |  B\n"+
      "C  | | |  C                           C  | | |  C\n"+
      "  0|1|2|3                               0|1|2|3\n";
    assertEquals(myview, v1.displayMyBoardWithEnemyNextToIt(v2, "my_board", "enemy_board"));
  }

}
