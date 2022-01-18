package ece651.sp22.yy340.battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

public class BoardTextViewTest {
  @Test
  public void test_display_empty_2by2() {
    Board<Character> b1 = new BattleShipBoard<Character>(2, 2);
    BoardTextView view = new BoardTextView(b1);
    String expectedHeader = "  0|1\n";
    assertEquals(expectedHeader, view.makeHeader());
    String expected = expectedHeader + "A  |  A\n" + "B  |  B\n" + expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
  }

  @Test
  public void test_display_non_empty_4by3() {
    LinkedList<Ship<Character>> shipList = new LinkedList<>();
    BasicShip s1 = new BasicShip(new Coordinate(0, 3));
    BasicShip s2 = new BasicShip(new Coordinate(2, 1));
    shipList.add(s1);
    shipList.add(s2);
    Board<Character> b2 = new BattleShipBoard<Character>(4, 3);    
    for (Ship<Character> ship : shipList){
      b2.tryAddShip(ship);
    }
    String expectedHeader = "  0|1|2|3\n";    
    BoardTextView view = new BoardTextView(b2);
    assertEquals(expectedHeader, view.makeHeader());
    String expected = expectedHeader +
      "A  | | |s A\n" +
      "B  | | |  B\n" +
      "C  |s| |  C\n" +
      expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
  }

  @Test
  public void test_invalid_board_size() {
    Board<Character> wideBoard = new BattleShipBoard <Character>(11, 20);
    Board<Character> tallBoard = new BattleShipBoard <Character>(10, 27);
    // you should write two assertThrows here
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(wideBoard));
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(tallBoard)); 
  }

}
