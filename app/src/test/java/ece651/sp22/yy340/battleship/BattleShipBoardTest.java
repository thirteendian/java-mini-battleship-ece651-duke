package ece651.sp22.yy340.battleship;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  @Test
  public void test_width_and_height() {
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20, 'X');
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());
  }

  @Test
  public void test_invalid_dimensions() {
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, 0, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(0, 20, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, -5, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(-8, 20, 'X'));
  }

  private <T> void checkWhatIsAtBoard(BattleShipBoard<T> b, T[][] expected) {
    for (int i = 0; i < b.getHeight(); i++) {
      for (int j = 0; j < b.getWidth(); j++) {
        assertEquals(b.whatIsAtForSelf(new Coordinate(i, j)), expected[i][j]);
      }
    }
  }

  @Test
  public void test_Ship_Array() {
    BattleShipBoard<Character> b = new BattleShipBoard<>(10, 20, 'X');
    Character[][] board = new Character[20][10];
    checkWhatIsAtBoard(b, board);

    assertEquals("That placement is invalid: the ship goes off the right of the board.",
        b.tryAddShip(new RectangleShip<Character>("Submarine", new Coordinate(3, 5), 10, 10, 's', '*')));
    // board[3][5] = 's';
    // checkWhatIsAtBoard(b, board);

    b.tryAddShip(new RectangleShip<Character>(new Coordinate(1, 1), 's', '*'));
    board[1][1] = 's';
    checkWhatIsAtBoard(b, board);

  }

  @Test
  public void test_fireat() {
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(4, 3, 'X');
    V1ShipFactory v1shipfactory = new V1ShipFactory();
    Ship<Character> s = v1shipfactory.makeDestroyer(new Placement("A1V"));
    BoardTextView view = new BoardTextView(b);
    b.tryAddShip(s);

    String myView =
      "  0|1|2|3\n" +
      "A  |d| |  A\n" +
      "B  |d| |  B\n" +
      "C  |d| |  C\n" +
      "  0|1|2|3\n";
    // make sure we laid things out the way we think we did.
    assertEquals(myView, view.displayMyOwnBoard());
    assertEquals('d', b.whatIsAtForSelf(new Coordinate(0, 1)));
    assertEquals('d', b.whatIsAtForSelf(new Coordinate(1, 1)));
    assertEquals('d', b.whatIsAtForSelf(new Coordinate(2, 1)));

    assertEquals(null, b.whatIsAtForEnemy(new Coordinate(0, 1)));
    assertSame(s, b.fireAt(new Coordinate(0, 1)));
    assertEquals('*', b.whatIsAtForSelf(new Coordinate(0, 1)));
    assertEquals('d', b.whatIsAtForEnemy(new Coordinate(0, 1)));
    assertSame(null, b.fireAt(new Coordinate(2, 2)));
    assertFalse(s.isSunk());
    String enermyView =
      "  0|1|2|3\n" +
      "A  |d| |  A\n" +
      "B  | | |  B\n" +
      "C  | | |  C\n" +
      "  0|1|2|3\n";
    assertEquals(enermyView, view.displayEnemyBoard());
    // Check lost
    assertFalse(b.CheckingLost());
    b.fireAt(new Coordinate(1, 1));
    b.fireAt(new Coordinate(2, 1));
        String enermyView1 =
      "  0|1|2|3\n" +
      "A  |d| |  A\n" +
      "B  |d| |  B\n" +
      "C  |d| |  C\n" +
      "  0|1|2|3\n";
     assertEquals(enermyView1, view.displayEnemyBoard());
     //Now all pieces is true in myPieces, isSunk is true
     assertTrue(s.isSunk());

     //also lost
     assertTrue(b.CheckingLost());
  }
  @Test
  public void test_moveShip(){
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(4, 3, 'X');
    V2ShipFactory v1shipfactory = new V2ShipFactory();
    Ship<Character> s = v1shipfactory.makeBattleship(new Placement("A1U"));
    BoardTextView view = new BoardTextView(b);
    b.tryAddShip(s);

    String myView =
      "  0|1|2|3\n" +
      "A  | |b|  A\n" +
      "B  |b|b|b B\n" +
      "C  | | |  C\n" +
      "  0|1|2|3\n";
    assertEquals(view.displayMyOwnBoard(), myView);

    b.fireAt(new Coordinate(1,2));
    b.moveShip(s, new Placement("B1U"));
    String myView1 =
      "  0|1|2|3\n" +
      "A  | | |  A\n" +
      "B  | |*|  B\n" +
      "C  |b|b|b C\n" +
      "  0|1|2|3\n";
    assertEquals(view.displayMyOwnBoard(), myView1);

  }
  
}
